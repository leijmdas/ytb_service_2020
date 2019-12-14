package ytb.manager.templateexcel.model.xls;

import com.alibaba.fastjson.JSONObject;
import ytb.manager.template.model.Dict_Req_Item;
import ytb.manager.templateexcel.model.tag.Tag;

import java.util.List;

/**
 * 文档模板内容表中document字段模型
 */
public class TemplateDocumentModel {

    private TemplateDocumentHeader header;

    private List<Tag> body;

    private List<Dict_Req_Item> reqItemList;

    private JSONObject tagTableParam;

    private JSONObject tagTableParamRadio;

    private JSONObject tagTableParamCheck;

    //存配有REST接口的tagTable数据
    private JSONObject tagTableRestDataList = new JSONObject();

    /**
     * 是否被修改过
     * 动态增删目录功能需要此属性
     * 前端页面第一次编辑初始化时,会对可点击动态增加目录的tagTaxt进行自身节点的拷贝,
     * 再次编辑文档时,不会对可点击动态增加目录的tagTaxt进行自身节点的拷贝,就是根据此属性来判断是否需要进行自身节点的拷贝
     */
    private boolean modify;

    public TemplateDocumentModel() {
    }

    public TemplateDocumentHeader getHeader() {
        return header;
    }

    public void setHeader(TemplateDocumentHeader header) {
        this.header = header;
    }

    public List<Tag> getBody() {
        return body;
    }

    public void setBody(List<Tag> body) {
        this.body = body;
    }

    public List<Dict_Req_Item> getReqItemList() {
        return reqItemList;
    }

    public void setReqItemList(List<Dict_Req_Item> reqItemList) {
        this.reqItemList = reqItemList;
    }

    public JSONObject getTagTableParam() {
        return tagTableParam;
    }

    public void setTagTableParam(JSONObject tagTableParam) {
        this.tagTableParam = tagTableParam;
    }

    public boolean isModify() {
        return modify;
    }

    public void setModify(boolean modify) {
        this.modify = modify;
    }

    public JSONObject getTagTableRestDataList() {
        return tagTableRestDataList;
    }

    public void setTagTableRestDataList(JSONObject tagTableRestDataList) {
        this.tagTableRestDataList = tagTableRestDataList;
    }

    public JSONObject getTagTableParamRadio() {
        return tagTableParamRadio;
    }

    public void setTagTableParamRadio(JSONObject tagTableParamRadio) {
        this.tagTableParamRadio = tagTableParamRadio;
    }

    public JSONObject getTagTableParamCheck() {
        return tagTableParamCheck;
    }

    public void setTagTableParamCheck(JSONObject tagTableParamCheck) {
        this.tagTableParamCheck = tagTableParamCheck;
    }
}
