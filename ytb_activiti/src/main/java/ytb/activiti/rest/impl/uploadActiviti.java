package ytb.activiti.rest.impl;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import ytb.common.basic.activititemplate.model.Manager_Template_ProcModel;
import ytb.activiti.service.ActivitiManagerService;
import ytb.activiti.service.impl.ActivitiManagerServiceImpl;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;


public class uploadActiviti {

    ActivitiManagerService activitiManagerService = new ActivitiManagerServiceImpl();

    public void uploadTemplate(HttpServletRequest request) throws Exception {

        String procId = "";
        Manager_Template_ProcModel mtpm = new Manager_Template_ProcModel();

        //使用Apache文件上传组件处理文件上传步骤：
        //1、创建一个DiskFileItemFactory工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //2、创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解决上传文件名的中文乱码
        upload.setHeaderEncoding("UTF-8");
        //3、判断提交上来的数据是否是上传表单的数据
        if (!ServletFileUpload.isMultipartContent(request)) {
            //按照传统方式获取数据
        }
        //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
        List<FileItem> list = upload.parseRequest(request);
        for (FileItem item : list) {
            //如果fileitem中封装的是普通输入项的数据
            if (item.isFormField()) {
                String name = item.getFieldName();
                //解决普通输入项的数据的中文乱码问题
                InputStream in = item.getInputStream();//获取模板ID
                ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
                byte[] bb = new byte[2048];

                int ch = in.read(bb);
                while (ch != -1) {
                    bytestream.write(bb, 0, ch);
                    ch = in.read(bb);
                }
                if (name.equals("proc_code")) {
                    mtpm.setProc_code(bytestream.toString("UTF-8"));
                } else if (name.equals("proc_type")) {
                    mtpm.setProc_type(Integer.parseInt(bytestream.toString()));
                } else if (name.equals("proc_id")) {
                    mtpm.setProc_id(Integer.parseInt(bytestream.toString()));
                }
                    /*procId = bytestream.toString();
                    System.out.println(procId);*/
            } else {//如果fileitem中封装的是上传文件
                //得到上传的文件名称，
                String filename = item.getName();
                mtpm.setProc_file(filename);
                System.out.println(filename);
                if (filename == null || filename.trim().equals("")) {
                    continue;
                }
                //获取item中的上传文件的输入流
                InputStream in = item.getInputStream();
                byte[] bytes = FileCopyUtils.copyToByteArray(in);
                mtpm.setProc_file(filename);
                mtpm.setProc_content(bytes);
                item.delete();

            }
        }

        activitiManagerService.modifyActivitiTemplateContent(mtpm);
        //activitiManagerService.addActivitiTemplate(mtpm);


    }

    public void uploadTemplateModify(MsgRequest req, MultipartFile mfile) throws Exception {

        if (mfile == null) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR);
        }
        Manager_Template_ProcModel tpm = new Manager_Template_ProcModel();
        tpm.setProc_content(mfile.getBytes());
        int proc_id = req.msgBody.getInteger("proc_id");
        tpm.setProc_id(proc_id);
        activitiManagerService.modifyActivitiTemplateContent(tpm);
    }
}