package ytb.manager.templateexcel.model.tag.impl.value;


import ytb.manager.templateexcel.model.HtmlComponent.TextBox;

import java.util.List;

/**
 * LXZ
 */
public class TagTextBoxValueDefault extends TagValue{

    private List<TextBox> items;

    public TagTextBoxValueDefault() {
    }

    public List<TextBox> getItems() {
        return items;
    }

    public void setItems(List<TextBox> items) {
        this.items = items;
    }
}
