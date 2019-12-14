package ytb.manager.templateexcel.model.tag;


import ytb.manager.templateexcel.model.tag.impl.TableDict;

public class TagTable extends Tag {

    private TableDict tableDict;

    //tag 标题已添加的行数
    private int titleRows = 0;

    //tag标题行数最大值
    private int titleRowLimit = 0;

    //存在序号列
    private boolean containsNoColumn;

    public TagTable() {
    }

    public TableDict getTableDict() {
        return tableDict;
    }

    public void setTableDict(TableDict tableDict) {
        this.tableDict = tableDict;
    }

    public int getTitleRows() {
        return titleRows;
    }

    public void setTitleRows(int titleRows) {
        this.titleRows = titleRows;
    }

    public int getTitleRowLimit() {
        return titleRowLimit;
    }

    public void setTitleRowLimit(int titleRowLimit) {
        this.titleRowLimit = titleRowLimit;
    }

    public boolean isContainsNoColumn() {
        return containsNoColumn;
    }

    public void setContainsNoColumn(boolean containsNoColumn) {
        this.containsNoColumn = containsNoColumn;
    }
}
