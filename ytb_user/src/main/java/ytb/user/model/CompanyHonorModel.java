package ytb.user.model;

import java.util.Date;

/**
 * 公司获得的荣誉
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/9/6 19:57
 */
public class CompanyHonorModel {

    private Integer honorId = 0;
    private Integer companyId = 0;
    private String honorName ="";
    private Date honorDate = new Date();
    private String organizeName ="";
    private String image ="";
    private Integer honorType = 0;

    public Integer getHonorId() {
        return honorId;
    }

    public void setHonorId(Integer honorId) {
        this.honorId = honorId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getHonorName() {
        return honorName;
    }

    public void setHonorName(String honorName) {
        this.honorName = honorName;
    }

    public Date getHonorDate() {
        return honorDate;
    }

    public void setHonorDate(Date honorDate) {
        this.honorDate = honorDate;
    }

    public String getOrganizeName() {
        return organizeName;
    }

    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getHonorType() {
        return honorType;
    }

    public void setHonorType(Integer honorType) {
        this.honorType = honorType;
    }
}
