package ytb.user.service;

import org.springframework.web.multipart.MultipartFile;
import ytb.user.dao.UserImgMapper;
import ytb.common.RestMessage.MsgRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException; ;

public interface IImageService extends UserImgMapper {

    public int DOC_TYPE_WORD = 1;
    public int DOC_TYPE_XLS = 2;
    public int DOC_TYPE_PPT = 3;
    public int DOC_TYPE_JSON = 4;
    public int DOC_TYPE_XML = 5;
    public int DOC_TYPE_PIC = 6;
    public int DOC_TYPE_RAR = 7;

    public int SAVEMODE_PATH = 1;//文件，按路径存取
    public int SAVEMODE_DB = 2;  //表，按字段存取

    void deleteUserImg(int imgId);
    void previewImage(int documentId, HttpServletResponse res) throws IOException;
    int upload(MsgRequest req, MultipartFile mfile) throws Exception ;

    void download(int documentId, HttpServletResponse res) throws IOException;

}
