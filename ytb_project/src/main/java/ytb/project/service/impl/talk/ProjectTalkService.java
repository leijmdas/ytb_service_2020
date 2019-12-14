package ytb.project.service.impl.talk;

import ytb.project.common.ProjectConst;
import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.ProjectTalkDAOService;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.tagtable.ProjectMemberTask;

import static ytb.project.model.ProjectTalkModel.getWorkGroup;

public final class ProjectTalkService extends ProjectTalkDAOService {

    public ProjectMemberTask getPhaseTaskMFlow(int projectId) {
        return getWorkGroup().getPhaseTask("添加物流单号", projectId);
    }


    //修改洽谈状态
    public void modifyTalkPhasePayDate(UserProjectContext context, int phase, int phaseStatus) {
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ptm.setPhase(phase);
        ptm.setPhaseStatus(phaseStatus);
        modifyTalkPhasePayDate(ptm);

    }

    //修改洽谈状态
    public void modifyTalkPhaseAndStatus(UserProjectContext context, int phase) {
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ptm.setPhaseStatus(ProjectConst.TalkPhase_STATUS);
        modifyTalkPhaseAndStatus(phase,ptm.getPhaseStatus(),null,ptm.getTalkId());

    }
    //修改洽谈状态
    public void modifyTalkChangeStatus(UserProjectContext context, int changeStatus) {
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ptm.setPhaseStatus(ProjectConst.TalkPhase_STATUS);
        modifyTalkPhaseAndStatus(ptm.getPhase(),ptm.getPhaseStatus(),changeStatus,ptm.getTalkId());

    }

}
