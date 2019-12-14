package ytb.log.notify.model;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * Package: ytb.common.basic.activititemplate.model
 * Author: lzz
 * Date: Created in 2018/8/23 16:29
 */
public class Template_notifyModel {

    //模板ID
    private int templateId ;
    //模板类型
    private Integer templateType ;
    //模板内容
    private String template;
    //Ali模板参数
    private  String aliSmsTemplate;
    //创建时间
    private Date createTime =  new Date() ;
    //模板名称
    private String templateName;
    //消息通知渠道
    private int notifyChannel;
    //通知类型
    private int notifyType;
    //创建人
    private int createBy;

    private int paramCount;

    private String templateUrl;

    public String getTemplateUrl() {
        return templateUrl;
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getAliSmsTemplate() {
        return aliSmsTemplate;
    }

    public void setAliSmsTemplate(String aliSmsTemplate) {
        this.aliSmsTemplate = aliSmsTemplate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public int getNotifyChannel() {
        return notifyChannel;
    }

    public void setNotifyChannel(int notifyChannel) {
        this.notifyChannel = notifyChannel;
    }

    public int getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(int notifyType) {
        this.notifyType = notifyType;
    }

    public int getParamCount() {
        return paramCount;
    }

    public void setParamCount(int paramCount) {
        this.paramCount = paramCount;
    }

    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
