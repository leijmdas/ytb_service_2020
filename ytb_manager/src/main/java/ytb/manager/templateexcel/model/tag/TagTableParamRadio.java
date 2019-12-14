package ytb.manager.templateexcel.model.tag;

import ytb.manager.templateexcel.model.tag.impl.TableDict;

/**
 * @ClassName TagTableParamRadio
 * @Description TODO
 * @Author qsy
 * @Date 2019/4/10 18:19
 **/
public class TagTableParamRadio extends TagRadio {

    //tagTableParamRadio  字段名称
    private String fieldName;

    //表
    private TableDict tableDict;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public TableDict getTableDict() {
        return tableDict;
    }

    public void setTableDict(TableDict tableDict) {
        this.tableDict = tableDict;
    }
}
