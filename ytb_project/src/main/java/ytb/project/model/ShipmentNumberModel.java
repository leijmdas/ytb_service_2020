package ytb.project.model;

import ytb.common.context.model.Ytb_ModelSkipNull;

public class ShipmentNumberModel  extends Ytb_ModelSkipNull {


    private int shipmentNumberId;
    //物流单号
    private String number;
    //物品名称
    private String goodsName;
    //项目id
    private int projectId;
    //文档名称
    private String documentName;
    //阶段id
    private int phase;
    //备注
    private String remark;
    //发送方
    private int userId;
    //接收方
    private int toUserId;


    public int getShipmentNumberId() {
        return shipmentNumberId;
    }

    public void setShipmentNumberId(int shipmentNumberId) {
        this.shipmentNumberId = shipmentNumberId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
