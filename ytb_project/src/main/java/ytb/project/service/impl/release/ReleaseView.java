package ytb.project.service.impl.release;

import com.alibaba.fastjson.JSONArray;
import ytb.project.common.ProjectConst;
import ytb.project.common.ProjectConstState;
import ytb.project.context.*;
import ytb.project.model.*;
import ytb.project.model.projectview.ProjectAssistViewModel;
import ytb.project.model.projectview.ProjectResultViewModel;
import ytb.project.model.projectview.ProjectTalkViewModel;
import ytb.project.model.projectview.ProjectViewModel;
import ytb.project.model.tagtable.ProjectMemberDutyModel;
import ytb.project.model.tagtable.ProjectMemberTask;
import ytb.project.service.IReleaseView;
import ytb.manager.template.model.Dict_WorkJobModel;
import ytb.user.model.UserInfoModel;
import ytb.common.context.model.YtbError;
import java.util.*;


public final class ReleaseView extends ReleaseService implements IReleaseView {

    @Override
    public ProjectResultViewModel getProjectLists(UserProjectContext context, Integer status) {
        ProjectResultViewModel resultView = new ProjectResultViewModel();
        //查询协助的项目
        List<ProjectAssistViewModel> assistViewModels =  getProjectAssistList(context.getUserId());
        resultView.setAssitList(assistViewModels);

        // 发布中
        if (status == ProjectConstState.PUBLISH_PASS) {//4
            List<ProjectViewModel> projectList = getProjectDAOService().getProjectList(context, status);
            resultView.setProjectList(projectList);

        }
        // 未发布
        else if (status == ProjectConstState.PUBLISH_DRAFT) {//1

            List<ProjectViewModel> projectList = getProjectDAOService().getProjectList(context, status);
            resultView.setProjectList(projectList);

        } else if (status == ProjectConstState.PROJECT_ALL) {//所有项目:0

            List<ProjectViewModel> projectList = getProjectDAOService().getProjectList(context, status);
            resultView.setProjectList(projectList);

            List<ProjectTalkViewModel> talkLst = getProjectDAOService().getProjectTalkList(context,
                    ProjectConstState.PROJECT_ALL, ProjectConstState.PROJECT_ALL,status);
            resultView.setTalkList(talkLst);

        }
        // 查询洽谈（200）、进行中（600）、已完成的项目（900）
        else if (status == ProjectConst.TalkPhase
                || status == ProjectConst.Phase_START
                || status == ProjectConstState.CHNAGE_TYPE_FINISH) {

            int phaseStart = status;
            int phaseEnd = status;
            if (status == ProjectConst.TalkPhase) {
                phaseEnd = ProjectConstState.TalkPhase;
            } else if (status == ProjectConst.Phase_START) {
                phaseEnd = ProjectConst.Phase_END;
            } else if (status == ProjectConstState.CHNAGE_TYPE_FINISH) {
                phaseStart = ProjectConst.Phase_START;
                phaseEnd = ProjectConst.Phase_END;
            }
            List<ProjectTalkViewModel> talkViewModels = getProjectDAOService().getProjectTalkList(context,
                    phaseStart, phaseEnd, status);
            resultView.setTalkList(talkViewModels);

        } else {
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "status");
        }

        return resultView;
    }

    public List<Map<String, Object>> getFinishProjectView(int userId, int projectId) {
        JSONArray jsonArray = new JSONArray();
        JSONArray json = new JSONArray();
        List<Map<String, Object>> list = getProjectDAOService().getFinishProjectView(userId, projectId);
        List<ProjectMemberDutyModel> pmDutyList = ProjectTalkModel.getWorkGroup().getProjectMemberDuty((Integer) list.get(0).get("memberId"));
        for (ProjectMemberDutyModel dutyModel : pmDutyList) {
             Dict_WorkJobModel workJobModel = getInst().getProjectCacheManager().findDictWorkJobModel(dutyModel.getWorkJobId());

            json.add(workJobModel.getTitle());
            List<ProjectMemberTask> projectMemberTaskList = ProjectTalkModel.getWorkGroup().getProjectMemberTasks(dutyModel.getMemberDutyId());
            for (ProjectMemberTask projectMemberTask : projectMemberTaskList) {
                jsonArray.add(projectMemberTask.getTaskName());
            }
        }
        list.get(0).put("workJob", json);
        list.get(0).put("tasks", jsonArray);
        return list;
    }

    //通过公司查询项目
    public List<Map<String, Object>> getProjectListOfCompany(Map map) {

        Integer status = Integer.valueOf(map.get("status").toString());
        if (status.equals(ProjectConstState.PUBLISH_PASS)) {//发布中
            return projectDAOService.getReleaseProjectList(map);

        } else if (status.equals(ProjectConst.TalkPhase)) {//洽谈中
            return projectDAOService.selectProjectTalk(map);

        } else if (status.equals(ProjectConstState.CHNAGE_TYPE_FINISH)) {//已完成项目
            return projectDAOService.getProjectByChangeStatus(map);

        } else if (status.equals(ProjectConstState.CHNAGE_TYPE_TALK_TERM)) {//洽谈终止
            return projectDAOService.getProjectByChangeStatus(map);

        } else if (status.equals(ProjectConstState.CHNAGE_TYPE_STOP)) { //项目终止
            return projectDAOService.getProjectByChangeStatus(map);

        } else if (status.equals(ProjectConst.Phase_START)) {//进行中
            return projectDAOService.selectProjectRun(map);

        }
        else if (status.equals(0)) {
            List<Map<String, Object>> projectModels = projectDAOService.getProjectsByCompany(map);
            for (Map list : projectModels) {
                if (list.get("leadName") == null || "".equals(list.get("leadName"))) {
                    UserInfoModel userLoginModel = getInst().getUserCenterService().getUserInfoById((Integer) list.get("userId"));//获取用户信息
                    if (userLoginModel != null) {
                        list.put("leadName", userLoginModel.getNickName());
                        list.put("groupName", userLoginModel.getNickName());
                    }
                }
            }
            return projectModels;
        }
        throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "status");

    }



    public void addViews(int projectId) {
        getProjectDAOService().modifyViewNumber(projectId);
    }

    public void addFavorite(UserProjectContext context, int projectId, int status) {
        if (status == 1) {
            getProjectDAOService().modifyFavorite(projectId);
        } else {
            getProjectDAOService().cancelFavorite(projectId);
        }
    }
}