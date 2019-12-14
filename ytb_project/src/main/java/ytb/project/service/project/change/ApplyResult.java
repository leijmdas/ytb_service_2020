package ytb.project.service.project.change;

import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.common.context.model.Ytb_Model;
import ytb.project.model.ProjectTalkModel;

public class ApplyResult extends Ytb_Model {

    boolean reApply;

    String nickName;
    String companyName;

    String projectName;
    int projectId;
    int talkId;
    int phase;

    public ApplyResult(UserProjectContext context){
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        setProjectName(pm.getProjectName());
        setProjectId(pm.getProjectId());
        setTalkId(ptm.getTalkId());
        setPhase(ptm.getPhase());
    }


    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getTalkId() {
        return talkId;
    }

    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    public boolean isReApply() {
        return reApply;
    }

    public void setReApply(boolean reApply) {
        this.reApply = reApply;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


}
