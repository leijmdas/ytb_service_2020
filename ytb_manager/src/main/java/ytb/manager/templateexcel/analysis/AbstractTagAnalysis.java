package ytb.manager.templateexcel.analysis;


import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import ytb.manager.templateexcel.model.HtmlComponent.Check;
import ytb.manager.templateexcel.model.HtmlComponent.Radio;
import ytb.manager.templateexcel.model.tag.*;
import ytb.manager.templateexcel.model.tag.impl.TableDict;
import ytb.manager.templateexcel.model.tag.impl.TagEnum;
import ytb.manager.templateexcel.model.tag.impl.TagTitle;
import ytb.manager.templateexcel.model.tag.impl.value.TagCheckValueDefault;
import ytb.manager.templateexcel.model.tag.impl.value.TagRadioValueDefault;
import ytb.manager.templateexcel.model.tag.impl.value.TagValue;
import ytb.manager.templateexcel.model.xls.ExcelRowData;
import ytb.common.context.model.YtbError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LXZ
 */
public abstract class AbstractTagAnalysis {

    private static final Pattern excelCoordPattern = Pattern.compile("([A-Z]+)([0-9]+)");

    private static Pattern excelRadioPattern = Pattern.compile("(○|●)(.+)");

    public Tag createTag(ExcelRowData excelRowData, XSSFSheet sheet, Map<String, Tag> tagMap) {
        String firstCellValue = excelRowData.getFirstCellValue();
        String tagType;
        String tableName = null;
        String tableParamName = null;
        String[] splits = firstCellValue.split(":");
        String typeStr = splits[0];
        String key;
        String version = null;
        String docName = null;
        List<String> refDocNameList = null;
        if (typeStr.contains("@")) {
            String[] typeStrSplit = typeStr.split("@");
            tagType = typeStrSplit[0];
            if (TagEnum.TAG_VERSION.equals(tagType)) {//tagVersion
                version = typeStrSplit[1];
            } else if (TagEnum.TAG_DOCUMENT.equals(tagType)) {//tagDocument
                docName = typeStrSplit[1];
            } else if (TagEnum.TAG_DOCUMENT_REF.equals(tagType)) {//tagDocumentRef
                String[] nameArr = typeStrSplit[1].split(",");
                refDocNameList = Arrays.asList(nameArr);
            } else {//标签关联表
                String tableNameOrField = typeStrSplit[1];
                if (tableNameOrField.contains(".")) {//tagTableParam
                    String[] split = tableNameOrField.split("\\.");
                    tableName = split[0];
                    tableParamName = split[1];
                } else {//tagTable or tagTableSum
                    tableName = tableNameOrField;
                }
            }
        } else {
            tagType = splits[0];
        }
        key = typeStr + "$A" + excelRowData.getRowIndex();

        int rowLimit = 1;
        if (splits.length > 1) {
            rowLimit = Integer.parseInt(splits[1]);
        }

        Tag tag = null;
        TagTableParamRadio tagTableParamRadio = null;
        TagTableParamCheck tagTableParamCheck = null;
        if (tagType.equals(TagEnum.TAG_TABLE_TEXT_REF)) {
            tag = new ytb.manager.templateexcel.model.tag.TagTableTextRef();
        } else if (tagType.equals(TagEnum.TAG_TABLE_PARAM_REF)) {
            tag = new ytb.manager.templateexcel.model.tag.TagTableParamRef();
        } else if (tagType.equals(TagEnum.TAG_TEXT)) {
            tag = new TagText();
        } else if (tagType.equals(TagEnum.TAG_TEXT_BOX)) {
            tag = new TagTextBox();
        } else if (tagType.equals(TagEnum.TAG_RADIO)) {
            tag = new TagRadio();
        }else if(tagType.equals(TagEnum.TAG_TABLE_PARAM_RADIO)){
            tagTableParamRadio = new TagTableParamRadio();
            tagTableParamRadio.setFieldName(tableParamName);
            TableDict tableDict = null;
            if (tableName != null && !tableName.equals("")) {
                tableDict = new TableDict();
                tableDict.setTableName(tableName);
            }
            if(tagTableParamRadio != null){
                tagTableParamRadio.setTableDict(tableDict);
            }
            tag = tagTableParamRadio;
        } else if (tagType.equals(TagEnum.TAG_CHECK)) {
            tag = new TagCheck();
        } else if (tagType.equals(TagEnum.TAG_TABLE_PARAM_CHECK)) {
            tagTableParamCheck = new TagTableParamCheck();
            tagTableParamCheck.setFieldName(tableParamName);
            TableDict tableDict = null;
            if (tableName != null && !tableName.equals("")) {
                tableDict = new TableDict();
                tableDict.setTableName(tableName);
            }
            if(tagTableParamCheck != null){
                tagTableParamCheck.setTableDict(tableDict);
            }
            tag = tagTableParamCheck;
        }else if (tagType.equals(TagEnum.TAG_FILE)) {
            tag = new TagFile();
        } else if (tagType.startsWith(TagEnum.TAG_TABLE)) {
            TableDict tableDict = null;
            if (tableName != null && !tableName.equals("")) {
                tableDict = new TableDict();
                tableDict.setTableName(tableName);
            }

            TagTable tagTable = null;

            if (tagType.equals(TagEnum.TAG_TABLE)) {
                tagTable = new TagTable();
            }
            if (tagType.equals(TagEnum.TAG_TABLE_SUM)) {
                tagTable = new TagTableSum();
            }
            if (tagType.equals(TagEnum.TAG_TABLE_IRREGULAR)) {
                tagTable = new TagTableIrregular();
            }
            if (tagType.equals(TagEnum.TAG_TABLE_PARAM)) {
                TagTableParam tagTableParam = new TagTableParam();
                tagTableParam.setTableParamName(tableParamName);
                tagTable = tagTableParam;
            }
            if (tagType.equals(TagEnum.TAG_TABLE_REF)) {
                tagTable = new TagTableRef();
            }

            if (tagTable != null) {
                tagTable.setTableDict(tableDict);
                tag = tagTable;
            }

            if (tagTable != null && TagEnum.isMultiRowTagType(tagType)) {
                //tagTable title 占几行 即title最大行限制
                int titleRowLimit = excelRowData.getCellRowSpan(0);
                tagTable.setTitleRowLimit(titleRowLimit);
            }

        } else if (tagType.equals(TagEnum.TAG_TITLE)) {
            tag = new ytb.manager.templateexcel.model.tag.TagTitle();
            rowLimit = 0;
        } else if (tagType.equals(TagEnum.TAG_TITLE_RED)) {
            tag = new ytb.manager.templateexcel.model.tag.TagTitleRed();
            rowLimit = 0;
        } else if (tagType.equals(TagEnum.TAG_DOCUMENT)) {
            TagDocument tagDocument = new TagDocument();
            tagDocument.setDocName(docName);
            tag = tagDocument;
            rowLimit = 0;
        } else if (tagType.equals(TagEnum.TAG_DOCUMENT_REF)) {
            TagDocumentRef tagDocumentRef = new TagDocumentRef();
            tagDocumentRef.setRefDocNameList(refDocNameList);
            tag = tagDocumentRef;
            rowLimit = 0;
        } else if (tagType.equals(TagEnum.TAG_VERSION)) {
            TagVersion tagVersion = new TagVersion();
            tagVersion.setVersion(version);
            tag = tagVersion;
            rowLimit = 0;
        } else {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, "not found tag model , and tagType is " + tagType);
        }

        if (tag == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, "tag is null");
        }

        tag.setTagType(tagType);
        tag.setKey(key);
        this.setRowLimit(tag, rowLimit);
        tagMap.put(key, tag);

        List<String> markTag = new ArrayList<String>() {//深度为1 内容起始位置为2
            {
                add(TagEnum.TAG_DOCUMENT);
                add(TagEnum.TAG_DOCUMENT_REF);
                add(TagEnum.TAG_VERSION);
            }
        };
        if (markTag.contains(tagType)) {
            tag.setDepth(1);
            tag.setContentStart(2);
        } else {
            //计算深度
            int i = 2;
            while (i < excelRowData.size()) {
                String cellValue = excelRowData.getCellValue(i);
                if (!cellValue.equals("")) {
                    break;
                }
                i++;
            }
            int spaceNum = i - 2;//空格数 深度=空格数+1
            int depth = spaceNum + 1;
            tag.setDepth(depth);

            //计算内容的起始位置
            int contentStart = i;
            tag.setContentStart(contentStart);
        }

        //处理关联
        String refStr = excelRowData.getTwoCellValue();
        if (refStr != null) {
            refStr = refStr.trim();
            tag.setRefString(refStr);
            if (!refStr.equals("") && !refStr.startsWith("reqItem")) {
                //关联显示和关联引用
                String[] refStrArr = refStr.split("\\|");
                for (String e : refStrArr) {
                    this.handleRef(e, tag, sheet, tagMap);
                }
            }
        }
        this.buildTitle(excelRowData, tag);
        return tag;
    }

    //关联显示和关联引用
    private void handleRef(String refStr, Tag tag, XSSFSheet sheet, Map<String, Tag> tagMap) {
        if (refStr.startsWith("src_ref")) {//关联引用 被引用的内容
            System.out.println("src_ref");
        } else if (refStr.startsWith("ref")) {//关联引用 引用别人的内容
            System.out.println("ref");
        } else if (refStr.startsWith("\"sheet\"")) {//关联显示
            String[] s = refStr.split("\\$");
            String s0 = s[0].replaceAll("\"", "");
            String coord = s[1];
            Matcher m = excelCoordPattern.matcher(coord);
            if (m.matches()) {
                String column = m.group(1);
                String row = m.group(2);
                int colNum = column.charAt(0) - 'A';// 0-based
                int rowNum = Integer.parseInt(row);//1-based
                DataFormatter dataFormatter = new DataFormatter();
                int i = rowNum - 1;
                while (i >= 1) {
                    XSSFCell firstCell = sheet.getRow(i).getCell(0);
                    String tagType = dataFormatter.formatCellValue(firstCell);
                    if (tagType.startsWith("tagRadio")) {//关联是单选按钮组
                        String refTagKey = "tagRadio$A" + (i + 1);
                        Tag refTag = tagMap.get(refTagKey);
                        TagRadio refTagRadio = (TagRadio) refTag;
                        if (refTagRadio.isHorizontal()) {//水平
                            int index = colNum - refTagRadio.getContentStart();//关联了第几个单选按钮
                            List<TagValue> value = refTagRadio.getValue();//获取所有行
                            TagRadioValueDefault tagRadioValueDefault = (TagRadioValueDefault) value.get(0);//第一行默认值
                            Radio radio = (Radio) tagRadioValueDefault.getItems().get(index);//获取当前行的第n个元数据
                            radio.getRefTagKey().add(tag.getKey());//单选按钮与需要关联显示的Tag建立关联关系
                            radio.setRefString(refStr);
                            tag.setShow(radio.isChecked());//设置tag是否显示
                        } else {//垂直
                            int index = 0;//匹配的单选按钮前面的单选按钮个数 即匹配的单选按钮在整个单选按钮组中的位置
                            int j = rowNum - 2;//从关联显示的单选按钮前一行开始往回找
                            while (j >= 1) {
                                XSSFRow vRow = sheet.getRow(j);
                                XSSFCell vRadioCell = vRow.getCell(colNum);
                                String formatCellValue = dataFormatter.formatCellValue(vRadioCell);//找radio格式的单元格
                                if (excelRadioPattern.matcher(formatCellValue).matches()) {
                                    index++;
                                    XSSFCell vTagTypeCell = vRow.getCell(0);
                                    String vTagType = dataFormatter.formatCellValue(vTagTypeCell);
                                    if (vTagType.startsWith("tagRadio")) {
                                        break;
                                    }
                                }
                                j--;
                            }
                            List<TagValue> value = refTagRadio.getValue();//获取所有行
                            int count = 0;
                            for (TagValue tagValue : value) {
                                if (tagValue instanceof TagRadioValueDefault) {
                                    if (count == index) {
                                        TagRadioValueDefault tagRadioValueDefault = (TagRadioValueDefault) tagValue;
                                        Radio radio = (Radio) tagRadioValueDefault.getItems().get(0);//获取当前行的第0个元数据
                                        radio.getRefTagKey().add(tag.getKey());//单选按钮与需要关联显示的Tag建立关联关系
                                        radio.setRefString(refStr);
                                        tag.setShow(radio.isChecked());//设置tag是否显示
                                        break;
                                    }
                                    count++;
                                }
                            }
                        }
                        break;
                    } else if (tagType.startsWith(TagEnum.TAG_CHECK)) {//复选按钮
                        String refTagKey = TagEnum.TAG_CHECK + "$A" + (i + 1);
                        Tag refTag = tagMap.get(refTagKey);
                        TagCheck tagCheck = (TagCheck) refTag;
                        //目前只有水平复选按钮组
                        int index = colNum - tagCheck.getContentStart();//关联了复选按钮处于第几个单元格
                        List<TagValue> value = tagCheck.getValue();//获取所有行
                        TagCheckValueDefault tagCheckValueDefault = (TagCheckValueDefault) value.get(0);//第一行默认值
                        Check check = (Check) tagCheckValueDefault.getItems().get(index);//获取当前行的第n个元数据
                        check.getRefTagKey().add(tag.getKey());//复选按钮与需要关联显示的Tag建立关联关系
                        tag.setShow(check.isChecked());//设置tag是否显示
                        break;
                    }
                    i--;
                }
            }
        } else {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "Excel-无效的引用信息");
        }
    }

    protected void setRowLimit(Tag tag, int rowLimit) {
        tag.setRowLimit(rowLimit);
    }

    public void buildTitle(ExcelRowData excelRowData, Tag tag) {
        TagTitle tagTitle = this.doBuildTitle(excelRowData, tag);
        //无title的Tag
        if (tagTitle == null) {
            if (tag.getRowLimit() > 0) {
                this.buildDefaultValue(excelRowData, tag);
            }
        } else {
            tag.getTagTitle().add(tagTitle);
            if (TagEnum.isMultiRowTagType(tag.getTagType())) {
                TagTable tagTable = (TagTable) tag;
                tagTable.setTitleRows(tagTable.getTitleRows() + 1);
            }
        }
    }

    /**
     * 子节点是tag的默认值
     */
    public void buildDefaultValue(ExcelRowData excelRowData, Tag tag) {
        try {
            TagValue tagValue = this.doBuildDefaultValue(excelRowData, tag);
            tag.getValue().add(tagValue);
            this.rowsAdd(tag);
        } catch (Exception ex) {
            throw new RuntimeException("构建" + tag.getTagType() + "的默认值" + excelRowData.toString() + "出错：" + ex.getMessage(), ex);
        }

    }

    protected abstract TagTitle doBuildTitle(ExcelRowData excelRowData, Tag tag);

    protected abstract TagValue doBuildDefaultValue(ExcelRowData excelRowData, Tag tag);

    /**
     * 子节点是tag值
     */
    public void buildTagValue(Tag curTag, Tag parentTag) {
        parentTag.getValue().add(curTag);
        this.rowsAdd(parentTag);
    }

    private void rowsAdd(Tag tag) {
        tag.setRows(tag.getRows() + 1);
    }

}
