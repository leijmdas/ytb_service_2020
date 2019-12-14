package ytb.bangbang.service;

import ytb.bangbang.model.GroupChatFile;

import java.util.List;
import java.util.Map;

/**
 * tanc
 */
public interface GroupChatFileService {

    //添加文件
    int addFile(GroupChatFile file);

    //删除文件
    int delFile(Integer id);

    //获取文件
    List<GroupChatFile> getFilelist(Map<String,Object> map);
}
