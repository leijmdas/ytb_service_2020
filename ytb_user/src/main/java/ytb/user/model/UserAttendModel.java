package ytb.user.model;

import java.util.Date;

/**
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/12/18 19:03
 * Company: 公司
 */
public class UserAttendModel {
    //表主键
    private Integer attendId = 0;

    //关注者ID
    private Integer companyId = 0;

    //关注者ID
    private Integer userId = 0;

    //被关注者ID
    private Integer attendObjId = 0;

    //被关注的类别(1:个人用户  2:公司用户)
    private Integer attendObjType = 1;

    //关注的时间
    private Date attendTime = new Date();


    public Integer getAttendId() {
        return attendId;
    }

    public void setAttendId(Integer attendId) {
        this.attendId = attendId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAttendObjId() {
        return attendObjId;
    }

    public void setAttendObjId(Integer attendObjId) {
        this.attendObjId = attendObjId;
    }

    public Integer getAttendObjType() {
        return attendObjType;
    }

    public void setAttendObjType(Integer attendObjType) {
        this.attendObjType = attendObjType;
    }

    public Date getAttendTime() {
        return attendTime;
    }

    public void setAttendTime(Date attendTime) {
        this.attendTime = attendTime;
    }
}
