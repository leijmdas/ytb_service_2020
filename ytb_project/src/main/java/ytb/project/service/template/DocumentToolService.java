package ytb.project.service.template;

import com.alibaba.fastjson.JSONObject;
import com.google.common.io.Files;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import ytb.common.basic.safecontext.service.SafeContext;
import ytb.common.utils.YtbSql;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.context.service.impl.YtbContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectDocumentModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.project.service.IDocumentToolService;
import ytb.project.service.impl.flow.ProjectFileServiceImpl;
import ytb.manager.template.model.Dict_document;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.context.model.YtbError;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;

/*
* leijm
* 20181024
* */
public final class DocumentToolService extends ProjectFileServiceImpl implements IDocumentToolService {
    public static DocumentToolService getDocumentToolService() {
        return documentToolService;
    }
    static DocumentToolService documentToolService = new DocumentToolService();

    public ProjectDocumentModel getProjectDocument_v(int documentId){
        StringBuilder sql=new StringBuilder(256);
        sql.append("select * from ytb_project.project_document_v ");
        sql.append(" where document_id=").append(documentId);

        return YtbSql.selectOne(sql,ProjectDocumentModel.class);
    }

    public ProjectTemplateModel getProjectDocById_v(int docListId){
        StringBuilder sql=new StringBuilder(256);
        sql.append("select * from ytb_project.project_template_v ");
        sql.append(" where template_id=").append(docListId);
        return YtbSql.selectOne(sql,ProjectTemplateModel.class);
    }

    @Override
    public String previewJson(Integer v, int documentId) throws UnsupportedEncodingException {
        return v != null && v == 1 ? previewJson_v(documentId) : previewJson_v(documentId);

    }

    public String previewJson_v(int documentId) throws UnsupportedEncodingException {
        ProjectDocumentModel doc = getProjectDocument_v(documentId);
        if (doc == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_DOCUMENT);
        }
        if (doc != null && doc.getDocument() == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_DOCUMENT);
        }

        String ret = new String(doc.getDocument(), "UTF-8");
        if(ret.trim().isEmpty()) {
            ret="{}";
        }
        return ret;
    }

    @Override
    public String previewJson(int documentId) throws UnsupportedEncodingException {
        ProjectDocumentModel doc = getProjectDocument(documentId);
        if (doc == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_DOCUMENT);
        }
        if (doc != null && doc.getDocument() == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_DOCUMENT);
        }

        String ret = new String(doc.getDocument(), "UTF-8").trim();
        return ret.isEmpty()?"{}":ret;
    }

    @Override
    public String getJsonHeader(int documentId) throws UnsupportedEncodingException {
        String ret = previewJson(documentId);
        JSONObject header = JSONObject.parseObject(ret).getJSONObject("header");
        return JSONObject.toJSONString(header);
    }

    //增加压缩标志
    public ProjectDocumentModel getDefaultDocument() {
        ProjectDocumentModel doc = new ProjectDocumentModel();
        doc.setDocType(DOC_TYPE_JSON);
        doc.setSaveMode(SAVEMODE_DB);
        doc.setDocSize(0);
        doc.setName("");
        doc.setPicType("");
        doc.setDocPath("");
        return doc;
    }

    @Override
    public int addJson(String document) throws UnsupportedEncodingException {
        ProjectDocumentModel doc=new ProjectDocumentModel();
        doc.setDocument(document.getBytes("UTF-8"));
        doc.setDocType(DOC_TYPE_JSON);
        doc.setSaveMode(SAVEMODE_DB);
        doc.setDocSize(document.length());
        doc.setName("");
        doc.setPicType("");
        doc.setDocPath("");
        addProjectDocument(doc);
        return doc.getDocumentId();
    }

    @Override
    public int modifyJson( UserProjectContext context,int projectId,int documentId,String document) throws UnsupportedEncodingException {

        ProjectDocumentModel doc = getProjectDocument(documentId);
        if (doc == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        doc.setDocumentId(documentId);
        doc.setDocument(document.getBytes("UTF-8"));
        doc.setDocSize(document.length());
        //修改文档变化时间

        context.getDocumentFamily().updateRefPDocTime(projectId,documentId);
        this.modifyProjectDocument(doc);
        return documentId;
    }

    @Override
    public int modifyJsonHeader( UserProjectContext context,int projectId, int documentId, JSONObject header) throws UnsupportedEncodingException {
        String ret = previewJson(documentId);
        JSONObject j = JSONObject.parseObject(ret);
        for (Map.Entry<String, Object> e : header.entrySet()) {
            if (e.getValue() != null) {
                j.getJSONObject("header").put(e.getKey(), e.getValue());
            }
        }
        return this.modifyJson(context,projectId, documentId, JSONObject.toJSONString(j));

    }


    //return doc key
    @Override
    public int addImage(MsgRequest req, HttpServletRequest request) throws IOException {

        ProjectDocumentModel doc = new ProjectDocumentModel();
        doc.setDocType(DOC_TYPE_PIC);
        doc.setSaveMode(SAVEMODE_DB);
        doc.setName("");
        doc.setPicType("JPG");
        doc.setDocPath("");
        int len = request.getContentLength();
        doc.setDocSize(len);


        //获取item中的上传文件的输入流
        InputStream in = request.getInputStream();//获取模板ID
        byte[] bytes = FileCopyUtils.copyToByteArray(in);
        doc.setDocument(bytes);
        addProjectDocument(doc);
        return doc.getDocumentId();
    }

    //return doc key
    public int add(byte[] body) throws IOException {
        ProjectDocumentModel doc = new ProjectDocumentModel();
        doc.setDocType(DOC_TYPE_PIC);
        doc.setSaveMode(SAVEMODE_DB);
        doc.setName("");
        doc.setPicType("JPG");
        doc.setDocPath("");
        doc.setDocSize(0);
        doc.setDocument(body);
        addProjectDocument(doc);
        return doc.getDocumentId();
    }

    @Override
    public int modifyImage(int documentId, HttpServletRequest request) throws IOException {
        ProjectDocumentModel doc = getProjectDocument(documentId);
        if (doc == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        doc.setDocumentId(documentId);
        int len = request.getContentLength();
        doc.setDocSize(len);
//        InputStream in = request.getInputStream();//获取模板ID
//        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
//        byte[] bb = new byte[2048];
//        int ch = in.read(bb);
//        while (ch != -1) {
//            bytestream.write(bb, 0, ch);
//            ch = in.read(bb);
//        }
//        doc.addProjectDocument(bytestream.toByteArray());
        InputStream in = request.getInputStream();//获取模板ID
        byte[] bytes = FileCopyUtils.copyToByteArray(in);
        doc.setDocument(bytes);
        this.modifyProjectDocument(doc);
        return 1;
    }


    //rest.setDateHeader("Expires", 0);
//        rest.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
//        rest.addHeader("Cache-Control", "post-ytb.check=0, pre-ytb.check=0");
//        rest.setHeader("Pragma", "no-cache");
    @Override
    public void previewImage(int documentId, HttpServletResponse res) throws IOException {

        ProjectDocumentModel doc = getProjectDocument(documentId);
        if (doc == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        String picType=doc.getPicType().trim();
        if(picType.equalsIgnoreCase("jpg")) {
            res.setContentType("image/jpeg");
        }else if(picType.equalsIgnoreCase("png")) {
            res.setContentType("image/png");
        }else{
            res.setContentType("image/jpeg");
            picType="jpg";
        }
        // 构造Image对象
        if (doc == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        BufferedImage src = ImageIO.read(previewImage(documentId,doc));
        ImageIO.write(src, picType , res.getOutputStream());
        try {
            res.getOutputStream().flush();
        } finally {
            res.getOutputStream().close();
        }
    }


    File previewImage(int documentId,  ProjectDocumentModel   projectDocument  ) throws IOException {

        if (projectDocument.getSaveMode() == SAVEMODE_DB) {
            return previewImage_db(documentId,projectDocument);
        }
        return previewImage_path(documentId,projectDocument);
    }

    File previewImage_db(int documentId,ProjectDocumentModel projectDocument) throws IOException {

        if (projectDocument != null && projectDocument.getDocument() == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        File temp = File.createTempFile("temp-talk-name", ".tmp");
        Files.write(projectDocument.getDocument(), temp);
        return temp;
    }

    File previewImage_path(int documentId,ProjectDocumentModel projectDocument) throws IOException {

        if (projectDocument != null && projectDocument.getDocPath()== null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        return  new File(projectDocument.getDocPath());
    }

    public  int uploadBinary(MsgHandler handler, MultipartFile multipartFile) throws Exception{
        MsgRequest req=handler.req;
        if (multipartFile == null) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR);
        }
        ProjectDocumentModel projectDocument = new ProjectDocumentModel();
        if (req.msgBody.getInteger("saveMode") == null) {
            projectDocument.setSaveMode(SAVEMODE_DB);
            projectDocument.setDocType(Dict_document.DOC_TYPE_PIC);
            projectDocument.setPicType("JPG");
        } else {
            projectDocument.setSaveMode(req.msgBody.getInteger("saveMode"));
            projectDocument.setDocType(req.msgBody.getInteger("docType"));
            projectDocument.setPicType(req.msgBody.getString("picType"));
        }
        if (req.msgBody.getString("docPath") != null) {
            projectDocument.setDocPath(req.msgBody.getString("docPath"));
        }
        String originalFilename = multipartFile.getOriginalFilename();
        projectDocument.setName(originalFilename);
        projectDocument.setPicType(originalFilename.substring(originalFilename.lastIndexOf(".") + 1));
        projectDocument.setDocument(multipartFile.getBytes());
        projectDocument.setDocSize(projectDocument.getDocument().length);

        addProjectDocument(projectDocument);
        handler.resp.msgBody.put("fileName", multipartFile.getOriginalFilename());
        return projectDocument.getDocumentId();
    }

    @Override
    public int uploadTemplate(MsgHandler handler, MultipartFile  mfile) throws Exception {
        MsgRequest req=handler.req;
        if (mfile == null) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR,"mfile is null");
        }
        ProjectDocumentModel document =  req.msgBody.parseObject(req.msgBody.toJSONString(), ProjectDocumentModel.class);
        if (req.msgBody.getInteger("saveMode") == null) {
            document.setSaveMode(IDocumentToolService.SAVEMODE_DB);
            document.setDocType(IDocumentToolService.DOC_TYPE_PIC);
            document.setPicType("JPG");
        }
        String p = YtbContext.getYtb_context().getConfig_value("FILE_PATH_RAR");
        document.setDocPath(p);
        String [] ss = document.getName().split("/");
        document.setName(ss[ss.length-1]);

        LoginSso sso = SafeContext.getLog_ssoAndApiKey(req.getToken());
        document.setDocSize((int) mfile.getSize());
        document.setDocument(null);
        if (document.getSaveMode() == IDocumentToolService.SAVEMODE_DB) {
            String m = YtbContext.getYtb_context().getConfig_value("FILE_UPLOAD_DOC_MAX");
            Long l = Long.valueOf(m) * 1024 * 1024;
            if (mfile.getSize() > l) {
                throw new YtbError(YtbError.CODE_FILE_TOO_BIG);
            }
        }
        addProjectDocument(document);
        document.setName(document.getName(sso.getUserId()));

        if (document.getSaveMode() == IDocumentToolService.SAVEMODE_DB) {
            document.setDocument(mfile.getBytes());
        }else
        {
            String m = YtbContext.getYtb_context().getConfig_value("FILE_UPLOAD_RAR_MAX");
            Long l = Long.valueOf(m) * 1024 * 1024;
            if (mfile.getSize() > l) {
                throw new YtbError(YtbError.CODE_FILE_TOO_BIG);
            }
            String fn = document.getDocPath() + "/" + document.getName();
            File f = new File(fn);
            //System.out.println(fn);
            mfile.transferTo(f);
        }
        modifyProjectDocumentName(document);
        return document.getDocumentId();
    }

    @Override
    public void download(int documentId, HttpServletResponse response) throws IOException {

        ProjectDocumentModel document = getProjectDocument(documentId);
        if (document == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;filename=" + document.getName());
        response.setCharacterEncoding("utf-8");
        if (document.getDocType() != Dict_document.DOC_TYPE_RAR) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "文件类型必须是压缩型!");
        }

        String fn = document.getDocPath()+"/"+document.getName();
        // System.out.println("now download ..." + fn );
        File file = new File(fn) ;// File.createTempFile("temp"+fn, ".tmp");
        if (document.getSaveMode() == SAVEMODE_DB) {
            Files.write(document.getDocument(), file);
        }
        // 将文件输入流写入response的输出流中
        IOUtil.copyCompletely(new FileInputStream(file), response.getOutputStream());

    }


    JSONObject transDocument(int documentId)   {
        return (JSONObject)JSONObject.toJSON(getProjectDocument(documentId));

    }

    JSONObject transDocumentString(int documentId) throws UnsupportedEncodingException {
        ProjectDocumentModel dd = getProjectDocument(documentId);
        String s = new String(dd.getDocument(), "UTF-8");
        JSONObject documentjson = (JSONObject) JSONObject.toJSON(dd);
        documentjson.fluentPut("template", s);
        return documentjson;
    }
}
