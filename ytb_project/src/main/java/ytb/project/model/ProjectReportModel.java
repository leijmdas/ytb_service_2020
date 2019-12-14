package ytb.project.model;

import ytb.common.context.model.Ytb_Model;

import java.util.Date;

/**
 * Package: ytb.project.model
 * Author: ZCS
 * Date: Created in 2018/12/24 14:21
 * Company: 公司
 */
public class ProjectReportModel extends Ytb_Model {
    private Integer reportId = 0;

    //举报类别（1发布垃圾广告、2发布虚假信息、3发布敏感信息、4发布色情信息）
    private String reportType = "";

    //举报人
    private Integer userId = 0;

    private Integer companyId = 0;

    //被举报人
    private Integer toUserId = 0;

    //被举报公司
    private Integer toCompanyId = 0;

    //备注
    private String remark = "";

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Integer status = 0;

    //时间
    private Date createTime = new Date();

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String report_type) {
        this.reportType = report_type;
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

    public void setCompanyId(Integer compantId) {
        this.companyId = compantId;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public Integer getToCompanyId() {
        return toCompanyId;
    }

    public void setToCompanyId(Integer toCompanyId) {
        this.toCompanyId = toCompanyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
