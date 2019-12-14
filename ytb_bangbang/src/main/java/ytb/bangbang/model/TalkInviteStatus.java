package ytb.bangbang.model;

import com.google.gson.Gson;
import java.io.Serializable;

/**
 * @author 
 */
public class TalkInviteStatus implements Serializable {
    /**
     * 洽谈邀请标识
     */
    private Integer inviteId;

    /**
     * 项目标识
     */
    private Integer projectId;

    /**
     * 文档标识
     */
    private Integer documentId;

    /**
     * 用户标识
     */
    private Integer userId;

    /**
     * 邀请状态：0 null要显示未邀请/1邀请中/2已邀请/3已拒绝
     */
    private Byte status;

    private static final long serialVersionUID = 1L;

    public Integer getInviteId() {
        return inviteId;
    }

    public void setInviteId(Integer inviteId) {
        this.inviteId = inviteId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", inviteId=").append(inviteId);
        sb.append(", projectId=").append(projectId);
        sb.append(", documentId=").append(documentId);
        sb.append(", userId=").append(userId);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}