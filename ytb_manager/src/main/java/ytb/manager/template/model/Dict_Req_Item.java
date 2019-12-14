package ytb.manager.template.model;

import com.alibaba.fastjson.JSONObject;

import javax.xml.bind.util.JAXBSource;

public class Dict_Req_Item {
    //需求因子id
    private int itemId;
    //需求模板ID
    private int templateId;
    //需求模板标题
    private String title;
    //需求因子编号
    private String reqItemNo;
    //需求因子描述
    private String reqItemDesc;
    //取值
    private int value;
    //关联岗位ID
    private int workJobId;
    //内容
    private String item;

    //关联岗位名称
    private String workJobName;
    //需求说明书名称
    private String templateName;


    public Dict_Req_Item() {
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWorkJobId() {
        return workJobId;
    }

    public void setWorkJobId(int workJobId) {
        this.workJobId = workJobId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getWorkJobName() {
        return workJobName;
    }

    public void setWorkJobName(String workJobName) {
        this.workJobName = workJobName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String toString(){
        return JSONObject.toJSONString(this);
    }
}

