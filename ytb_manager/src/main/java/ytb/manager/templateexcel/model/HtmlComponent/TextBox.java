package ytb.manager.templateexcel.model.HtmlComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * LXZ
 */
public class TextBox {

    private List<Metadata> items = new ArrayList<>();

    public TextBox() {
    }

    public List<Metadata> getItems() {
        return items;
    }

    public void setItems(List<Metadata> items) {
        this.items = items;
    }
}
