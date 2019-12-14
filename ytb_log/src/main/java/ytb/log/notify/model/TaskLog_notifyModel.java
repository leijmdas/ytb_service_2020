package ytb.log.notify.model;

import java.util.Date;

/**
 * Package: ytb.common.basic.activititemplate.model
 * Author: lzz
 * Date: Created in 2018/8/23 16:29
 */
public class TaskLog_notifyModel {

    //通知ID
    private int notifyId = 0;
    //通知方
    private int userId;
    //接收通知方
    private int toUserId;
    //通知模板参数json
    private String templateParam;
    //业务类型
    private int serviceType;
    //通知内容
    private String notify;
    //模板ID
    private int templateId;
    //通知时间
    private Date notifyTime =  new Date() ;
    //流程实例ID
    private String procId;
    //连接URL
    private String templateUrl="";

    public String getTemplateUrl() {
        return templateUrl;
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
    }

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public int getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(int notifyId) {
        this.notifyId = notifyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public String getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(String templateParam) {
        this.templateParam = templateParam;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getProcId() {
        return procId;
    }

    public void setProcId(String procId) {
        this.procId = procId;
    }
}
