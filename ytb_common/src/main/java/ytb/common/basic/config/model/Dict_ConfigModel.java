package ytb.common.basic.config.model;

import com.alibaba.excel.annotation.ExcelProperty;
import ytb.common.context.model.Ytb_Model;

public class Dict_ConfigModel extends Ytb_Model {

    //子系统标识号
    @ExcelProperty(value = "子系统标识号", index = 0)
    private int configId;
    //
    private int configType;
    //子系统名称
    private String configItem;

    private String configRemark;

    private String configValue;


    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }

    public int getConfigType() {
        return configType;
    }

    public void setConfigType(int configType) {
        this.configType = configType;
    }

    public String getConfigItem() {
        return configItem;
    }

    public void setConfigItem(String configItem) {
        this.configItem = configItem;
    }

    public String getConfigRemark() {
        return configRemark;
    }

    public void setConfigRemark(String configRemark) {
        this.configRemark = configRemark;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}
