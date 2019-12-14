package ytb.project.view.daoservice;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import ytb.project.context.UserProjectContext;
import ytb.project.dao.CustomTaskMapper;
import ytb.project.dao.MeetingNoticeMapper;
import ytb.project.dao.ShipmentNumberMapper;
import ytb.project.daoservice.DAOService;
import ytb.project.model.*;
import ytb.project.view.dao.ITaskDAOService;
import ytb.project.view.model.ProjectTaskViewModel.ViewCustomTaskModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewMeetingNoticeModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewShipmentNumberModel;

import java.util.List;
import java.util.Map;

@Service
public class TaskDAOService extends DAOService implements ITaskDAOService {

    public int addCustomTask(UserProjectContext context, int type, int folderId, int templateId) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        CustomTaskModel customTaskModel = new CustomTaskModel();

        customTaskModel.setCustomType(type);//1其它自定义 2采购 3加工
        customTaskModel.setTaskFolder(folderId);
        customTaskModel.setTemplateId(templateId);


        customTaskModel.setUserId(ptm.getUserId2());
        customTaskModel.setProjectId(pm.getProjectId());
        customTaskModel.setTalkId(ptm.getTalkId());
        customTaskModel.setPhase(ptm.getPhase());
        customTaskModel.setReceiver("");
        customTaskModel.setRemark("");
        return  addCustomTask(customTaskModel);
    }

    @Override
    public int addCustomTask(CustomTaskModel customTask) {
        try (SqlSession session = getSession()) {
            CustomTaskMapper customTaskMapper = session.getMapper(CustomTaskMapper.class);
            customTaskMapper.addCustomTask(customTask);
            session.commit();
            return customTask.getCustomTaskId();
        }
    }

    @Override
    public void modifyCustomTask(int customTaskId, int newProjectId) {
        try (SqlSession session = getSession()) {
            CustomTaskMapper taskMapper = session.getMapper(CustomTaskMapper.class);
            taskMapper.modifyCustomTask(customTaskId, newProjectId);
            session.commit();
        }
    }

    @Override
    public List<Map<String, Object>> selectCustomTask(int projectId, int phase, int userId) {
        try (SqlSession session = getSession()) {

            CustomTaskMapper customTaskMapper = session.getMapper(CustomTaskMapper.class);
            return customTaskMapper.selectCustomTask(projectId, phase, userId);
        }
    }

    @Override
    public List<ViewCustomTaskModel> selectViewCustomTaskModel(int projectId, int phase, int userId) {
        try (SqlSession session = getSession()) {
            CustomTaskMapper customTaskMapper = session.getMapper(CustomTaskMapper.class);
            return customTaskMapper.selectViewCustomTaskModel(projectId, phase, userId);
        }
    }

    @Override
    public List<ViewCustomTaskModel> getCustomTaskList(int userId, int projectId) {
        try (SqlSession session = getSession()) {
            CustomTaskMapper customTaskMapper = session.getMapper(CustomTaskMapper.class);
            return customTaskMapper.getCustomTaskList(userId, projectId);
        }
    }

    //添加会议任务
    @Override
    public void addMeetingNotice(MeetingNoticeModel meetingNotice) {

        try (SqlSession session = getSession()) {
            MeetingNoticeMapper meetingNoticeMapper = session.getMapper(MeetingNoticeMapper.class);
            meetingNoticeMapper.addMeetingNotice(meetingNotice);
            session.commit();
        }
    }

    @Override
    public List<ViewMeetingNoticeModel> selectViewMeetingNoticeModel(int projectId, int phase, int userId) {
        try (SqlSession session = getSession()) {
            MeetingNoticeMapper meetingNoticeMapper = session.getMapper(MeetingNoticeMapper.class);
            return meetingNoticeMapper.selectViewMeetingNoticeModel(projectId, phase, userId);

        }
    }

    @Override
    public void addShipmnetNumber(ShipmentNumberModel shipmentNumber) {

        try (SqlSession session = getSession()) {
            ShipmentNumberMapper shipmentNumberMapper = session.getMapper(ShipmentNumberMapper.class);
            shipmentNumberMapper.addShipmnetNumber(shipmentNumber);
            session.commit();
        }

    }

    @Override
    public List<Map<String, Object>> selectShipNumber(int projectId, int phase, int userId) {
        try (SqlSession session = getSession()) {
            ShipmentNumberMapper shipmentNumberMapper = session.getMapper(ShipmentNumberMapper.class);
            return shipmentNumberMapper.selectShipNumber(projectId, phase, userId);

        }
    }

    @Override
    public List<ViewShipmentNumberModel> selectViewShipmentNumberModel(int projectId, int phase, int userId) {

        try (SqlSession session = getSession()) {
            ShipmentNumberMapper shipmentNumberMapper = session.getMapper(ShipmentNumberMapper.class);
            return shipmentNumberMapper.selectViewShipmentNumberModel(projectId, phase, userId);

        }
    }
}
