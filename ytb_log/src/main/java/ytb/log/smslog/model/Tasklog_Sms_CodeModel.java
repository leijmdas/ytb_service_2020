package ytb.log.smslog.model;

import java.util.Date;

/**
 * Package: ytb.common.basic.activititemplate.model
 * Author: lzz
 * Date: Created in 2018/8/23 16:29
 */
public class Tasklog_Sms_CodeModel {


    //用户id
    private int userId;
    //短信id
    private int smsId;
    //发送时间
    private Date sendTime;
    //失效时间
    private Date endTime;
    //短信验证码
    private String smsCode;
    private String phone;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSmsId() {
        return smsId;
    }

    public void setSmsId(int smsId) {
        this.smsId = smsId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
