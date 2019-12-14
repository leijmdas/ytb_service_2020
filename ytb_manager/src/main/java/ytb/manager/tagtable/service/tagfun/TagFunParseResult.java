package ytb.manager.tagtable.service.tagfun;


import ytb.manager.tagtable.model.tagtemplate.TagTableRaramResult;
import ytb.manager.tagtable.model.tagtemplate.Tag_FunctionModel;

import java.util.List;
import java.util.Map;

public class TagFunParseResult {
    public TagTableRaramResult parseResult(List<Map<String, Object>> lst, Tag_FunctionModel m) {
        if (m.getRetKey().equals(Tag_FunctionModel.RET_KEY_VALUE)) {
            return parseResult2Value(lst, m);
        }
        if (m.getRetKey().equals(Tag_FunctionModel.RET_KEY_LIST)) {
            return parseResult2List(lst, m);
        }
        return parseResult2Table(lst, m);
    }


    TagTableRaramResult parseResult2Value(List<Map<String, Object>> lst, Tag_FunctionModel m){
        TagTableRaramResult tagResult = new TagTableRaramResult();
        tagResult.setVisible(true);
        tagResult.setList(null);
        tagResult.setValue(lst.size() > 0 ? lst.get(0).get("name").toString() : "");
        return tagResult;
    }
    TagTableRaramResult parseResult2List(List<Map<String, Object>> lst, Tag_FunctionModel m){
        TagTableRaramResult tagResult = new TagTableRaramResult();
        tagResult.setVisible(true);
        tagResult.setValue(null);
        tagResult.setList( lst );
        return tagResult;
    }

    TagTableRaramResult parseResult2Table(List<Map<String, Object>> lst, Tag_FunctionModel m){
        TagTableRaramResult tagResult = new TagTableRaramResult();
        tagResult.setVisible(true);
        tagResult.setValue(null);
        tagResult.setList( null );
        tagResult.setTable(lst);
        return tagResult;
    }
}
