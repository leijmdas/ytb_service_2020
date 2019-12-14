package ytb.bangbang.dao;

import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.GroupChatFile;

import java.util.List;
import java.util.Map;

public interface GroupChatFileDao {

    //添加文件
    int addFile(GroupChatFile file);

    //删除文件
    int delFile(@Param("id") Integer id);

    //获取文件
    List<GroupChatFile> getFilelist(Map<String,Object> map);
}
