package ytb.manager.charges.model;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * @author
 */
public class DictArea implements Serializable {
    /**
     * 地区标识
     */
    private Integer areaId;

    /**
     * 父节点
     */
    private Integer parentId;

    /**
     * 1-省、直辖市
     * 2-市、区
     * 区、县
     */
    private Byte depthType;

    /**
     * 界面录入
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    private List<DictAreaSalary> dictAreaSalaryList;

    public List<DictAreaSalary> getDictAreaSalaryList() {
        return dictAreaSalaryList;
    }

    public void setDictAreaSalaryList(List<DictAreaSalary> dictAreaSalaryList) {
        this.dictAreaSalaryList = dictAreaSalaryList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Byte getDepthType() {
        return depthType;
    }

    public void setDepthType(Byte depthType) {
        this.depthType = depthType;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", areaId=").append(areaId);
        sb.append(", parentId=").append(parentId);
        sb.append(", depthType=").append(depthType);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}