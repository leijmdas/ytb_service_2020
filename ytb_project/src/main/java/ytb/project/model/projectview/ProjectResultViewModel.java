package ytb.project.model.projectview;

import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Package: ytb.project.model.projectview
 * Author: ZCS
 * Date: Created in 2019/2/14 15:40
 */
public class ProjectResultViewModel extends Ytb_ModelSkipNull {

    //发布的项目
    private List<ProjectViewModel> projectList = new ArrayList<>();

    //洽谈的项目
    private List<ProjectTalkViewModel> talkList = new ArrayList<>();

    //协助的的项目
    private List<ProjectAssistViewModel> assitList = new ArrayList<>();



    public List getAssitList() {
        return assitList;
    }

    public void setAssitList(List assitList) {
        this.assitList = assitList;
    }

    public List<ProjectTalkViewModel> getTalkList() {
        return talkList;
    }

    public void setTalkList(List<ProjectTalkViewModel> talkList) {
        this.talkList = talkList;
    }

    public List<ProjectViewModel> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectViewModel> projectList) {
        this.projectList = projectList;
    }
}
//    //阶段个数
//    private int phaseNo ;
//    //第一个阶段 601  604   一定是200
//    private int phaseStart ;
//    //最后一个阶段  洽谈一定是200
//    private int phaseEnd ;
//    //当前阶段
//    private int phase ;