package ytb.common.basic.safelog.model;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;

import java.sql.Timestamp;
import java.util.Date;

public class Tasklog_UserModel {
    //create table tasklog_user

    public int getTasklogId() {
        return tasklogId;
    }

    public void setTasklogId(int tasklogId) {
        this.tasklogId = tasklogId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public short getOprtType() {
        return oprtType;
    }

    public void setOprtType(short oprtType) {
        this.oprtType = oprtType;
    }

    public String getOprtName() {
        return oprtName;
    }

    public void setOprtName(String oprtName) {
        this.oprtName = oprtName;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public Date getOprtDate() {
        return oprtDate;
    }

    public void setOprtDate(Date oprtDate) {
        this.oprtDate = oprtDate;
    }

    public Timestamp getOprtTime() {
        return oprtTime;
    }

    public void setOprtTime(Timestamp oprtTime) {
        this.oprtTime = oprtTime;
    }

    int tasklogId;
    long userId;
    String userIp;
    short oprtType;

    public long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(long seqNo) {
        this.seqNo = seqNo;
    }

    long seqNo;

    String oprtName;
    int retCode;
    Date oprtDate;
    Timestamp oprtTime;




    public String toString(){
        return YtbUtils.toJSONStringPretty(this);
    }
}
