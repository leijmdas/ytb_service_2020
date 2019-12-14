package ytb.manager.templateexcel.analysis;

import ytb.manager.templateexcel.model.HtmlComponent.Metadata;
import ytb.manager.templateexcel.model.HtmlComponent.Radio;
import ytb.manager.templateexcel.model.HtmlComponent.Text;
import ytb.manager.templateexcel.model.tag.impl.TagTitle;
import ytb.manager.templateexcel.model.xls.ExcelRowData;
import ytb.manager.templateexcel.model.tag.Tag;
import ytb.manager.templateexcel.model.tag.TagRadio;
import ytb.manager.templateexcel.model.tag.impl.value.TagRadioValueDefault;
import ytb.manager.templateexcel.model.tag.impl.value.TagValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LXZ
 */
public class TagRadioAnalysis extends AbstractTagAnalysis {

    private static Pattern p = Pattern.compile("(○|●)(.+)");

    private static Pattern refItemRegExp = Pattern.compile("([A-Z])\\((.+)\\)");

    @Override
    public TagTitle doBuildTitle(ExcelRowData excelRowData, Tag tag) {
        //计算radio有几列,1列表示此radio是垂直显示
        int i = tag.getContentStart();
        while (i < excelRowData.size()) {
            String cellValue = excelRowData.getCellValue(i);
            if (cellValue.equals("") || i > tag.getContentStart() + 1) {
                break;
            }
            i++;
        }
        TagRadio tagRadio = (TagRadio) tag;
        //i > tag.getContentStart() + 1多列 水平
        tagRadio.setHorizontal(i > tag.getContentStart() + 1);
        return null;
    }

    @Override
    public TagValue doBuildDefaultValue(ExcelRowData excelRowData, Tag tag) {
        TagRadioValueDefault tagRadioValueDefault = new TagRadioValueDefault();
        String ref = excelRowData.getTwoCellValue();
        tagRadioValueDefault.setRef(ref);
        String[] s = ref.split(":");
        HashMap<String, String> radioIndexMapRefItem = new HashMap<>();
        for (int i = 0; i < s.length; i++) {
            if (i == 0) {
                continue;
            }
            radioIndexMapRefItem.put(i + "", s[i]);
        }
        tagRadioValueDefault.setMap(radioIndexMapRefItem);
        List<Metadata> items = new ArrayList<>();
        int i = tag.getContentStart();
        int count = 1;
        while (i < excelRowData.size()) {
            String cellValue = excelRowData.getCellValue(i);
            if (cellValue.equals("")) {
                break;
            }
            Metadata item;
            Matcher m = p.matcher(cellValue);
            if (m.matches()) {
                Radio radio = new Radio();
                String checked = m.group(1);
                String desc = m.group(2);
                radio.setDesc(desc);
                radio.setChecked(checked.equals("●"));
                item = radio;
                //处理radio的需求因子数据
                String refItem = radioIndexMapRefItem.get(count + "");
                if (refItem != null) {
                    Matcher refItemRegExpM = refItemRegExp.matcher(refItem);
                    if (refItemRegExpM.matches()) {
                        String reqItemNo = refItemRegExpM.group(1);
                        String reqItemDesc = refItemRegExpM.group(2);
                        radio.setReqItemNo(reqItemNo);
                        radio.setReqItemDesc(reqItemDesc);
                        count++;
                    }
                }
            } else {
                Text text = new Text();
                text.setText(cellValue);
                item = text;
            }
            items.add(item);
            i++;
        }
        tagRadioValueDefault.setItems(items);
        return tagRadioValueDefault;
    }
}
