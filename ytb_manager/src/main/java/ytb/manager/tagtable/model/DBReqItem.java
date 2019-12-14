package ytb.manager.tagtable.model;

import com.alibaba.fastjson.JSONObject;

public class DBReqItem {
    int itemId;
    String reqItemDesc;//"含PCBA电路板设计"
    String reqItemNo; //A
    int templateId;//199
    int value;//1

    int workJobId;//0

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getReqItemDesc() {
        return reqItemDesc;
    }

    public void setReqItemDesc(String reqItemDesc) {
        this.reqItemDesc = reqItemDesc;
    }

    public String getReqItemNo() {
        return reqItemNo;
    }

    public void setReqItemNo(String reqItemNo) {
        this.reqItemNo = reqItemNo;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
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

    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
