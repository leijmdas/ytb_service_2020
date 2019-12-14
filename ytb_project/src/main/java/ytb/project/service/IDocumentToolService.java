package ytb.project.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import ytb.project.context.UserProjectContext;
import ytb.project.dao.ProjectDocumentMapper;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
/*
* leijianming
*
* 20181024
* */
//doc_type	文档类型格式
//        1 word
//        2 jsonmodel
//        3 ppt
//        4 json
//        5 xml
//        6 pic
//        7 rar
//        save_mode	保存方式	
//        1	文件，按路径存取
//        2表，按字段存取
public interface IDocumentToolService extends ProjectDocumentMapper {

    public int DOC_TYPE_WORD = 1;
    public int DOC_TYPE_XLS = 2;
    public int DOC_TYPE_PPT = 3;
    public int DOC_TYPE_JSON = 4;
    public int DOC_TYPE_XML = 5;
    public int DOC_TYPE_PIC = 6;
    public int DOC_TYPE_RAR = 7;

    public int SAVEMODE_PATH = 1;//文件，按路径存取
    public int SAVEMODE_DB = 2;  //表，按字段存取

    int addJson(String document) throws UnsupportedEncodingException;

    int modifyJson(UserProjectContext context,int projectId ,int documentId, String document) throws UnsupportedEncodingException;

    int modifyJsonHeader( UserProjectContext context,int projectId, int documentId, JSONObject req) throws UnsupportedEncodingException;

    String getJsonHeader(int documentId) throws UnsupportedEncodingException;

    String previewJson(int documentId) throws UnsupportedEncodingException;

    String previewJson(Integer v, int documentId) throws UnsupportedEncodingException;

    int addImage(MsgRequest req, HttpServletRequest request) throws IOException;

    int modifyImage(int documentId, HttpServletRequest request) throws IOException;

    void previewImage(int documentId, HttpServletResponse res) throws IOException;

    int uploadTemplate(MsgHandler handler, MultipartFile mfile) throws Exception;

    int uploadBinary(MsgHandler handler, MultipartFile multipartFile) throws Exception;

    void download(int documentId, HttpServletResponse response) throws IOException;

}
