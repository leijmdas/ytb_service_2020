package ytb.project.dao;


import org.apache.ibatis.annotations.Param;
import ytb.project.model.MeetingNoticeModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewMeetingNoticeModel;

import java.util.List;

public interface MeetingNoticeMapper {
    //新增会议通知
    void addMeetingNotice(MeetingNoticeModel meetingNotice);

    //查看会议通知
    List<ViewMeetingNoticeModel>  selectViewMeetingNoticeModel(@Param("projectId") int projectId, @Param("phase") int phase, @Param("userId") int userId);
}
