package ytb.manager.template.model;

import ytb.manager.pfUser.model.Dict_CompanyType;

import java.util.ArrayList;
import java.util.List;

public class Dict_Prj_Config {
    Integer configId;

    Integer projectType;
    String code;
    String name;
    String scene;
    int valueType;
    String value;
    String remark;

    public Integer getProjectType() {
        return projectType;
    }

    public void setProjectType(Integer projectType) {
        this.projectType = projectType;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public int getValueType() {
        return valueType;
    }

    public void setValueType(int value_type) {
        this.valueType = value_type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
