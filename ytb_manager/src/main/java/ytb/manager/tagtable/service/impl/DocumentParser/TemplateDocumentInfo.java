package ytb.manager.tagtable.service.impl.DocumentParser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ytb.manager.tagtable.model.DBTagTable;
import ytb.manager.templateexcel.model.xls.TemplateDocumentHeader;
import ytb.common.context.model.YtbError;
import ytb.common.context.model.Ytb_Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TemplateDocumentInfo extends Ytb_Model {

    final static String TAG_tagTitleRed = "tagTitleRed";
    final static String TAG_tagTable = "tagTable";
    final static String TAG_tagTableSum = "tagTableSum";
    final static String TAG_tagTableParam = "tagTableParam";

    transient JSONObject docJson;
    TemplateDocumentHeader dBDocHeader;
    DBTagTable tagTableParam;

    public String getCmdType() {
        return cmdType;
    }

    public void setCmdType(String cmdType) {
        this.cmdType = cmdType;
    }

    transient String cmdType;
    //是否甲方
    boolean isPa = false;
    //导出表时是否检查费用表记录
    boolean checkCostFlag = false ;

    public boolean getCheckCostFlag() {
        return checkCostFlag;
    }

    public void setCheckCostFlag(boolean checkCostFlag) {
        this.checkCostFlag = checkCostFlag;
    }

    public Boolean isPa() {
        return isPa;
    }

    public void setPa(Boolean pa) {
        isPa = pa;
    }


    public boolean isTemplateReq() {
        return getdBDocHeader().checkTemplateReqTrue();
    }

    public boolean isTemplateWorkplan() {
        return getdBDocHeader().checkTemplateWorkplanTrue();
    }

    public boolean isTemplate_TYPE_workplan_NonePp() {
        return getdBDocHeader().checkTemplate_TYPE_workplan_NonePpTrue();
    }

    public boolean isTemplate_TYPE_testReport() {

        return getdBDocHeader().checkTemplate_TYPE_testReportTrue();

    }

    public TemplateDocumentInfo parseHeader() throws IOException {
        JSONObject header = docJson.getJSONObject("header");
        dBDocHeader = JSON.parseObject(JSONObject.toJSONString(header), TemplateDocumentHeader.class);
        return this;
    }

    public List<String> listTagTableKey(boolean includeTableParam) {
        List<String> results = new ArrayList<>();

        JSONArray body = docJson.getJSONArray("body");
        for (int i = 0; i < body.size(); i++) {
            readAllTagTableKey(body.getJSONObject(i), results);
        }

        if (includeTableParam && docJson.getJSONObject("tagTableParam") != null
                && docJson.getJSONObject("tagTableParam").getJSONObject("tableDict") != null) {
            results.add("@" + docJson.getJSONObject("tagTableParam").getJSONObject("tableDict").getString("tableName"));
        }
        return results;
    }

    public List<String> listTagTableName() throws IOException {
        return listTagTableName(true);
    }

    public List<String> listTagTableName(boolean includeTableParam) throws IOException {
        List<String> lst = listTagTableKey(includeTableParam);
        List<String> lstTableName = new ArrayList<>();
        for (String key : lst) {
            String ss[] = key.split("@|\\$");
            lstTableName.add(ss[1]);
        }
        return lstTableName;
    }


    public List<JSONObject> listTagTitleRed () {
        List<JSONObject> results = new ArrayList<>();
        JSONArray body = docJson.getJSONArray("body");
        for (int i = 0; i < body.size(); i++) {
           if( readTagTitleRed_isFinish(body.getJSONObject(i), results) ){
               break;
           }
        }
        return results;
    }


    public JSONObject getTagTableByTableName(TemplateDocumentInfo documentInfo, String tableName) {
        List<JSONObject> list = listTagTable();
        for (JSONObject e : list) {
            String key = e.getString("key");
            String[] s = key.split("@|\\$");
            if (tableName.equals(s[1])) {
                return e;
            }
        }

        if ( documentInfo.getDocJson().getJSONObject("tagTableParam") != null) {
            return documentInfo.getDocJson().getJSONObject("tagTableParam");
        }

        throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD,tableName);
    }

    public List<JSONObject> listTagTable() {
        List<JSONObject> results = new ArrayList<>();
        JSONArray body = docJson.getJSONArray("body");
        for (int i = 0; i < body.size(); i++) {
            readAllTagTable(body.getJSONObject(i), results);
        }
        return results;
    }



    public DBTagTable parseTagTableParam() {
        tagTableParam = null;
        if(docJson.getJSONObject(TAG_tagTableParam)!=null){
            tagTableParam = DBTagTable.parseTagTable(JSONObject.toJSONString(docJson.getJSONObject(TAG_tagTableParam)));
        }
        if(tagTableParam==null){
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD,"标签关联参数表不存在！");
        }

        return tagTableParam;
    }

    public boolean readTagTitleRed_isFinish(JSONObject tag, List<JSONObject> results) {
        String tagType = tag.getString("tagType");
        if (tagType != null && tagType.equalsIgnoreCase(TAG_tagTitleRed)) {
            return true;
        }
        results.add(tag);
        return false;
    }


    public void readAllTagTableKey(JSONObject tag, List<String> results) {
        String key = tag.getString("key");
        if (key != null && (key.startsWith(TAG_tagTable + "@")
                || key.startsWith(TAG_tagTableSum + "@"))) {
            results.add(key);
            return;
        }
        JSONArray value = tag.getJSONArray("value");
        if (value != null && value.size() > 0) {
            for (int i = 0; i < value.size(); i++) {
                JSONObject child = value.getJSONObject(i);
                if (child.containsKey("tagType")) {
                    readAllTagTableKey(child, results);
                }
            }
        }
    }

    public void readAllTagTable(JSONObject tag, List<JSONObject> results) {
        String key = tag.getString("key");
        if (key != null && (key.startsWith(TAG_tagTable + "@")
                || key.startsWith(TAG_tagTableSum + "@"))) {
            results.add(tag);
            return;
        }
        JSONArray value = tag.getJSONArray("value");
        if (value != null && value.size() > 0) {
            for (int i = 0; i < value.size(); i++) {
                JSONObject child = value.getJSONObject(i);
                if (child.containsKey("tagType")) {
                    readAllTagTable(child, results);
                }
            }
        }
    }


    public JSONObject getDocJson() {
        return docJson;
    }

    public void setDocJson(JSONObject docJson) {
        this.docJson = docJson;
    }

    public TemplateDocumentHeader getdBDocHeader() {
        return dBDocHeader;
    }

    public void setdBDocHeader(TemplateDocumentHeader dBDocHeader) {
        this.dBDocHeader = dBDocHeader;
    }


}
