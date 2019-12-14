package ytb.manager.templateexcel.model.tag.impl.value;

import ytb.manager.templateexcel.model.HtmlComponent.Metadata;

import java.util.List;

/**
 * LXZ
 */
public class TagFileValueDefault extends TagValue{

    private List<Metadata> items;

    public TagFileValueDefault() {
    }

    public List<Metadata> getItems() {
        return items;
    }

    public void setItems(List<Metadata> items) {
        this.items = items;
    }
}
