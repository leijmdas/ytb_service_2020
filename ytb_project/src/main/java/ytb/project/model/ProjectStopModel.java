package ytb.project.model;

import ytb.common.context.model.Ytb_Model;

/**
 * Package: ytb.project.model
 * Author: ZCS
 * Date: Created in 2019/4/25 20:03
 */
public class ProjectStopModel  extends Ytb_Model {
    //提出方
    private int partyBy ;
    //提出方ID
    private int userId;
    //當前階段提出
    private int nowPhase;

    public int getPartyBy() {
        return partyBy;
    }

    public void setPartyBy(int partyBy) {
        this.partyBy = partyBy;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNowPhase() {
        return nowPhase;
    }

    public void setNowPhase(int nowPhase) {
        this.nowPhase = nowPhase;
    }
}
