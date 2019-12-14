package ytb.bangbang.dao;

import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.GroupNoticeModel;

import java.util.List;

public interface GroupNoticeDao {
    //发布公告
    int addGroupNotice(GroupNoticeModel groupNoticeModel);
    //读取公告
    List<GroupNoticeModel> getGroupNotice(@Param("groupId")int groupId);
    //删除公告
    int  deleteGroupNotice(@Param("noticeId")int noticeId);
}
