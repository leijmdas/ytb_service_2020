package ytb.manager.tagtable.model.tagtemplate;

import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TagTableRaramResult extends Ytb_ModelSkipNull {

    private String resultType ;

    //固定单值
    private String value;

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    private Boolean visible;

    //0 1 或者多个。0则不显示
    private List<Map<String, Object>> list = new ArrayList<>();

    public List<Map<String, Object>> getTable() {
        return table;
    }

    public void setTable(List<Map<String, Object>> table) {
        this.table = table;
    }

    private List<Map<String, Object>> table  ;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    public String getResultType() {
        resultType = value != null ? "value" : "list";
        resultType = table != null ? "table" : resultType;

        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }


}
