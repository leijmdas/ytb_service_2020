package ytb.user.service.impl;

import com.google.common.io.Files;

import org.apache.ibatis.session.SqlSession;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.springframework.web.multipart.MultipartFile;
import ytb.user.dao.UserImgMapper;
import ytb.user.model.UserImgModel;
import ytb.user.service.IImageService;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.context.model.YtbError;
import ytb.common.context.service.impl.YtbContext;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageServiceImpl implements IImageService {

    @Override
    public void previewImage(int documentId, HttpServletResponse res) throws IOException {
//        rest.setDateHeader("Expires", 0);
//        rest.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
//        rest.addHeader("Cache-Control", "post-ytb.check=0, pre-ytb.check=0");
//        rest.setHeader("Pragma", "no-cache");
        res.setContentType("image/jpeg");
        UserImgModel userImgModel = getUserImgInfo(documentId);
        String picType = userImgModel.getPicType().trim();
        if(picType.equalsIgnoreCase("jpg")) {
            res.setContentType("image/jpg");
        }else if(picType.equalsIgnoreCase("png")) {
            res.setContentType("image/png");
        }else{
            res.setContentType("image/jpeg");
            picType="jpeg";
        }

        // 构造Image对象
        BufferedImage src = ImageIO.read(previewImage(documentId,userImgModel));
        ImageIO.write(src, picType , res.getOutputStream());
        try {
            res.getOutputStream().flush();
        } finally {
            res.getOutputStream().close();
        }
    }


    @Override
    public int upload(MsgRequest req, MultipartFile mfile) throws Exception {
        if (mfile == null) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR);
        }

        UserImgModel userImgModel =new UserImgModel();

        if(req.msgBody.getInteger("saveMode")==null) {
            userImgModel.setSaveMode(SAVEMODE_DB);
            userImgModel.setDocType(DOC_TYPE_PIC);
            userImgModel.setPicType("JPG");
        }else{
            userImgModel.setDocPath(req.msgBody.getString("docPath"));
            userImgModel.setPicType(req.msgBody.getString("picType"));
            userImgModel.setSaveMode(req.msgBody.getInteger("saveMode"));
            userImgModel.setDocType(req.msgBody.getInteger("docType"));
            userImgModel.setName(req.msgBody.getString("name"));
        }
        if(userImgModel.getSaveMode()==SAVEMODE_DB) {
            userImgModel.setDocument(mfile.getBytes());
            userImgModel.setDocSize(mfile.getBytes().length);
            //添加返回资源ID
            addUserImg(userImgModel);
        }else if(userImgModel.getSaveMode()==SAVEMODE_PATH) {
            userImgModel.setDocument(null);
            //添加返回资源ID
            addUserImg(userImgModel);
            File f=new File(userImgModel.getDocPath());
            mfile.transferTo(f);
            userImgModel.setDocSize((int)f.length());
        }

        return userImgModel.getDocumentId();
    }

    @Override
    public void download(int documentId, HttpServletResponse response) throws IOException {
        UserImgModel userImgModel = getUserImgInfo(documentId);
        if (userImgModel == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        File file = null;
        String fileName = userImgModel.getName();
        if (userImgModel.getSaveMode() == SAVEMODE_DB) {
            file = File.createTempFile("temp-talk-name", ".tmp");
            Files.write(userImgModel.getDocument(), file);
        } else {
            file = new File(userImgModel.getDocPath());
        }
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setCharacterEncoding("utf-8");
        // 将文件输入流写入response的输出流中
        IOUtil.copyCompletely(new FileInputStream(file), response.getOutputStream());
    }

    File previewImage(int documentId,UserImgModel userImgModel ) throws IOException {
        if (userImgModel == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        if (userImgModel.getSaveMode() == SAVEMODE_DB) {
            return previewImage_db(documentId,userImgModel);
        }

        return previewImage_path(documentId,userImgModel);
    }

    File previewImage_db(int documentId,UserImgModel userImgModel) throws IOException {

        if (userImgModel != null && userImgModel.getDocument() == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        File temp = File.createTempFile("temp-talk-name", ".tmp");
        Files.write(userImgModel.getDocument(), temp);

        return temp;
    }

    File previewImage_path(int documentId,UserImgModel userImgModel) throws IOException {

        if (userImgModel != null && userImgModel.getDocPath()== null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        return  new File(userImgModel.getDocPath());
    }


    @Override
    public int addUserImg(UserImgModel userImgModel) {
        SqlSession session = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try{
            UserImgMapper userImgDao = session.getMapper(UserImgMapper.class);
            userImgDao.addUserImg(userImgModel);
            session.commit();
            return userImgModel.getDocumentId();
        }finally {
            session.close();
        }
    }

    @Override
    public void deleteUserImg(int documentId) {
        SqlSession session =  YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        UserImgModel userImgModel = null;
        try {
            UserImgMapper userImgDao = session.getMapper(UserImgMapper.class);
            String f=userImgDao.getUserImgInfo(documentId).getDocPath();
            new File(f).delete();
            userImgDao.deleteUserImg(documentId);
        } finally {
            session.close();
        }
        //删除文档path
    }

    @Override
    public UserImgModel getUserImgInfo(int documentId) {
        SqlSession session =  YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        UserImgModel userImgModel = null;
        try {
            UserImgMapper userImgDao = session.getMapper(UserImgMapper.class);
            userImgModel = userImgDao.getUserImgInfo(documentId);
        } finally {
            session.close();
        }
        return userImgModel;
    }
}
