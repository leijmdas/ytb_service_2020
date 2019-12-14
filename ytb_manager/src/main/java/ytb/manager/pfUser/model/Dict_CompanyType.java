package ytb.manager.pfUser.model;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Dict_CompanyType {

    int companyType;
    int parentId;

    String remark;

    public int getCompanyType() {
        return companyType;
    }

    public void setCompanyType(int companyType) {
        this.companyType = companyType;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Dict_CompanyType> getChildren() {
        return children;
    }

    public void setChildren(List<Dict_CompanyType> children) {
        this.children = children;
    }

    List<Dict_CompanyType> children=new ArrayList<>();

}
