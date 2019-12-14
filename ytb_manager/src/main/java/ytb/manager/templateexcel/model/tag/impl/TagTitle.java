package ytb.manager.templateexcel.model.tag.impl;


import ytb.manager.templateexcel.model.HtmlComponent.CellData;

import java.util.List;

/**
 * lxz
 * 描述一行标题
 */
public class TagTitle {

    private List<CellData> items;

    public TagTitle() {
    }

    public List<CellData> getItems() {
        return items;
    }

    public void setItems(List<CellData> items) {
        this.items = items;
    }
}
