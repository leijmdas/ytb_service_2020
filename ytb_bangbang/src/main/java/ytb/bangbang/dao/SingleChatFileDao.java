package ytb.bangbang.dao;

import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.SingleChatFile;

import java.util.List;
import java.util.Map;

public interface SingleChatFileDao {

    //添加文件
    int addFile(SingleChatFile file);

    //删除文件
    int delFile(@Param("id") Integer id);

    //查询文件
    List<SingleChatFile> getFilelist(Map<String,Object> map);
}
