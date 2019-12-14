package ytb.project.model.projectFolderView.templateFolder;

import ytb.project.common.ProjectConst;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.common.context.model.Ytb_Model;

import java.util.ArrayList;
import java.util.List;

//文件夹
public class TemplateFolderModel extends Ytb_Model {
    // PA:甲方
    // PB:乙方
    // PM:乙方成员

    String userType ;
    int companyId;
    int projectId;
    String projectName;
    int talkId;
    int phase;

    List<Integer> phaseList = new ArrayList<>();

    public List<Integer> getPhaseList() {
        return phaseList;
    }

    public void setPhaseList(List<Integer> phaseList) {
        this.phaseList = phaseList;
    }


    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getTalkId() {
        return talkId;
    }

    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void buildPhaseList(UserProjectContext context) {
        ProjectModel pm = context.getProjectModel();
        phaseList.add(ProjectConst.TalkPhase);

        if (phase >= pm.getPhaseStart()) {
            int end = phase <= pm.getPhaseEnd() ? phase : pm.getPhaseEnd();
            for (int iphase = pm.getPhaseStart(); iphase <= end; iphase++) {
                phaseList.add(iphase);
            }
        }
    }
//    public void buildPhaseList() {
//        if (phase == -1 || phase == 0) {
//            this.phaseList.add(0);
//        } else if (phase <= ProjectConst.TalkPhase) {
//            this.phaseList.add(ProjectConst.TalkPhase);
//        } else if (phase > ProjectConst.TalkPhase && phase < ProjectConst.Phase_START) {
//            this.phaseList.add(ProjectConst.TalkPhase);
//            this.phaseList.add(ProjectConst.Phase1);
//
//        } else if (phase <= ProjectConst.Phase_END) {
//            this.phaseList.add(ProjectConst.TalkPhase);
//            for (int s = ProjectConst.Phase1; s <= phase; s++) {
//                this.phaseList.add(ProjectConst.Phase1 + s-ProjectConst.Phase1);
//            }
//        } else if (phase > ProjectConst.Phase_END) {
//            this.phaseList.add(ProjectConst.TalkPhase);
//            for (int s = ProjectConst.Phase1; s < ProjectConst.Phase_END; s++) {
//                this.phaseList.add(ProjectConst.Phase1 + s-ProjectConst.Phase1);
//            }
//        }
//    }


}
