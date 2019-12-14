package ytb.project.view.model;

import ytb.common.context.model.Ytb_ModelSkipNull;

/**
 * Package: ytb.project.view.model
 * Author: ZCS
 * Date: Created in 2019/4/4 16:07
 * Copyright: Copyright (c) 2018
 */
public class ProjectChangeResult  extends Ytb_ModelSkipNull {
    //旧洽谈ID
    private int oldTalkId;

    //新项目ID（变更之后生成的）
    private int newProjectId;

    //新洽谈ID
    private int newTalkId;

    //变更类型
    private int changeType;

    //阶段
    private int phase;


    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getChangeType() {
        return changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    public int getOldTalkId() {
        return oldTalkId;
    }

    public void setOldTalkId(int oldTalkId) {
        this.oldTalkId = oldTalkId;
    }

    public int getNewTalkId() {
        return newTalkId;
    }

    public void setNewTalkId(int newTalkId) {
        this.newTalkId = newTalkId;
    }

    public int getNewProjectId() {
        return newProjectId;
    }

    public void setNewProjectId(int newProjectId) {
        this.newProjectId = newProjectId;
    }
}
