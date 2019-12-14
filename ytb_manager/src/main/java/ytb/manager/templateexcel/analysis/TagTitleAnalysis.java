package ytb.manager.templateexcel.analysis;

import ytb.manager.templateexcel.model.HtmlComponent.CellData;
import ytb.manager.templateexcel.model.tag.Tag;
import ytb.manager.templateexcel.model.tag.impl.TagTitle;
import ytb.manager.templateexcel.model.tag.impl.value.TagValue;
import ytb.manager.templateexcel.model.xls.ExcelRowData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxz 2019/1/2 14:17
 */
public class TagTitleAnalysis extends AbstractTagAnalysis {

    @Override
    protected TagTitle doBuildTitle(ExcelRowData excelRowData, Tag tag) {
        String cellValue = excelRowData.getCellValue(tag.getContentStart());
        CellData cellData = new CellData();
        cellData.setText(cellValue);

        TagTitle tagTitle = new TagTitle();
        List<CellData> items = new ArrayList<>();
        items.add(cellData);
        tagTitle.setItems(items);

        return tagTitle;
    }

    @Override
    protected TagValue doBuildDefaultValue(ExcelRowData excelRowData, Tag tag) {
        return null;
    }
}
