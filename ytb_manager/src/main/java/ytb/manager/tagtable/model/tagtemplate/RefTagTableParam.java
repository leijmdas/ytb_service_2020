package ytb.manager.tagtable.model.tagtemplate;

// tagTagleParamRef@project.project_name$A1.11
import com.alibaba.fastjson.JSONObject;
import ytb.manager.templateexcel.model.xls.TemplateDocumentHeader;

public class RefTagTableParam extends TemplateDocumentHeader {
    private String tag;
    private Integer functionId;

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }


    public static RefTagTableParam parse(String msgBody) {
        return JSONObject.parseObject(msgBody, RefTagTableParam.class);
    }


    public Integer getRepositoryId() {
        return repositoryId==null?0:repositoryId;
    }

    public void setRepositoryId(Integer repositoryId) {
        this.repositoryId = repositoryId;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }



}
