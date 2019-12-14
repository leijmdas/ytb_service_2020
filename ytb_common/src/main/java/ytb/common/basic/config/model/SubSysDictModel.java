package ytb.common.basic.config.model;

import ytb.common.context.model.Ytb_Model;

public class SubSysDictModel extends Ytb_Model {

    //子系统标识号
    private int subsysId;
    //
    private String subsysNo;
    //子系统名称
    private String subsysName;

    private String remark;

    private String ip;

    private int port;


    public int getSubsysId() {
        return subsysId;
    }

    public void setSubsysId(int subsysId) {
        this.subsysId = subsysId;
    }

    public String getSubsysNo() {
        return subsysNo;
    }

    public void setSubsysNo(String subsysNo) {
        this.subsysNo = subsysNo;
    }

    public String getSubsysName() {
        return subsysName;
    }

    public void setSubsysName(String subsysName) {
        this.subsysName = subsysName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
