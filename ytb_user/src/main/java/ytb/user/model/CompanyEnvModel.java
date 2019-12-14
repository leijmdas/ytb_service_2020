package ytb.user.model;

/**
 * 公司单位环境照片
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/9/10 10:31
 */
public class CompanyEnvModel {
    //表主键
    private int envId = 0;

    //公司ID
    private int companyId =0;

    //图片类型
    private int imageType ;

    //图片
    private String workImage = "";

    //描述
    private String lifeImage = "";

    public int getEnvId() {
        return envId;
    }

    public void setEnvId(int envId) {
        this.envId = envId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getImageType() {
        return imageType;
    }

    public void setImageType(int imageType) {
        this.imageType = imageType;
    }
    public String getWorkImage() {
        return workImage;
    }

    public void setWorkImage(String workImage) {
        this.workImage = workImage;
    }

    public String getLifeImage() {
        return lifeImage;
    }

    public void setLifeImage(String lifeImage) {
        this.lifeImage = lifeImage;
    }
}
