package ytb.manager.template.service;

import com.alibaba.fastjson.JSONArray;
import org.springframework.web.multipart.MultipartFile;

import ytb.manager.template.model.Dict_TemplateModel;
import ytb.manager.template.model.Dict_document;
import ytb.manager.templateexcel.model.xls.TemplateDocumentHeader;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;


public interface IDocumentService {

    void previewImage(int documentId, HttpServletResponse res) throws IOException;

    String previewJson(int documentId) throws UnsupportedEncodingException;

    void modifyReDocument(int documentId, MsgRequest req) throws UnsupportedEncodingException;

    void modifyReDocumentHeader(int documentId, TemplateDocumentHeader header) throws UnsupportedEncodingException;

    String getReDocumentHeader(int documentId) throws UnsupportedEncodingException;

    void delReDocument(int documentId);

    int upload(RestHandler handler, MultipartFile mfile) throws Exception;

    int uploadTemplate(RestHandler handler, MultipartFile mfile) throws Exception;

    void download(int documentId, HttpServletResponse res) throws IOException;

    //查询所 有通用模板文档 如终止，需求变更等
    List<Dict_TemplateModel> getDocTemplate_gen(Integer docType) ;

    List<Dict_TemplateModel> selectDocTemplates(Integer repositoryId, Integer workJobId, JSONArray docTypes) ;

    Dict_TemplateModel getTemplate(int templateId);

    Dict_document getMngrReDocument(int documentId);


}

