

package ytb.manager.template.model;

/**
 * 需求书岗位真值约束MODEL
 */
public class Dict_WorkJob_Check {
    //真值约束id
    private int checkId;
    //需求模板ID
    private int templateId;
    //需求因子项ID
    private int reqItemId;
    //因子取值
    private int itemValue;
    //岗位ID
    private int workJobId;

    //模板名称
    private String templateName;

    //需求因子编号
    private String reqItemNo;

    //需求因子描述
    private String reqItemDesc;

    //岗位名称
    private String workJobName;

    public Dict_WorkJob_Check() {
    }

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public int getReqItemId() {
        return reqItemId;
    }

    public void setReqItemId(int reqItemId) {
        this.reqItemId = reqItemId;
    }

    public int getItemValue() {
        return itemValue;
    }

    public void setItemValue(int itemValue) {
        this.itemValue = itemValue;
    }

    public int getWorkJobId() {
        return workJobId;
    }

    public void setWorkJobId(int workJobId) {
        this.workJobId = workJobId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getReqItemNo() {
        return reqItemNo;
    }

    public void setReqItemNo(String reqItemNo) {
        this.reqItemNo = reqItemNo;
    }

    public String getReqItemDesc() {
        return reqItemDesc;
    }

    public void setReqItemDesc(String reqItemDesc) {
        this.reqItemDesc = reqItemDesc;
    }

    public String getWorkJobName() {
        return workJobName;
    }

    public void setWorkJobName(String workJobName) {
        this.workJobName = workJobName;
    }
}
