package ytb.manager.template.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.io.Files;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.util.StringUtil;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.springframework.web.multipart.MultipartFile;
import ytb.common.ytblog.YtbLog;
import ytb.manager.context.ManagerSrvContext;
import ytb.manager.context.MyBatisUtil;
import ytb.manager.template.dao.DocumentMapper;
import ytb.manager.template.dao.TemplateMapper;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.manager.template.model.Dict_document;
import ytb.manager.template.service.IDocumentService;
import ytb.manager.templateexcel.model.xls.TemplateDocumentHeader;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.context.model.YtbError;
import ytb.common.context.service.impl.YtbContext;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import static ytb.manager.template.model.Dict_document.DOC_TYPE_XLS;
import static ytb.manager.template.model.Dict_document.SAVEMODE_DB;

public class DocumentServiceImpl implements IDocumentService {

    @Override
    public void previewImage(int documentId, HttpServletResponse res) throws IOException {
        Dict_document dict_document = ManagerSrvContext.getInst().getProjectTypeService().getMngrReDocument(documentId);
        String picType = dict_document.getPicType().trim();
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
        // 构造Image对象
        BufferedImage src = ImageIO.read(previewImage(documentId));
        ImageIO.write(src, picType, res.getOutputStream());
        try {
            res.getOutputStream().flush();
        } finally {
            res.getOutputStream().close();
        }
    }


    File previewImage(int documentId) throws IOException {
        Dict_document dict_document = ManagerSrvContext.getInst().getProjectTypeService().getMngrReDocument(documentId);
        if (dict_document.getSaveMode() == SAVEMODE_DB) {
            return previewImage_db(documentId);
        }
        return previewImage_path(documentId);
    }

    File previewImage_db(int documentId) throws IOException {

        Dict_document dict_document = ManagerSrvContext.getInst().getProjectTypeService().getMngrReDocument(documentId);
        if (dict_document == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        if (dict_document != null && dict_document.getDocument() == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        File temp = File.createTempFile("temp-talk-name", ".tmp");
        Files.write(dict_document.getDocument(), temp);
        //File f = new File("d:/1.jpg");
        //dict_document.setDocument(Files.toByteArray(f));
        //projectTypeService.modifyDocument(dict_document);
        return temp;
    }

    File previewImage_path(int documentId) throws IOException {

        Dict_document dict_document = ManagerSrvContext.getInst().getProjectTypeService().getMngrReDocument(documentId);
        if (dict_document == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        if (dict_document != null && dict_document.getDocPath() == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        return new File(dict_document.getDocPath());
    }


    @Override
    public String previewJson(int documentId) throws UnsupportedEncodingException {
        Dict_document dict_document = ManagerSrvContext.getInst().getProjectTypeService().getMngrReDocument(documentId);
        if (dict_document == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        if (dict_document != null && dict_document.getDocument() == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        return new String(dict_document.getDocument(), "UTF-8");
    }

    @Override
    public String getReDocumentHeader(int documentId) throws UnsupportedEncodingException {
        String ret = previewJson(documentId);
        JSONObject header = JSONObject.parseObject(ret).getJSONObject("header");
        return JSONObject.toJSONString(header);
    }

    @Override
    public void delReDocument(int documentId) {
        ManagerSrvContext.getInst().getProjectTypeService().delMngrReDocument(documentId);

    }

    @Override
    public void modifyReDocumentHeader (int documentId,  TemplateDocumentHeader tagDocumentHeader) throws UnsupportedEncodingException {

        Dict_document dict_document = ManagerSrvContext.getInst().getProjectTypeService().getMngrReDocument(documentId);
        if(dict_document==null) {
            YtbLog.logDebug("dict_document is null");
            return;
        }
        String doc = new String(dict_document.getDocument(), "utf-8");
        JSONObject jdoc = JSONObject.parseObject(doc);
        for (Map.Entry<String, Object> e :  tagDocumentHeader.toJSONObject().entrySet()) {
            if(e.getValue()!=null) {
                jdoc.getJSONObject("header").put(e.getKey(), e.getValue());
            }
        }
        dict_document.setDocument(JSONObject.toJSONString(jdoc).getBytes("utf-8"));
        ManagerSrvContext.getInst().getProjectTypeService().modifyDocument(dict_document);
    }

    @Override
    public void modifyReDocument(int documentId, MsgRequest req) throws UnsupportedEncodingException {
        Dict_document dict_document = ManagerSrvContext.getInst().getProjectTypeService().getMngrReDocument(documentId);
        String base64Str = req.msgBody.getString("document");
        String s = new String(Base64.getDecoder().decode(base64Str), "UTF-8");
        JSONObject docNewModelDocument = JSON.parseObject(s);
        docNewModelDocument.put("modify", true);
        byte[] document = JSON.toJSONString(docNewModelDocument).getBytes("UTF-8");
        dict_document.setDocument(document);
        ManagerSrvContext.getInst().getProjectTypeService().modifyDocument(dict_document);
    }

    @Override
    public int upload(RestHandler handler, MultipartFile mfile) throws Exception {
        MsgRequest req = handler.req;
        if (mfile == null) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR);
        }
        Dict_document dict_document = new Dict_document();
        if (req.msgBody.getInteger("saveMode") == null) {
            dict_document.setSaveMode(SAVEMODE_DB);
            dict_document.setDocType(Dict_document.DOC_TYPE_PIC);
            dict_document.setPicType("JPG");
        } else {
            dict_document.setSaveMode(req.msgBody.getInteger("saveMode"));
            dict_document.setDocType(req.msgBody.getInteger("docType"));
            dict_document.setPicType(req.msgBody.getString("picType"));
        }
        if (req.msgBody.getString("docPath") != null) {
            dict_document.setDocPath(req.msgBody.getString("docPath"));
        }
        String originalFilename = mfile.getOriginalFilename();
        dict_document.setName(originalFilename);
        dict_document.setPicType(originalFilename.substring(originalFilename.lastIndexOf(".") + 1));
        dict_document.setDocument(mfile.getBytes());
        dict_document.setDocSize(dict_document.getDocument().length);
        ManagerSrvContext.getInst().getProjectTypeService().addMngrReDocument(dict_document);
        handler.resp.msgBody.put("fileName", mfile.getOriginalFilename());
        return dict_document.getDocumentId();
    }


    @Override
    public int uploadTemplate(RestHandler handler, MultipartFile mfile) throws Exception {
        MsgRequest req = handler.req;
        if (mfile == null) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR);
        }
        Dict_document dict_document = new Dict_document();        //new Upload().uploadTemplate( null);
        if (req.msgBody.getInteger("saveMode") == null) {
            dict_document.setDocType(DOC_TYPE_XLS);
            dict_document.setSaveMode(SAVEMODE_DB);
            dict_document.setPicType("JPG");
        } else {
            dict_document.setSaveMode(req.msgBody.getInteger("saveMode"));
            dict_document.setDocType(req.msgBody.getInteger("docType"));
            dict_document.setPicType(req.msgBody.getString("picType"));
        }
        if (req.msgBody.getString("docPath") != null) {
            dict_document.setDocPath(req.msgBody.getString("docPath"));
        }
        //添加返回资源ID
        dict_document.setDocType(DOC_TYPE_XLS);
        dict_document.setSaveMode(SAVEMODE_DB);

        dict_document.setName(mfile.getOriginalFilename());
        dict_document.setDocument(mfile.getBytes());
        dict_document.setDocSize(dict_document.getDocument().length);
        ManagerSrvContext.getInst().getProjectTypeService().addMngrReDocument(dict_document);
        String templateId = req.msgBody.getString("templateId");

        if (templateId == null) {
            throw new YtbError(1000, "templateId must input");
        }
        Dict_TemplateModel dict_templateModel = ManagerSrvContext.getInst().getProjectTypeService().getTemplate(Integer.parseInt(templateId));
        //资源Id关联模板表
        dict_templateModel.setDocXls(dict_document.getDocumentId());
        dict_templateModel.setTitle(dict_document.getName());
        dict_templateModel.setDocumentId(0);
        ManagerSrvContext.getInst().getProjectTypeService().modifyTemplateDocument(dict_templateModel);
        handler.resp.msgBody.put("fileName", mfile.getOriginalFilename());
        return dict_document.getDocumentId();
    }

    public void download(int documentId, HttpServletResponse response) throws IOException {
        Dict_document dict_document = ManagerSrvContext.getInst().getProjectTypeService().getMngrReDocument(documentId);
        if (dict_document == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        File file = null;
        String fileName = dict_document.getName();
        if (dict_document.getSaveMode() == SAVEMODE_DB) {
            file = File.createTempFile("temp-talk-name", ".tmp");
            Files.write(dict_document.getDocument(), file);
        } else {
            file = new File(dict_document.getDocPath());
        }
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setCharacterEncoding("utf-8");
        // 将文件输入流写入response的输出流中
        IOUtil.copyCompletely(new FileInputStream(file), response.getOutputStream());

    }

    public List<Dict_TemplateModel> selectDocTemplates(Integer repositoryId, Integer workJobId, JSONArray docTypes) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ytb_manager.dict_template");
        sql.append(" where repository_id=#{repositoryId}");
        sql.append(" and work_job_id=#{workJobId}");
        sql.append(" and docType in (").append(StringUtil.join(docTypes.toArray(), ",")).append(")");
        Dict_TemplateModel tm = new Dict_TemplateModel();
        tm.setWorkJobId(workJobId);
        tm.setRepositoryId(repositoryId);

        return YtbContext.getSqlSessionBuilder().selectTable(sql, tm, Dict_TemplateModel.class);
    }

    public List<Dict_TemplateModel> getDocTemplate_gen(Integer docType) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            TemplateMapper tplMapper = session.getMapper(TemplateMapper.class);
            return tplMapper.getDocTemplate_gen(docType);
        } finally {
            session.close();
        }
    }

    public Dict_TemplateModel getTemplate(int templateId) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            TemplateMapper tplMapper = session.getMapper(TemplateMapper.class);
            return tplMapper.getTemplate(templateId);
        } finally {
            session.close();
        }
    }


    public Dict_document getMngrReDocument(int documentId) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            DocumentMapper docMapper = session.getMapper(DocumentMapper.class);
            return docMapper.getMngrReDocument(documentId);

        } finally {
            session.close();
        }
    }

    JSONObject transDocument(int documentId) {
        return (JSONObject) JSONObject.toJSON(getMngrReDocument(documentId));

    }

    JSONObject transDocumentString(int documentId) throws UnsupportedEncodingException {
        Dict_document dd = getMngrReDocument(documentId);
        String s = new String(dd.getDocument(), "UTF-8");
        JSONObject documentjson = (JSONObject) JSONObject.toJSON(dd);
        documentjson.fluentPut("document", s);
        return documentjson;
    }
}
