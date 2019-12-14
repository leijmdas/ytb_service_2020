package ytb.log.smslog.model;

import java.util.Date;

/**
 * Package: ytb.common.basic.activititemplate.model
 * Author: lzz
 * Date: Created in 2018/8/23 16:29
 */
public class Tasklog_SmsModel {

    //短信id
    private int smsId;
    //通知方
    private int userId;
    //接收方 手机号
    private String mobile;
    //短信内容
    private String content;
    //短信模板ID
    private int templateId;
    //发送时间
    private Date sendTime;
    //发送次数
    private int retryTimes;
    //结果码
    private int retCode;
    //发送错误的原因
    private String errorMsg;
    //更新时间
    private Date  createTime = new Date();


    public int getSmsId() {
        return smsId;
    }

    public void setSmsId(int smsId) {
        this.smsId = smsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
