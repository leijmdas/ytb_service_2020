package ytb.account.wallet.model;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class ProjectTrade implements Serializable {
    private Integer projectTradeId;

    private Integer userId;

    private Integer companyId;

    private Integer projectId;

    private Integer talkId;

    private Short phase;

    private Integer parentTradeId;

    private Integer tradeId;

    private Date updateTime;

    private Byte serviceType;

    private static final long serialVersionUID = 1L;

    public Integer getProjectTradeId() {
        return projectTradeId;
    }

    public void setProjectTradeId(Integer projectTradeId) {
        this.projectTradeId = projectTradeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTalkId() {
        return talkId;
    }

    public void setTalkId(Integer talkId) {
        this.talkId = talkId;
    }

    public Short getPhase() {
        return phase;
    }

    public void setPhase(Short phase) {
        this.phase = phase;
    }

    public Integer getParentTradeId() {
        return parentTradeId;
    }

    public void setParentTradeId(Integer parentTradeId) {
        this.parentTradeId = parentTradeId;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getServiceType() {
        return serviceType;
    }

    public void setServiceType(Byte serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", projectTradeId=").append(projectTradeId);
        sb.append(", userId=").append(userId);
        sb.append(", companyId=").append(companyId);
        sb.append(", projectId=").append(projectId);
        sb.append(", talkId=").append(talkId);
        sb.append(", phase=").append(phase);
        sb.append(", parentTradeId=").append(parentTradeId);
        sb.append(", tradeId=").append(tradeId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serviceType=").append(serviceType);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}