package ytb.manager.templateexcel.model.tag;

import ytb.manager.templateexcel.model.tag.impl.TableDict;

/**
 * @ClassName TagTableParamCheck
 * @Description TODO
 * @Author 123
 * @Date 2019/4/15 10:29
 **/
public class TagTableParamCheck extends TagCheck {

    //表字段名
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
