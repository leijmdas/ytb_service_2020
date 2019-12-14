package ytb.manager.templateexcel.analysis;

import ytb.manager.templateexcel.model.HtmlComponent.File;
import ytb.manager.templateexcel.model.HtmlComponent.Image;
import ytb.manager.templateexcel.model.HtmlComponent.Metadata;
import ytb.manager.templateexcel.model.tag.impl.TagTitle;
import ytb.manager.templateexcel.model.xls.ExcelRowData;
import ytb.manager.templateexcel.model.tag.Tag;
import ytb.manager.templateexcel.model.tag.impl.value.TagFileValueDefault;
import ytb.manager.templateexcel.model.tag.impl.value.TagValue;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LXZ
 */
public class TagFileAnalysis extends AbstractTagAnalysis {

    //【XXX】文件上传
    private static final Pattern p1 = Pattern.compile("【(.+)】");

    //1/5参考产品图片 图片上传
    private static final Pattern p2 = Pattern.compile("(\\d+)/(\\d+)(.+)");

    @Override
    public TagTitle doBuildTitle(ExcelRowData excelRowData, Tag tag) {
        return null;
    }

    @Override
    public TagValue doBuildDefaultValue(ExcelRowData excelRowData, Tag tag) {
        List<Metadata> items = new ArrayList<>();
        int i = tag.getContentStart();
        while(i<excelRowData.size()){
            String cellValue = excelRowData.getCellValue(i);
            if(cellValue.equals("")){
                break;
            }
            Metadata item = null;
            Matcher m1 = p1.matcher(cellValue);
            Matcher m2 = p2.matcher(cellValue);
            if(m1.matches()){
                File file = new File();
                file.setDesc(m1.group(1));
                item = file;
            }
            if(m2.matches()){
                int curIndex = Integer.parseInt(m2.group(1));
                int limit = Integer.parseInt(m2.group(2));
                String desc = m2.group(3);
                Image image = new Image();
                image.setCurIndex(curIndex);
                image.setLimit(limit);
                image.setDesc(desc);
                item = image;
            }
            if(item!=null){
                items.add(item);
            }
            i++;
        }
        TagFileValueDefault tagFileValueDefault = new TagFileValueDefault();
        tagFileValueDefault.setItems(items);
        return tagFileValueDefault;
    }
}
