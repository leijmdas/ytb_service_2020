package ytb.manager.templateexcel.analysis;

import ytb.manager.templateexcel.model.tag.Tag;
import ytb.manager.templateexcel.model.tag.impl.TagTitle;
import ytb.manager.templateexcel.model.tag.impl.value.TagValue;
import ytb.manager.templateexcel.model.xls.ExcelRowData;

/**
 * @author lxz 2019/1/20 14:49
 */
public class TagVersionAnalysis extends AbstractTagAnalysis {

    @Override
    protected TagTitle doBuildTitle(ExcelRowData excelRowData, Tag tag) {
        return null;
    }

    @Override
    protected TagValue doBuildDefaultValue(ExcelRowData excelRowData, Tag tag) {
        return null;
    }
}
