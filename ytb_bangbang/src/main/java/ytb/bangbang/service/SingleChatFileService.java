package ytb.bangbang.service;

import ytb.bangbang.model.SingleChatFile;

import java.util.List;
import java.util.Map;

/**
 * tanc
 */

public interface SingleChatFileService {

    //添加文件
    int addFile(SingleChatFile file);

    //删除文件
    int delFile(Integer id);

    //查询文件
    List<SingleChatFile> getFilelist(Map<String,Object> map);
}
