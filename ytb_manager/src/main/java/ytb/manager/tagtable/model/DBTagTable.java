package ytb.manager.tagtable.model;

import com.alibaba.fastjson.JSONObject;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentInfo;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.context.model.Ytb_Model;

import java.io.IOException;
import java.util.List;


public class DBTagTable extends Ytb_Model {
    public static DBTagTable parseTagTable(String info) {
        return JSONObject.parseObject(info, DBTagTable.class);
    }

    public static DBTagTable parseTagTable(TemplateDocumentInfo documentInfo, String tableName) throws IOException {

        JSONObject value = documentInfo.getTagTableByTableName(documentInfo, tableName);
        return parseTagTable(JSONObject.toJSONString(value));
//        if (tagTable == null && documentInfo.getDocJson().getJSONObject("tagTableParam") != null) {
//            tagTable = parseTagTable(JSONObject.toJSONString(documentInfo.getDocJson().getJSONObject("tagTableParam")));
//        }
    }

    TableDict tableDict;
    List<TagField> tagTitle;
    List<DBTagValue> value;
    String tagType;
    String key;

    boolean show;
    int rowLimit;
    int rows;
    int depth;
    String refString;
    int conentStart;

    public String toString(){
        return MsgHandler.toJSONStringPrettyWriteMapNullValue(this);
    }
    public List<TagField> getTagTitle() {
        return tagTitle;
    }

    public void setTagTitle(List<TagField> tagTitle) {
        this.tagTitle = tagTitle;
    }

    public List<DBTagValue> getValue() {
        return value;
    }

    public void setValue(List<DBTagValue> value) {
        this.value = value;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public int getRowLimit() {
        return rowLimit;
    }

    public void setRowLimit(int rowLimit) {
        this.rowLimit = rowLimit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getRefString() {
        return refString;
    }

    public void setRefString(String refString) {
        this.refString = refString;
    }

    public int getConentStart() {
        return conentStart;
    }

    public void setConentStart(int conentStart) {
        this.conentStart = conentStart;
    }


    public TableDict getTableDict() {
        return tableDict;
    }

    public void setTableDict(TableDict tableDict) {
        this.tableDict = tableDict;
    }


    public static class TableDict {
        String dbName;
        String tableName;
        public String getDbName() {
            return dbName;
        }

        public void setDbName(String dbName) {
            this.dbName = dbName;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

    }


    public static class TagField extends Ytb_Model {
        String fieldName;
        String fieldType;
        String fieldMemo;
        String text;
        String refField;
        String refValue;

        public String toString(){
            return JSONObject.toJSONString(this);
        }

        public String getRefValue() {
            return refValue;
        }

        public void setRefValue(String refValue) {
            this.refValue = refValue;
        }

        public String getRefField() {
            return refField;
        }

        public void setRefField(String refField) {
            this.refField = refField;
        }



        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldType() {
            return fieldType;
        }

        public void setFieldType(String fieldType) {
            this.fieldType = fieldType;
        }

        public String getFieldMemo() {
            return fieldMemo;
        }

        public void setFieldMemo(String fieldMemo) {
            this.fieldMemo = fieldMemo;
        }
    }

    public static class DBTagValue  extends Ytb_Model {

        List<TagField> items;

        public String findRefValeOrText(String fieldName) {
            for (TagField item : items) {
                if (item.getRefField() != null && item.getRefField().equals(fieldName)) {
                    return item.getRefValue();
                }
                if (item.getFieldName() != null && item.getFieldName().equals(fieldName)) {
                    return item.getText();
                }
            }

            return "";
        }

        public List<TagField> getItems() {
            return items;
        }

        public void setItems(List<TagField> items) {
            this.items = items;
        }


        public String toString(){
            return MsgHandler.toJSONStringPrettyWriteMapNullValue(this);
        }
    }

}




