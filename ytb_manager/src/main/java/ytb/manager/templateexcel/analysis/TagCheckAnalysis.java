package ytb.manager.templateexcel.analysis;

import ytb.manager.templateexcel.model.HtmlComponent.Check;
import ytb.manager.templateexcel.model.HtmlComponent.Metadata;
import ytb.manager.templateexcel.model.HtmlComponent.Text;
import ytb.manager.templateexcel.model.tag.impl.TagTitle;
import ytb.manager.templateexcel.model.xls.ExcelRowData;
import ytb.manager.templateexcel.model.tag.Tag;
import ytb.manager.templateexcel.model.tag.impl.value.TagCheckValueDefault;
import ytb.manager.templateexcel.model.tag.impl.value.TagValue;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LXZ
 */
public class TagCheckAnalysis extends AbstractTagAnalysis {

    private static Pattern p = Pattern.compile("(○|●)(.+)");

    @Override
    public TagTitle doBuildTitle(ExcelRowData excelRowData, Tag tag) {
        return null;
    }

    @Override
    public TagValue doBuildDefaultValue(ExcelRowData excelRowData, Tag tag) {
        List<Metadata> items = new ArrayList<>();
        int i = tag.getContentStart();
        while (i < excelRowData.size()) {
            String cellValue = excelRowData.getCellValue(i);
            cellValue = cellValue.trim();
            if (cellValue.equals("")) {
                break;
            }
            Metadata item;
            Matcher m = p.matcher(cellValue);
            if (m.matches()) {
                Check check = new Check();
                String checked = m.group(1);
                String desc = m.group(2);
                check.setChecked(checked.equals("●"));
                check.setDesc(desc);
                item = check;
            } else {
                Text text = new Text();
                text.setText(cellValue);
                item = text;
            }
            items.add(item);
            i++;
        }
        TagCheckValueDefault tagCheckValueDefault = new TagCheckValueDefault();
        tagCheckValueDefault.setItems(items);
        return tagCheckValueDefault;
    }
}
