package ytb.manager.templateexcel.model.tag.impl.value;

import org.h2.index.Index;
import ytb.manager.templateexcel.model.HtmlComponent.CellData;

import java.util.List;

/**
 * LXZ
 */
public class TagTableValueDefault extends TagValue {

    private List<CellData> items;

    /**
     * 是否可删除
     * 默认不可删除
     * <p>
     * 参考 TagTableAnalysis#doBuildDefaultValue
     */
    private boolean removeable;

    /**
     * 是否可添加
     * 默认可添加
     */
    private boolean addable;

    public TagTableValueDefault() {
    }

    public List<CellData> getItems() {
        return items;
    }

    public void setItems(List<CellData> items) {
        this.items = items;
    }

    public boolean isRemoveable() {
        return removeable;
    }

    public void setRemoveable(boolean removeable) {
        this.removeable = removeable;
    }

    public boolean isAddable() {
        return addable;
    }

    public void setAddable(boolean addable) {
        this.addable = addable;
    }

    public int getShowCellCount() {
        int sum = 0;
        for (CellData c : items) {
            if (!c.isHide()) {
                sum++;
            }
        }
        return sum;
    }

    public IndexRange getIndexRangeByColumn(int column) {
        int start = -1, end = -1, i = 0;
        while (i < items.size()) {
            if (i + 1 == column) {
                CellData c = items.get(i);
                if (c.isMergeCell()) {
                    start = i;
                    end = i + c.getColSpan() - 1;
                } else {
                    start = end = i;
                }
                break;
            }
            i++;
        }
        if (start == -1) {
            throw new RuntimeException("start=-1");
        }
        return new IndexRange(start, end);
    }

    public class IndexRange {

        private int start;

        private int end;

        private IndexRange() {

        }

        private IndexRange(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }
}
