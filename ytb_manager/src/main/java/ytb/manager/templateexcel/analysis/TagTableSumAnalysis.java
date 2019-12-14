package ytb.manager.templateexcel.analysis;

import ytb.manager.templateexcel.model.HtmlComponent.CellData;
import ytb.manager.templateexcel.model.tag.Tag;
import ytb.manager.templateexcel.model.tag.impl.TagTitle;
import ytb.manager.templateexcel.model.tag.impl.value.TagTableValueDefault;
import ytb.manager.templateexcel.model.tag.impl.value.TagValue;
import ytb.manager.templateexcel.model.xls.CellRangeAddressCustom;
import ytb.manager.templateexcel.model.xls.ExcelRowData;
import org.apache.poi.ss.usermodel.Comment;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author lxz
 * @Date 2018/11/8 14:03
 * @Description xxx
 */
public class TagTableSumAnalysis extends TagTableAnalysis {

    private static Pattern p1 = Pattern.compile("^【(.*)】$");

    @Override
    public TagTitle doBuildTitle(ExcelRowData excelRowData, Tag tag) {
        return null;
    }

    @Override
    public TagValue doBuildDefaultValue(ExcelRowData excelRowData, Tag tag) {
        int i = tag.getContentStart();
        List<CellData> items = new ArrayList<>();
        while (i < excelRowData.size()) {
            String cellValue = excelRowData.getCellValue(i);
            Comment cellComment =  excelRowData.getCellComment(i);
            Matcher m = p1.matcher(cellValue);
            boolean isInput = false;
            String placeholder = "";
            if (m.matches()) {
                placeholder = m.group(1);
                isInput = true;
            }
            CellData cellData = null;
            CellRangeAddressCustom cellRangeAddressCustom = excelRowData.getCellRangeAddressCustom(i);
            if (cellRangeAddressCustom != null) {
                if (cellRangeAddressCustom.isRead()) {
//                    cellData.setHide(true);
                } else {
                    cellData = new CellData();
                    if (isInput) {
                        cellData.setPlaceholder(placeholder);
                    } else {
                        cellData.setText(cellValue);
                    }
                    cellData.setRowSpan(cellRangeAddressCustom.getRowSpan());
                    cellData.setColSpan(cellRangeAddressCustom.getColumnSpan());
                    cellRangeAddressCustom.setRead(true);
                }
            } else {
                cellData = new CellData();
                if (isInput) {
                    cellData.setPlaceholder(placeholder);
                } else {
                    cellData.setText(cellValue);
                }
                cellData.setRowSpan(1);
                cellData.setColSpan(1);
            }

            if (cellData != null) {
                //注释信息存入hint
                if(null != cellComment){
                    cellData.setHint(cellComment.getString().toString());
                }
                items.add(cellData);
            }

            i += excelRowData.getCellSkipNum(i) + 1;
        }

        TagTableValueDefault tagTableValueDefault = new TagTableValueDefault();
        tagTableValueDefault.setItems(items);

        this.processTableValueDefault(tag, tagTableValueDefault);

        return tagTableValueDefault;
    }
}
