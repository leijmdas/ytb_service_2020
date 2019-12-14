package ytb.manager.templateexcel.model.tag.impl.value;



import ytb.manager.templateexcel.model.HtmlComponent.Metadata;

import java.util.List;
import java.util.Map;

/**
 * LXZ
 */
public class TagRadioValueDefault extends TagValue {

    //Radio or Text
    private List<Metadata> items;


    //引用信息
    private String ref;

    //map key为TagRadioValueDefault某个单选按钮的索引值,value为A(含PCBA电路板设计)
    private Map<String, String> map;

    public TagRadioValueDefault() {
    }

    public List<Metadata> getItems() {
        return items;
    }

    public void setItems(List<Metadata> items) {
        this.items = items;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

}
