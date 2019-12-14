package ytb.manager.templateexcel.analysis;

import org.apache.poi.ss.usermodel.Comment;
import ytb.manager.templateexcel.model.tag.Tag;
import ytb.manager.templateexcel.model.tag.TagTable;
import ytb.manager.templateexcel.model.tag.TagTableSum;
import ytb.manager.templateexcel.model.tag.impl.DbTableEnum;
import ytb.manager.templateexcel.model.tag.impl.TableDict;
import ytb.manager.templateexcel.model.tag.impl.TagTitle;
import ytb.manager.templateexcel.model.tag.impl.value.TagTableValueDefault;
import ytb.manager.templateexcel.model.tag.impl.value.TagValue;
import ytb.manager.templateexcel.model.xls.CellRangeAddressCustom;
import ytb.manager.templateexcel.model.xls.ExcelRowData;
import ytb.manager.templateexcel.model.HtmlComponent.CellData;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LXZ
 */
public class TagTableAnalysis extends AbstractTagAnalysis {

    private static Pattern p1 = Pattern.compile("^【(.*)】$");

    @Override
    public TagTitle doBuildTitle(ExcelRowData excelRowData, Tag tag) {
        TagTitle tagTitle = new TagTitle();
        tagTitle.setItems(readOneRow(excelRowData, tag));
        return tagTitle;
    }

    @Override
    public TagValue doBuildDefaultValue(ExcelRowData excelRowData, Tag tag) {
        TagTableValueDefault tagTableValueDefault = new TagTableValueDefault();
        tagTableValueDefault.setItems(readOneRow(excelRowData, tag));

//        this.processTableValueDefault(tag, tagTableValueDefault);

        return tagTableValueDefault;
    }

    protected void processTableValueDefault(Tag tag, TagTableValueDefault tagTableValueDefault) {
        TagTable tagTable = (TagTable) tag;
        if (tagTable instanceof TagTableSum) {//如果是合计表格,不可添加行,不可删除行
            tagTableValueDefault.setAddable(false);
            tagTableValueDefault.setRemoveable(false);
            return;
        }
        TableDict tableDict = tagTable.getTableDict();
        if (tableDict != null) {
            String dbName = tableDict.getDbName();
            String tableName = tableDict.getTableName();
            switch (tableName) {
                case DbTableEnum.TableEnum.WORK_GROUP_TASK:
                case DbTableEnum.TableEnum.DEMAND_PART_LIST: {//Excel默认的内容行可添加,不可删除  添加的行可删除
                    tagTableValueDefault.setAddable(true);
                    tagTableValueDefault.setRemoveable(false);
                    break;
                }
                default: {
                    tagTableValueDefault.setAddable(false);
                    tagTableValueDefault.setRemoveable(false);
                }
            }
        } else {
            tagTableValueDefault.setAddable(true);
            tagTableValueDefault.setRemoveable(false);
        }
    }

    protected List<CellData> readOneRow(ExcelRowData excelRowData, Tag tag) {
        int i = tag.getContentStart();
        List<CellData> items = new ArrayList<>();
        while (i < excelRowData.size()) {
            String cellValue = excelRowData.getCellValue(i);
            Comment cellComment =  excelRowData.getCellComment(i);
            CellData cellData = new CellData();
            //注释信息存入hint
            if(null != cellComment){
                cellData.setHint(cellComment.getString().toString());
            }
            Matcher m = p1.matcher(cellValue);
            if (m.matches()) {
                String placeholder = m.group(1);
                cellData.setPlaceholder(placeholder);
            } else {
                cellData.setText(cellValue);
            }
            CellRangeAddressCustom cellRangeAddressCustom = excelRowData.getCellRangeAddressCustom(i);
            if (cellRangeAddressCustom != null) {
                if (cellRangeAddressCustom.isRead()) {
                    cellData.setHide(true);
                } else {
                    cellData.setRowSpan(cellRangeAddressCustom.getRowSpan());
                    cellData.setColSpan(cellRangeAddressCustom.getColumnSpan());
                    cellData.setMergeCell(true);
                    cellRangeAddressCustom.setRead(true);
                }
            } else {
                cellData.setRowSpan(1);
                cellData.setColSpan(1);
            }
            items.add(cellData);
            i++;
        }
        return items;
    }


}
