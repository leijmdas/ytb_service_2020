package ytb.manager.templateexcel.analysis;

import ytb.manager.templateexcel.model.HtmlComponent.*;
import ytb.manager.templateexcel.model.tag.Tag;
import ytb.manager.templateexcel.model.tag.impl.TagTitle;
import ytb.manager.templateexcel.model.tag.impl.value.TagTableValueDefault;
import ytb.manager.templateexcel.model.tag.impl.value.TagTextBoxValueDefault;
import ytb.manager.templateexcel.model.tag.impl.value.TagValue;
import ytb.manager.templateexcel.model.xls.ExcelRowData;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lxz 2018/12/27 14:07
 */
public class TagTableParamAnalysis extends TagTableAnalysis {

    private static final Pattern p = Pattern.compile("(?<a>[^【】]+)|【(?<b>[^【】]*)】");

    @Override
    public TagTitle doBuildTitle(ExcelRowData excelRowData, Tag tag) {
        return null;
    }

    @Override
    public TagValue doBuildDefaultValue(ExcelRowData excelRowData, Tag tag) {
        int inputCount = 1;//if gt 1 throw ex

        String cellValue = excelRowData.getCellValue(tag.getContentStart());
        Matcher m = p.matcher(cellValue);
        TextBox textBox = new TextBox();
        CellData cellData = new CellData();
        cellData.setTextBox(textBox);
        if (cellValue.equals("")) {
            cellData.setText("");
        } else {
            while (m.find()) {
                String a = m.group("a");
                String b = m.group("b");
                Metadata metadata;
                if (a != null) {
                    Text text = new Text();
                    text.setText(a);
                    metadata = text;
                } else {
                    if (inputCount > 1) {
                        throw new RuntimeException(tag.getTagType() + "input only 1");
                    }
                    Input input = new Input();
                    input.setPlaceholder(b);
                    metadata = input;
                    inputCount++;
                }
                textBox.getItems().add(metadata);
            }
        }

        TagTableValueDefault tagTableValueDefault = new TagTableValueDefault();
        List<CellData> items = new ArrayList<>();
        items.add(cellData);
        tagTableValueDefault.setItems(items);

        return tagTableValueDefault;
    }
}
