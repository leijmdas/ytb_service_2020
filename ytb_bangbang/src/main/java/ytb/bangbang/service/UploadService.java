package ytb.bangbang.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface UploadService {
    /**
     * 上传文件
     * @param request
     * @param file
     * @param rootPath
     * @return
     */
    Map<String,String> upload(HttpServletRequest request, MultipartFile file,String rootPath);
    /**
     * 下载文件
     * @param downloadfileDirectory   文件内容路径
     * @param resp      下载resp
     */
    void fileDownloadByPath(String downloadfileDirectory, HttpServletResponse resp,HttpServletRequest request,String fileName);

    /**
     * 预览图片
     * @param documentId
     * @param res
     * @throws IOException
     */
    void previewImage(int documentId, HttpServletResponse res) throws IOException;
}
