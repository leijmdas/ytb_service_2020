package ytb.manager.templateexcel.analysis;


import ytb.manager.templateexcel.model.HtmlComponent.CellData;
import ytb.manager.templateexcel.model.HtmlComponent.Input;
import ytb.manager.templateexcel.model.HtmlComponent.Metadata;
import ytb.manager.templateexcel.model.HtmlComponent.Text;
import ytb.manager.templateexcel.model.tag.TagText;
import ytb.manager.templateexcel.model.tag.impl.TagTitle;
import ytb.manager.templateexcel.model.xls.ExcelRowData;
import ytb.manager.templateexcel.model.tag.Tag;
import ytb.manager.templateexcel.model.tag.impl.value.TagTextValueDefault;
import ytb.manager.templateexcel.model.tag.impl.value.TagValue;
import ytb.common.context.model.YtbError;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LXZ
 */
public class TagTextAnalysis extends AbstractTagAnalysis {

    //A.XXX
    private static final Pattern p1 = Pattern.compile("([A-Z]\\.)(.+)");

    //a）XXX
    private static final Pattern p2 = Pattern.compile("([a-z]）)(.+)");

    //1.2.3. XXX
    private static final Pattern p3 = Pattern.compile("(([0-9]+\\.)+)(.+)");

    //【】| XXX
    private static final Pattern p4 = Pattern.compile("(?<a>[^【】]+)|【(?<b>[^【】]*)】");

    //【XXX】
    private static final Pattern p5 = Pattern.compile("【(.*)】");

    public void setRowLimit(Tag tag, int rowLimit) {
        tag.setRowLimit(Integer.MAX_VALUE);
    }

    @Override
    public TagTitle doBuildTitle(ExcelRowData excelRowData, Tag tag) {
        TagText tagText = (TagText) tag;

        String s = excelRowData.getCellValue(tag.getContentStart());
        String num;
        String title;
        Matcher m1 = p1.matcher(s);
        Matcher m2 = p2.matcher(s);
        Matcher m3 = p3.matcher(s);
        int flag;
        if (m1.matches()) {
            num = m1.group(1);
            title = m1.group(2);
            flag = 1;
        } else if (m2.matches()) {
            num = m2.group(1);
            title = m2.group(2);
            flag = 2;
        } else if (m3.matches()) {
            num = m3.group(1);
            title = m3.group(3);
            flag = 3;
        } else {
            throw new RuntimeException(s + " 正则无法匹配");
        }

        CellData numCellData = new CellData();
        numCellData.setText(num);

        title = title.trim();
        Matcher m4 = p4.matcher(title);
        List<CellData> items = new ArrayList<>();
        items.add(numCellData);
        while (m4.find()) {
            String a = m4.group("a");
            String b = m4.group("b");
            CellData cellData = new CellData();
            if (a != null) {//纯文本
                cellData.setText(a);
                cellData.setEdit(false);
            } else {//输入框
                cellData.setPlaceholder(b);
                cellData.setEdit(true);
                tagText.setCanAddDir(true);
                if (flag == 1 || flag == 2) {
                    tagText.setCanAddDirChildLevel(false);
                } else {
                    tagText.setCanAddDirChildLevel(true);
                }

            }
            items.add(cellData);
        }

        TagTitle tagTitle = new TagTitle();
        tagTitle.setItems(items);

        switch (flag) {
            case 1:
            case 3: {
                String[] splits = num.split("\\.");
                tagText.setTopLevelDir(splits.length == 1);
                break;
            }
            case 2: {
                tagText.setTopLevelDir(false);
                break;
            }
            default: {
                throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "tagText flag -1");
            }
        }

        if (flag == 1 || flag == 2) {
            ((TagText) tag).setLetterDir(true);
        }

        return tagTitle;
    }

    @Override
    public TagValue doBuildDefaultValue(ExcelRowData excelRowData, Tag tag) {
        String s = excelRowData.getCellValue(tag.getContentStart());
        s = s.trim();
        Metadata metadata;
        Matcher m = p5.matcher(s);
        if (m.matches()) {
            Input input = new Input();
            input.setPlaceholder(m.group(1));
            metadata = input;
        } else {
            Text text = new Text();
            text.setText(s);
            metadata = text;
        }
        TagTextValueDefault tagTextValueDefault = new TagTextValueDefault();
        tagTextValueDefault.setMetadata(metadata);
        return tagTextValueDefault;
    }

}
