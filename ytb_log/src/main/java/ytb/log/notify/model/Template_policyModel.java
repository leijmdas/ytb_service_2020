package ytb.log.notify.model;

import java.util.Date;

/**
 * Package: ytb.common.basic.activititemplate.model
 * Author: lzz
 * Date: Created in 2018/8/23 16:29
 */
public class Template_policyModel {

    //策略ID
    private int templateId ;
    //策略类型
    private int templateType ;
    //策略名称
    private String templateName;
    //任务内容类别
    private  int objectType;
    //创建时间
    private Date createTime =  new Date() ;
    //模板策略类
    private String templateClass;
    //受理页面URL
    private String pageUrl;
    //创建人
    private int createBy;


    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public int getTemplateType() {
        return templateType;
    }

    public void setTemplateType(int templateType) {
        this.templateType = templateType;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTemplateClass() {
        return templateClass;
    }

    public void setTemplateClass(String templateClass) {
        this.templateClass = templateClass;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }
}
