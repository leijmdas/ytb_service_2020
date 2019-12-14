package ytb.user.model;

import java.util.Date;

/**
 * Package: ytb.manager.pfUser.model
 * <p>
 * Description： TODO
 * <p>
 * Author: ZCS
 * <p>
 * Date: Created in 2018/12/6 10:24
 * <p>
 * Company: 公司
 * <p>
 * Copyright: Copyright (c) 2018
 * <p>
 * Version: 0.0.1
 * <p>
 * Modified By:
 */
public class UserBlackModel {
    //表主键
    private Integer userId =0;

    //近黑名单时间
    private Date inBlackTime;

    //出黑名单时间
    private Date outBlackTime;

    //类型（0黑名单  1白名单）
    private Integer listType = 0;

    //类型（1个人 2公司）
    private Integer userType =1;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getInBlackTime() {
        return inBlackTime;
    }

    public void setInBlackTime(Date inBlackTime) {
        this.inBlackTime = inBlackTime;
    }

    public Date getOutBlackTime() {
        return outBlackTime;
    }

    public void setOutBlackTime(Date outBlackTime) {
        this.outBlackTime = outBlackTime;
    }

    public Integer getListType() {
        return listType;
    }

    public void setListType(Integer listType) {
        this.listType = listType;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
