package ytb.manager.metadata.model;

import com.google.gson.Gson;
import java.io.Serializable;

/**
 * @author 
 */
public class SubsysDict implements Serializable {
    /**
     * 子系统标识号
     */
    private Integer subsysId;

    private String subsysNo;
    /**
     * 子系统名称
     */
    private String subsysName;

    /**
     * 子系统
     */
    private String remark;

    private String ip;

    private Short port;

    private static final long serialVersionUID = 1L;

    public Integer getSubsysId() {
        return subsysId;
    }

    public void setSubsysId(Integer subsysId) {
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

    public Short getPort() {
        return port;
    }

    public void setPort(Short port) {
        this.port = port;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", subsysId=").append(subsysId);
        sb.append(", subsysNo=").append(subsysNo);
        sb.append(", subsysName=").append(subsysName);
        sb.append(", remark=").append(remark);
        sb.append(", ip=").append(ip);
        sb.append(", port=").append(port);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}