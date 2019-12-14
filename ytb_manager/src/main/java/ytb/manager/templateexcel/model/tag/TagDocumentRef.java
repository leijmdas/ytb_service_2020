package ytb.manager.templateexcel.model.tag;

import java.util.List;

/**
 * @author lxz 2019/1/18 14:35
 */
public class TagDocumentRef extends Tag {

    private List<String> refDocNameList;

    public List<String> getRefDocNameList() {
        return refDocNameList;
    }

    public void setRefDocNameList(List<String> refDocNameList) {
        this.refDocNameList = refDocNameList;
    }
}
