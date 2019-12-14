package ytb.manager.templateexcel.analysis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ytb.manager.templateexcel.model.HtmlComponent.Metadata;
import ytb.manager.templateexcel.model.HtmlComponent.Radio;
import ytb.manager.templateexcel.model.tag.*;
import ytb.manager.templateexcel.model.tag.impl.TableDict;
import ytb.manager.templateexcel.model.tag.impl.TagEnum;
import ytb.manager.templateexcel.model.tag.impl.value.TagRadioValueDefault;
import ytb.manager.templateexcel.model.tag.impl.value.TagValue;
import ytb.manager.templateexcel.model.xls.CellRangeAddressCustom;
import ytb.manager.templateexcel.model.xls.ExcelRowData;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * LXZ
 */
public class ExcelAnalysis {

    public static String toJSONString(InputStream is) throws IOException {
        XSSFWorkbook sheets = new XSSFWorkbook(is);
        return JSON.toJSONString(analysis(sheets));
    }

    public static String toJSONString(String path) throws IOException, InvalidFormatException {
        File file = new File(path);
        XSSFWorkbook sheets = new XSSFWorkbook(file);
        return JSON.toJSONString(analysis(sheets));
    }

    public static List<Tag> analysis(InputStream is) throws IOException {
        XSSFWorkbook sheets = new XSSFWorkbook(is);
        return analysis(sheets);
    }

    private static List<Tag> analysis(XSSFWorkbook sheets) {
        XSSFSheet sheet = sheets.getSheetAt(0);
//        DataFormatter formatter = new DataFormatter();

        //Tag结果集
        List<Tag> resultList = new ArrayList<>();

        //记录深度对应的父tag
        Map<Integer, Tag> depthMap = new HashMap<>();

        //记录上一个解析的tag
        Tag preTag = null;

        //key -> tag
        Map<String, Tag> tagMap = new HashMap<>();

        //合并区域
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
        List<CellRangeAddressCustom> mergedRegionsCustom = new ArrayList<>();
        for (CellRangeAddress e : mergedRegions) {
            CellRangeAddressCustom c = new CellRangeAddressCustom(e.getFirstRow(), e.getLastRow(), e.getFirstColumn(), e.getLastColumn());
            mergedRegionsCustom.add(c);
        }

        //循环Excel每一行数据
        for (Row row : sheet) {
            //Excel标题行跳过
            if (row.getRowNum() == 0) {
                continue;
            }

            //每一行数据
            ExcelRowData excelRowData = new ExcelRowData();
            excelRowData.setRow(row);
            excelRowData.setMergedRegions(mergedRegionsCustom);
            if (excelRowData.size() == 0) {
                continue;
            }
            excelRowData.setRowIndex(row.getRowNum() + 1);

            //标签类型
            String tagType = excelRowData.getFirstCellValue();

            //Tag解析类
            AbstractTagAnalysis abstractTagAnalysis;

            if (tagType.equals("tagEnd")) {//解析结束标志
                break;
            } else if (tagType.equals("")) {//构建标签的内容行
                if (preTag == null) {
                    continue;
                }

                String preTagType = preTag.getTagType();
                if (TagEnum.isMultiRowTagType(preTagType)) {
                    TagTable tagTable = (TagTable) preTag;
                    //针对表格的title读取
                    if (tagTable.getTitleRows() < tagTable.getTitleRowLimit()) {
                        abstractTagAnalysis = TagAnalysisBuildFactory.getAbstractTagAnalysis(preTagType);
                        abstractTagAnalysis.buildTitle(excelRowData, preTag);
                        continue;
                    }
                }

                Tag parentTag = null;
                //还没到到上一个Tag的结束行
                if (preTag.getRows() < preTag.getRowLimit()) {
                    parentTag = preTag;
                } else {
                    //上一个Tag已经读取完毕,往上找到未结束的tag
                    Tag curTag;
                    do {
                        curTag = depthMap.get(preTag.getDepth());
                    } while (curTag != null && curTag.getRows() >= curTag.getRowLimit());
                    if (curTag != null && curTag.getRows() < curTag.getRowLimit()) {
                        parentTag = preTag = curTag;
                    }
                }
                if (parentTag != null) {
                    abstractTagAnalysis = TagAnalysisBuildFactory.getAbstractTagAnalysis(parentTag.getTagType());
                    abstractTagAnalysis.buildDefaultValue(excelRowData, parentTag);
                }
            } else {
                abstractTagAnalysis = TagAnalysisBuildFactory.getAbstractTagAnalysis(tagType);
                //构建当前Tag
                Tag curTag = abstractTagAnalysis.createTag(excelRowData, sheet, tagMap);
                //挂载当前Tag
                int depth = curTag.getDepth();
                if (depth == 1) {
                    resultList.add(curTag);
                } else {
                    Tag parentTag;
                    if (preTag != null && depth > preTag.getDepth()) {
                        parentTag = preTag;
                    } else {
                        parentTag = depthMap.get(depth);
                    }
                    abstractTagAnalysis.buildTagValue(curTag, parentTag);
                }
                //记录当前Tag
                preTag = curTag;
                //建立下一深度的Tag的父节点
                depthMap.put(depth + 1, curTag);
            }
        }

        return resultList;
    }

    /**
     * 获取带有的需求因子数据的Radio列表
     *
     * @param tagList 标签列表
     * @return Radio列表
     */
    public static List<Radio> getRadioList(List<Tag> tagList) {
        List<Radio> radioList = new ArrayList<>();
        for (Tag tag : tagList) {
            readReqItemRadio(tag, radioList);
        }
        return radioList;
    }

    private static void readReqItemRadio(Tag tag, List<Radio> radioList) {
        if (tag instanceof TagRadio) {
            TagRadio tagRadio = (TagRadio) tag;
            List<TagValue> valueList = tagRadio.getValue();
            for (TagValue v : valueList) {
                if (v instanceof TagRadioValueDefault) {
                    TagRadioValueDefault tagRadioValueDefault = (TagRadioValueDefault) v;
                    List<Metadata> items = tagRadioValueDefault.getItems();
                    for (Metadata m : items) {
                        if (m instanceof Radio) {
                            Radio radio = (Radio) m;
                            if (radio.getReqItemNo() != null && !"".equals(radio.getReqItemNo())) {
                                radioList.add(radio);
                            }
                        }
                    }
                }
            }
            return;
        }
        List<TagValue> tagValueList = tag.getValue();
        for (TagValue tagValue : tagValueList) {
            if (tagValue instanceof Tag) {
                readReqItemRadio((Tag) tagValue, radioList);
            }
        }
    }

    /**
     * @return java.util.List<ytb.manager.templateexcel.model.tag.TagTableParamRadio>
     * @Author qsy
     * @Description 获取带有的标签关联表数据的TagTableParamRadio列表
     * @Date 15:21 2019/4/11
     * @Param [tagList]
     **/
    public static List<TagTableParamRadio> getTagTableParamRadioList(List<Tag> tagList) {
        List<TagTableParamRadio> tagTableParamRadioList = new ArrayList<>();
        for (Tag tag : tagList) {
            readTableParamRadio(tag, tagTableParamRadioList);
        }
        return tagTableParamRadioList;
    }

    /**
     * @return void
     * @Author qsy
     * @Description //TODO
     * @Date 15:23 2019/4/11
     * @Param [tag, tagTableParamRadioList]
     **/
    private static void readTableParamRadio(Tag tag, List<TagTableParamRadio> tagTableParamRadioList) {
        if (tag instanceof TagTableParamRadio) {
            TagTableParamRadio tagTableParamRadio = (TagTableParamRadio) tag;
            TableDict tableDict = tagTableParamRadio.getTableDict();
            if (tableDict != null) {
                String tableName = tableDict.getTableName();
                if (tableName != null && !"".equals(tableName)) {
                    tagTableParamRadioList.add(tagTableParamRadio);
                }
            }
            return;
        }
        List<TagValue> tagValueList = tag.getValue();
        for (TagValue tagValue : tagValueList) {
            if (tagValue instanceof Tag) {
                readTableParamRadio((Tag) tagValue, tagTableParamRadioList);
            }
        }
    }

    /**
     * @return java.util.List<ytb.manager.templateexcel.model.tag.TagTableParamCheck>
     * @Author qsy
     * @Description 获取TagTableParamCheck列表
     * @Date 13:10 2019/4/15
     * @Param [tagList]
     **/
    public static List<TagTableParamCheck> getTagTableParamCheckList(List<Tag> tagList) {
        List<TagTableParamCheck> tagTableParamCheckList = new ArrayList<>();
        for (Tag tag : tagList) {
            readTableParamCheck(tag, tagTableParamCheckList);
        }
        return tagTableParamCheckList;
    }

    /**
     * @return void
     * @Author qsy
     * @Description //TODO
     * @Date 13:19 2019/4/15
     * @Param [tag, tagTableParamCheckList]
     **/
    public static void readTableParamCheck(Tag tag, List<TagTableParamCheck> tagTableParamCheckList) {
        if (tag instanceof TagTableParamCheck) {
            TagTableParamCheck tagTableParamCheck = (TagTableParamCheck) tag;
            TableDict tableDict = tagTableParamCheck.getTableDict();
            if (tableDict != null) {
                String tableName = tableDict.getTableName();
                if (tableName != null && !"".equals(tableName)) {
                    tagTableParamCheckList.add(tagTableParamCheck);
                }
            }
            return;
        }
        List<TagValue> tagValueList = tag.getValue();
        for (TagValue tagValue : tagValueList) {
            if (tagValue instanceof Tag) {
                readTableParamCheck((Tag) tagValue, tagTableParamCheckList);
            }
        }
    }

    /**
     * 获取带有的标签关联表数据的TagTable列表
     *
     * @param tagList 标签列表
     * @return TagTable列表
     */
    public static List<TagTable> getTagTableList(List<Tag> tagList) {
        List<TagTable> tagTableList = new ArrayList<>();
        for (Tag tag : tagList) {
            readTagTable(tag, tagTableList);
        }
        return tagTableList;
    }

    private static void readTagTable(Tag tag, List<TagTable> tagTableList) {
        if (tag instanceof TagTable) {
            TagTable tagTable = (TagTable) tag;
            TableDict tableDict = tagTable.getTableDict();
            if (tableDict != null) {
                String tableName = tableDict.getTableName();
                if (tableName != null && !"".equals(tableName)) {
                    tagTableList.add(tagTable);
                }
            }
            return;
        }
        List<TagValue> tagValueList = tag.getValue();
        for (TagValue tagValue : tagValueList) {
            if (tagValue instanceof Tag) {
                readTagTable((Tag) tagValue, tagTableList);
            }
        }
    }

    public static List<Tag> getTagList(List<Tag> tagList, String tagType) {
        List<Tag> rTagList = new ArrayList<>();
        for (int i = 0; i < tagList.size(); i++) {
            readTag(tagList.get(i), rTagList, tagType);
        }
        return rTagList;
    }

    private static void readTag(Tag tag, List<Tag> tagList, String tagType) {
        if (tag.getTagType().equals(tagType)) {
            tagList.add(tag);
        }
        List<TagValue> tagValueList = tag.getValue();
        for (TagValue tagValue : tagValueList) {
            if (tagValue instanceof Tag) {
                Tag $tag = (Tag) tagValue;
                if ($tag.getTagType().equals(tagType)) {
                    tagList.add($tag);
                } else {
                    readTag($tag, tagList, tagType);
                }
            }
        }
    }

    //body
    public static List<JSONObject> getTagList(JSONArray tagList, String tagType) {
        List<JSONObject> rTagList = new ArrayList<>();
        for (int i = 0; i < tagList.size(); i++) {
            readTag(tagList.getJSONObject(i), rTagList, tagType);
        }
        return rTagList;
    }

    private static void readTag(JSONObject tag, List<JSONObject> tagTableList, String tagType) {
        if (tag.get("tagType").equals(tagType)) {
            tagTableList.add(tag);
        }
        JSONArray tagValueList = tag.getJSONArray("value");
        for (int i = 0; i < tagValueList.size(); i++) {
            JSONObject tagValue = tagValueList.getJSONObject(i);
            if (tagValue.containsKey("tagType")) {
                if (tagValue.get("tagType").equals(tagType)) {
                    tagTableList.add(tagValue);
                } else {
                    readTag(tagValue, tagTableList, tagType);
                }
            }
        }
    }

    /**
     * 获取文档中数据来源为REST接口的tagTable JSONArray数组
     *
     * @param body 文档内容 JSONArray
     * @return JSONArray
     */
    public static JSONArray getTagTableWithDataSrcFromRest(JSONArray body) {
        List<JSONObject> tagTableList = getTagList(body, TagEnum.TAG_TABLE);
        //筛选数据源来源于rest接口的tagTable
        JSONArray tagTableRest = new JSONArray();
        for (JSONObject tagTableJsonObj : tagTableList) {
            JSONObject tableDict = tagTableJsonObj.getJSONObject("tableDict");
            Short refSrc = tableDict.getShort("refSrc");
            /*String refObject = tableDict.getString("refObject");
            String refParam = tableDict.getString("refParam");*/
            if (refSrc == 1) {//数据来源于rest接口
                tagTableRest.add(tagTableJsonObj);
            }
        }
        return tagTableRest;
    }

    /**
     * 获取文档中tagTableRef表格
     *
     * @param body 文档内容 JSONArray
     * @return List<JSONObject>
     */
    public static List<JSONObject> getTagTableRef(JSONArray body) {
        return getTagList(body, TagEnum.TAG_TABLE_REF);
    }

    /**
     * 构建Rest TagTable 数据
     *
     * @param tagTable 表格
     * @param dataList 数据
     */
    public static void buildTagTableData(JSONObject tagTable, List<Map<String, Object>> dataList) {
        JSONArray value = tagTable.getJSONArray("value");
        if (value.size() == 0) {
            return;
        }
        JSONObject rowDataPrototype = JSON.parseObject(value.getJSONObject(0).toJSONString());
        //克隆第0行
        value.clear();

        Map<String, Integer> fieldNameMap = new HashMap<>();
        JSONArray items = rowDataPrototype.getJSONArray("items");

        for (int j = 0; j < items.size(); j++) {
            JSONObject cellData = items.getJSONObject(j);
            String fieldName = cellData.getString("fieldName");
            fieldNameMap.put(fieldName, j);
        }

        for (Map<String, Object> data : dataList) {
            JSONObject cloneRowData = JSON.parseObject(rowDataPrototype.toJSONString());//克隆数据行
            value.add(cloneRowData);
            //构建一行
            for (Map.Entry<String, Object> row : data.entrySet()) {
                String fieldName = row.getKey();
                Object val = row.getValue();

                Integer columnIndex = fieldNameMap.get(fieldName);
                //System.out.println(JSONObject.toJSONString(cloneRowData));
                JSONObject cellDataJsonObj = cloneRowData.getJSONArray("items").getJSONObject(columnIndex);
                cellDataJsonObj.put("text", val.toString());
            }
        }
    }

    public static JSONObject getTagTable(JSONArray body, String tableName) {
        List<JSONObject> tagTableList = getTagList(body, TagEnum.TAG_TABLE);
        for (JSONObject tagTable : tagTableList) {
            if (tableName.equals(tagTable.getJSONObject("tableDict").getString("tableName"))) {
                return tagTable;
            }
        }
        return null;
    }

    public static JSONObject parseDocument(String document) throws UnsupportedEncodingException {
        return JSONObject.parseObject(document);
    }

//    public static JSONArray getBody(JSONObject document) {
//        return document.getJSONArray("body");
//    }
//
//    public static void setBody(JSONObject document, JSONObject body) {
//        document.put("body", body);
//    }

    public static void modifyBodyReplaceTag(JSONObject document, Map<String, JSONObject> tableBodyMap) {
        JSONArray body = document.getJSONArray("body");
        // tableBodyMap key:table json key : body :tableBody
        modifyBodyReplaceTag(body, TagEnum.TAG_TABLE, tableBodyMap);

    }

    public static void modifyBodyReplaceTag(JSONArray body, String tagType, Map<String, JSONObject> keyMap) {
        for (int i = 0; i < body.size(); i++) {
            replaceTagInner(body, i, body.getJSONObject(i), tagType, keyMap);
        }
    }

    private static void replaceTagInner(JSONArray parent, int index, JSONObject tag, String tagType, Map<String, JSONObject> keyMap) {
        if (!tag.containsKey("tagType")) {//no tag
            return;
        }
        if (tag.getString("tagType").equals(tagType)) {
            String key = tag.getString("key");
            if (keyMap.containsKey(key)) {
                parent.set(index, keyMap.get(key));
                return;
            }
        }
        JSONArray value = tag.getJSONArray("value");
        for (int i = 0; i < value.size(); i++) {
            JSONObject tagValue = value.getJSONObject(i);
            replaceTagInner(value, i, tagValue, tagType, keyMap);
        }
    }

//    public static void main(String[] args) {
//        /*DocumentServiceImpl documentService = new DocumentServiceImpl();
//        Dict_document document = documentService.getMngrReDocument(1865);*/
//        SqlMapper sqlMapper = new SqlMapper(MyBatisUtil.getSession());
//        Dict_document document = sqlMapper.selectOne("select * from dict_document where document_id=1866", Dict_document.class);
//        String s = new String(document.getDocument());
//        JSONObject jsonObject = JSONObject.parseObject(s);
//        JSONArray body = jsonObject.getJSONArray("body");
//        Map<String, JSONObject> map = new HashMap<>();
//        map.put("tagTable@work_group_task$A4", new JSONObject());
//        // modifyBody(body, map);
//    }


}