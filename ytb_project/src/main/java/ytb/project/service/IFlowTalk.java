package ytb.project.service;

import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.ProjectTemplateModel;
import ytb.project.service.impl.flow.FlowTalk;
import ytb.common.context.model.Ytb_ModelSkipNull;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IFlowTalk {
    public static class SubmitTalkResult extends Ytb_ModelSkipNull {

        Integer projectId;
        Integer talkId;
        Integer phase;
        Integer phaseStatus;

        public Integer getChangeStatus() {
            return changeStatus;
        }

        public void setChangeStatus(Integer changeStatus) {
            this.changeStatus = changeStatus;
        }

        Integer changeStatus;

        public SubmitTalkResult(UserProjectContext context) {
            ProjectTalkModel ptm = context.getProjectTalkModel();
            IFlowTalk.SubmitTalkResult result = this;
            result.setProjectId(ptm.getProjectId());
            result.setTalkId(ptm.getTalkId());
            result.setPhase(ptm.getPhase());
            result.setPhaseStatus(ptm.getPhaseStatus());

        }

        public Integer getProjectId() {
            return projectId;
        }

        public void setProjectId(Integer projectId) {
            this.projectId = projectId;
        }

        public Integer getTalkId() {
            return talkId;
        }

        public void setTalkId(Integer talkId) {
            this.talkId = talkId;
        }

        public Integer getPhaseStatus() {
            return phaseStatus;
        }

        public void setPhaseStatus(Integer phaseStatus) {
            this.phaseStatus = phaseStatus;
        }

        public Integer getPhase() {
            return phase;
        }

        public void setPhase(Integer phase) {
            this.phase = phase;
        }


    }

    //乙方提交洽谈文档
    FlowTalk.SubmitTalkResult pbTalkSubmitTemplate(UserProjectContext context) throws IOException;

    //甲方审核洽谈文档
    List<ProjectTemplateModel> paTalkAuditTemplate(UserProjectContext context, int status);


    //申请洽谈终止
    FlowTalk.ApplyTalkEndResult applyTalkTerm(UserProjectContext context, int talkId);

    //洽谈中止
    FlowTalk.SubmitTalkResult talkTerm (UserProjectContext context,  int talkId, String notify, String remark) throws UnsupportedEncodingException;

}
