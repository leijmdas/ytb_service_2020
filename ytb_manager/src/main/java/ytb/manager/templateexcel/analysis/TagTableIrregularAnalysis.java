package ytb.manager.templateexcel.analysis;

import ytb.manager.templateexcel.model.HtmlComponent.CellData;
import ytb.manager.templateexcel.model.tag.Tag;
import ytb.manager.templateexcel.model.tag.impl.TagTitle;
import ytb.manager.templateexcel.model.tag.impl.value.TagTableValueDefault;
import ytb.manager.templateexcel.model.tag.impl.value.TagValue;
import ytb.manager.templateexcel.model.xls.CellRangeAddressCustom;
import ytb.manager.templateexcel.model.xls.ExcelRowData;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lxz
 * @Date 2018/11/9 18:40
 * @Description 不规则表格解析器
 */
public class TagTableIrregularAnalysis extends TagTableAnalysis {

    @Override
    public TagTitle doBuildTitle(ExcelRowData excelRowData, Tag tag) {
        int i = tag.getContentStart();
        List<CellData> items = new ArrayList<>();
        while (i < excelRowData.size()) {
            CellRangeAddressCustom cellRangeAddressCustom = excelRowData.getCellRangeAddressCustom(i);
            if (cellRangeAddressCustom != null) {
                if (cellRangeAddressCustom.isRead()) {
                } else {
                    cellRangeAddressCustom.setRead(true);
                    String cellValue = excelRowData.getCellValue(i);
                    if (cellValue.equals("")) {
                        break;
                    }
                    CellData cellData = new CellData();
                    cellData.setText(cellValue);
                    cellData.setRowSpan(cellRangeAddressCustom.getRowSpan());
                    cellData.setColSpan(cellRangeAddressCustom.getColumnSpan());
                    items.add(cellData);
                }
            } else {
                String cellValue = excelRowData.getCellValue(i);
                if (cellValue.equals("")) {
                    break;
                }
                CellData cellData = new CellData();
                cellData.setText(cellValue);
                cellData.setRowSpan(1);
                cellData.setColSpan(1);
                items.add(cellData);
            }
            i++;
        }
        TagTitle tagTitle = new TagTitle();
        tagTitle.setItems(items);
        return tagTitle;
    }

    @Override
    public TagValue doBuildDefaultValue(ExcelRowData excelRowData, Tag tag) {
        int i = tag.getContentStart();
        List<CellData> items = new ArrayList<>();
        while (i < excelRowData.size()) {
            CellRangeAddressCustom cellRangeAddressCustom = excelRowData.getCellRangeAddressCustom(i);
            if (cellRangeAddressCustom != null) {
                if (cellRangeAddressCustom.isRead()) {
                    CellData cellData = new CellData();
                    cellData.setHide(true);
                    //默认值在外面处理
                    items.add(cellData);
                } else {
                    cellRangeAddressCustom.setRead(true);
                    String cellValue = excelRowData.getCellValue(i);
                    if (cellValue.equals("")) {
                        break;
                    }
                    CellData cellData = new CellData();
                    cellData.setText(cellValue);
                    cellData.setRowSpan(cellRangeAddressCustom.getRowSpan());
                    cellData.setColSpan(cellRangeAddressCustom.getColumnSpan());
                    items.add(cellData);
                }
            } else {
                String cellValue = excelRowData.getCellValue(i);
                if (cellValue.equals("")) {
                    break;
                }
                CellData cellData = new CellData();
                cellData.setText(cellValue);
                cellData.setRowSpan(1);
                cellData.setColSpan(1);
                items.add(cellData);
            }
            i++;
        }
        TagTableValueDefault tagTableValueDefault = new TagTableValueDefault();
        tagTableValueDefault.setItems(items);

        super.processTableValueDefault(tag, tagTableValueDefault);

        return tagTableValueDefault;
    }
}
