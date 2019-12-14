package ytb.project.service.impl.talk;

import ytb.common.context.model.YtbError;
import ytb.common.context.model.Ytb_Model;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.tagtable.ProjectMemberDutyModel;
import ytb.project.model.tagtable.ProjectMemberTask;
import ytb.project.model.tagtable.WorkGroupMemberModel;

import java.util.List;

public final class MemberDutyTaskService extends WorkGroup {
    public static class MemberDutyInfo extends Ytb_Model {

        int userId;

        String userName;
        boolean hasPurchase;
        boolean hasProcess;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public boolean isHasPurchase() {
            return hasPurchase;
        }

        public void setHasPurchase(boolean hasPurchase) {
            this.hasPurchase = hasPurchase;
        }

        public boolean isHasProcess() {
            return hasProcess;
        }

        public void setHasProcess(boolean hasProcess) {
            this.hasProcess = hasProcess;
        }

    }

    public MemberDutyInfo checkExistDutyPP(UserProjectContext context) {
        ProjectTalkModel ptm = context.getProjectTalkModel();

        MemberDutyInfo dutyInfo = new MemberDutyInfo();

        dutyInfo.setUserId(context.getUserId());
        dutyInfo.setUserName(context.getLoginSso().getLoginSsoJson().getNick_name());

        List<WorkGroupMemberModel> memberModels = getWorkGroupMember(ptm.getGroupId(), dutyInfo.getUserId());
        if (memberModels.size() > 0) {
            int memberId = memberModels.get(0).getMemberId();
            List<ProjectMemberDutyModel> dutyModels = getProjectMemberDuty(memberId);
            for (ProjectMemberDutyModel dutyModel : dutyModels) {
                List<ProjectMemberTask> tasks = getProjectMemberTasks(dutyModel.getMemberDutyId());
                for (ProjectMemberTask task : tasks) {
                    if (task.getTaskName().contains("加工")) {
                        dutyInfo.setHasProcess(true);
                    }
                    if (task.getTaskName().contains("采购")) {
                        dutyInfo.setHasPurchase(true);
                    }
                    if (dutyInfo.isHasProcess() && dutyInfo.isHasPurchase()) {
                        return dutyInfo;
                    }
                }
            }
            return dutyInfo;
        }
        throw new YtbError(YtbError.CODE_DEFINE_ERROR, "用户不是组员！");

    }
}
