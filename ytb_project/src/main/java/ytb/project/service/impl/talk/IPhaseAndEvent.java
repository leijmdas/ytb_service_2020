package ytb.project.service.impl.talk;

import ytb.project.context.UserProjectContext;
import ytb.project.dao.ProjectEventMapper;
import ytb.project.model.ProjectEventViewModel;
import ytb.project.model.ProjectEventModel;
import ytb.project.model.ProjectPhaseModel;
import ytb.project.service.impl.pay.payevent.PayEventModel;

import java.util.List;
import java.util.Map;

public interface IPhaseAndEvent extends ProjectEventMapper {

    //添加项目事件
    int addTalkEvent(ProjectEventModel projectEvent);

    ProjectEventModel addEvent(UserProjectContext context, PayEventModel payEvent);
    ProjectEventModel addEventAssist(UserProjectContext context, PayEventModel payEvent);
    ProjectEventModel addPayEvent(UserProjectContext context, PayEventModel payEvent);

    ProjectPhaseModel getProjectPhaseAutoAdd(UserProjectContext context);

    ProjectPhaseModel getProjectPhaseByProjectId(int projectId, int phase);

    ProjectPhaseModel getProjectPhase(int talkId,int phase);


    //添加项目阶段
    int addProjectPhase(ProjectPhaseModel projectPhase);


    List<ProjectEventViewModel> selectPayEventViewModel(int userId, int talkId, int phase);

    List<Map<String, Object>> selectProEventByPhaseStatus(int projectId, int userId, int phaseStatus);

    List<ProjectEventViewModel> selectProEventViewModelByPhaseStatus(int projectId, int userId, int phaseStatus);

    List<ProjectEventViewModel> selectProjectEventViewModelByUserId(int projectId, int phase,int userId, int phaseStatus);

    //获取项目阶段事件
    List<Map<String, Object>> getProjectEvent(Map map);

    //获取项目事件
    List<ProjectEventViewModel> selectProjectEventViewModel(int projectId, int eventType, int phase, int userId);

    List<Map<String, Object>> selectProjectEventByP(int projectId, int phase);

    List<Map<String, Object>> getBeforeTalkEvent(Map map);

}
