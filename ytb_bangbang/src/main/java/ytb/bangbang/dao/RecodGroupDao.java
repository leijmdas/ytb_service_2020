package ytb.bangbang.dao;

import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.Record_GroupModel;

import java.util.List;
import java.util.Map;

public interface RecodGroupDao {
    /*
     * 功能： 获取群组的聊天记录信息
     * 参数：
     */
     List<Record_GroupModel> GetRecordGroupList(@Param("groupId") int groupId);

    /**
     * 添加聊天记录信息
     * @param groupId
     * @param fromId
     * @return
     */
     int AddRecordGroup(@Param("groupId") int groupId,@Param("content") String content,@Param("fromId")int fromId);


    List<Record_GroupModel> getRecordGroupList(Map<String,Object> map);

    /**
     * 删除群聊记录
     * @param recordId
     * @return
     */
    int delGroupFile(@Param("recordId") int recordId);

    int getFromUserById(@Param("recordId") int recordId);
}
