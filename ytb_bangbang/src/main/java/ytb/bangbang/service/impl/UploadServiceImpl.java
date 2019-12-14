package ytb.bangbang.service.impl;


import org.springframework.web.multipart.MultipartFile;
import ytb.bangbang.model.BBDocument;
import ytb.bangbang.service.BBDocumentService;
import ytb.bangbang.service.UploadService;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 文件保存路经为：D:/YTB_BB_FILE/userId/date/fileType/file
 */
public class UploadServiceImpl  implements UploadService {
    @Override
    public Map<String,String> upload(HttpServletRequest request, MultipartFile file,String rootPath) {

        String fileName="";
        String filePath="";
        if("".equals(file)){
            return null;
        }
        fileName = file.getOriginalFilename();
        System.out.println("文件名:" + fileName);
        //生成一个uuid 的文件名
        UUID randomUUID = UUID.randomUUID();
        String uidFileName=null;
        if(!fileName.matches(".*\\..*")){
            uidFileName = randomUUID+".png";
        }else {
            uidFileName = randomUUID + fileName.substring(fileName.lastIndexOf("."));
        }
        System.out.println(rootPath);
        //将路径转化为文件夹 并 判断文件夹是否存在
        File dir = new File(rootPath);
        if (!dir.isDirectory()||!dir.exists()) {
            dir.mkdirs();
        }
        try {
            //获取一个文件的保存路径
            filePath = rootPath + uidFileName;
            file.transferTo(new File(filePath));//写入磁盘
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("文件路径: "+filePath);
        File f = new File(filePath);
        String fileSize=calculationFileSize(f);
        String fileType=getFileType(fileName);
        Map<String,String> map=new HashMap<String,String>();
        map.put("filePath",filePath);
        map.put("fileSize",fileSize);
        map.put("oldFileName",fileName);
        map.put("newFileName",uidFileName);
        map.put("fileType",fileType);
        return map;
    }
    @Override
    public void fileDownloadByPath(String filePath, HttpServletResponse response,HttpServletRequest request,String fileName){

        OutputStream out = null;
        try {
            byte[] buffer = null;
            byte[] content = new byte[1024];
            File file = new File(filePath);
            if (file.exists()) {
                System.out.println("存在");
                FileInputStream fls = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int n;
                while ((n = fls.read(content)) != -1) {
                    bos.write(content, 0, n);
                }
                fls.close();
                bos.close();
                buffer = bos.toByteArray();
            }
            response.reset();
            response.resetBuffer(); //设置输出流的 HTTP MIME 类型为application/octet-stream
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream"); //将 HTTP 头添加到输出流
            String filename = new String(file.getName().getBytes("UTF-8"), "iso-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setHeader("Content-Length", String.valueOf(buffer.length));
            out = response.getOutputStream();
            out.write(buffer, 0, buffer.length);
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            closeOutputStream(out);
        }
    }

    @Override
    public void previewImage(int documentId, HttpServletResponse res) throws IOException {
        BBDocumentService service=new BBDocumentServiceImpl();
        BBDocument document=service.getDocumentInfo(documentId);
        String picType = getFileType(document.getName());
        if (picType.equalsIgnoreCase("jpg")) {
            res.setContentType("image/jpg");
            picType = "jpg";
        } else if (picType.equalsIgnoreCase("png")) {
            res.setContentType("image/png");
            picType = "png";
        } else {
            res.setContentType("image/jpeg");
            picType = "jpeg";
        }
        String filePath=document.getDocPath();
        // 构造Image对象
        BufferedImage src = ImageIO.read(new File(filePath));
        ImageIO.write(src, picType, res.getOutputStream());
        try {
            res.getOutputStream().flush();
        } finally {
            res.getOutputStream().close();
        }

    }

    //关闭流
    private static void closeOutputStream(OutputStream out) {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
            } finally {
                out = null;
            }
        }
    }

    String calculationFileSize(File file){
        String resource_size = "";
        try {
            FileInputStream fis = new FileInputStream(file);

            DecimalFormat df = new DecimalFormat("#.##");

            double resourcesize = (double)((double)fis.available()/1024);

            if((double)((double)fis.available()/1024)>1000)
            {
                resource_size =df.format((double)((double)fis.available()/1024/1024))+"MB";
            }
            else
            {
                resource_size =df.format((double)((double)fis.available()/1024))+"KB";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return resource_size;
    }

    public String getFileType(String fileName) {
        String[] strArray = fileName.split("\\.");
        int suffixIndex = strArray.length -1;
//        System.out.println(strArray[suffixIndex]);
        return strArray[suffixIndex];
    }

}
