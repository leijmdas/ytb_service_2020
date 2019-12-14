package ytb.manager.tagtable.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ytb.manager.templateexcel.model.xls.TemplateDocumentHeader;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class DBReqItemList {
    TemplateDocumentHeader header;
    List<DBReqItem> reqItemList;

    public TemplateDocumentHeader getHeader() {
        return header;
    }

    public void setHeader(TemplateDocumentHeader header) {
        this.header = header;
    }


    public List<DBReqItem> getReqItemList() {
        return reqItemList;
    }

    public void setReqItemList(List<DBReqItem> reqItemList) {
        this.reqItemList = reqItemList;
    }


    //  "header": {
//        "docType": 100,
//                "documentId": 377,
//                "projectId": 0,
//                "version": "1.0"
//    },
//            "reqItemList": [
//    {
//        "itemId": 318,
//            "reqItemDesc": "含PCBA电路板设计",
//            "reqItemNo": "A",
//            "templateId": 199,
//            "value": 1,
//            "workJobId": 0
//    },
    public static DBReqItemList parseDBReqItemList(byte[] document) throws UnsupportedEncodingException {

        //String doc = MsgHandler.Base64toStr(document);
        JSONObject json = JSONObject.parseObject(new String (document,"UTF-8"));
        JSONArray jsona = json.getJSONArray("reqItemList");
        //System.out.println(jsona);
        return JSONObject.parseObject(JSONObject.toJSONString(json), DBReqItemList.class);
    }


    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
