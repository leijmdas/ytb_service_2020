package ytb.manager.templateexcel.analysis;

import ytb.manager.templateexcel.model.HtmlComponent.Input;
import ytb.manager.templateexcel.model.HtmlComponent.Metadata;
import ytb.manager.templateexcel.model.HtmlComponent.Text;
import ytb.manager.templateexcel.model.HtmlComponent.TextBox;
import ytb.manager.templateexcel.model.tag.impl.TagTitle;
import ytb.manager.templateexcel.model.xls.ExcelRowData;
import ytb.manager.templateexcel.model.tag.Tag;
import ytb.manager.templateexcel.model.tag.impl.value.TagTextBoxValueDefault;
import ytb.manager.templateexcel.model.tag.impl.value.TagValue;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LXZ
 */
public class TagTextBoxAnalysis extends AbstractTagAnalysis {

    private static final Pattern p = Pattern.compile("(?<a>[^【】]+)|【(?<b>[^【】]*)】");

    @Override
    public TagTitle doBuildTitle(ExcelRowData excelRowData, Tag tag) {
        return null;
    }

    @Override
    public TagValue doBuildDefaultValue(ExcelRowData excelRowData, Tag tag) {
        List<TextBox> items = new ArrayList<>();
        int i = tag.getContentStart();
        while (i < excelRowData.size()) {
            String cellValue = excelRowData.getCellValue(i);
            if (cellValue.equals("")) {
                break;
            }
            Matcher m = p.matcher(cellValue);
            TextBox textBox = new TextBox();
            while (m.find()) {
                String a = m.group("a");
                String b = m.group("b");
                Metadata metadata;
                if (a != null) {
                    Text text = new Text();
                    text.setText(a);
                    metadata = text;
                } else  {
                    Input input = new Input();
                    input.setPlaceholder(b);
                    metadata = input;
                }
                textBox.getItems().add(metadata);
            }
            items.add(textBox);
            i++;
        }
        TagTextBoxValueDefault tagTextBoxValueDefault = new TagTextBoxValueDefault();
        tagTextBoxValueDefault.setItems(items);
        return tagTextBoxValueDefault;
    }
}
