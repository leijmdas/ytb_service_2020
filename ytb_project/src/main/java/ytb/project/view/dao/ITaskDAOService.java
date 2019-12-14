package ytb.project.view.dao;

import ytb.project.context.UserProjectContext;
import ytb.project.dao.CustomTaskMapper;
import ytb.project.dao.MeetingNoticeMapper;
import ytb.project.dao.ShipmentNumberMapper;

public interface ITaskDAOService extends CustomTaskMapper, ShipmentNumberMapper, MeetingNoticeMapper {
    int addCustomTask(UserProjectContext context, int type, int folderId, int templateId);


}
