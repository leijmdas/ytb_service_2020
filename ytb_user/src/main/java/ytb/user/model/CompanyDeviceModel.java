package ytb.user.model;

/**
 * 公司设备表
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/9/10 10:22
 */
public class CompanyDeviceModel {
    //表主键
    private int deviceId = 0;

    //公司信息ID
    private int companyId = 0;

    //设备名称
    private String name ="";

    //设备型号
    private String model = "";

    //设备品牌
    private String brand = "";

    //设备数量
    private int number = 0;

    //图片
    private String image = "";


    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
