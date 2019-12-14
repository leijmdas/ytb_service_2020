package ytb.manager.templateexcel.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import ytb.manager.context.MyBatisUtil;
import ytb.manager.metadata.model.MetadataDict;
import ytb.manager.metadata.model.MetadataField;
import ytb.manager.metadata.service.impl.SysMetaDataServiceImpl;
import ytb.manager.tagtable.model.DBTagTable;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentInfo;
import ytb.manager.tagtable.service.impl.TagTableManagerService;
import ytb.manager.tagtable.service.impl.TagTableProjectService;
import ytb.manager.template.dao.DocumentMapper;
import ytb.manager.template.model.Dict_Req_Item;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.manager.template.model.Dict_document;
import ytb.manager.template.service.IDocumentService;
import ytb.manager.template.service.ProjectTypeService;
import ytb.manager.template.service.ReqItemService;
import ytb.manager.template.service.TemplateService;
import ytb.manager.template.service.impl.DocumentServiceImpl;
import ytb.manager.template.service.impl.ProjectTypeServiceImpl;
import ytb.manager.template.service.impl.ReqItemServiceImpl;
import ytb.manager.template.service.impl.TemplateServiceImpl;
import ytb.manager.templateexcel.analysis.ExcelAnalysis;
import ytb.manager.templateexcel.common.YtbTemplate;
import ytb.manager.templateexcel.model.HtmlComponent.CellData;
import ytb.manager.templateexcel.model.HtmlComponent.Check;
import ytb.manager.templateexcel.model.HtmlComponent.Metadata;
import ytb.manager.templateexcel.model.HtmlComponent.Radio;
import ytb.manager.templateexcel.model.tag.*;
import ytb.manager.templateexcel.model.tag.impl.DbTableEnum;
import ytb.manager.templateexcel.model.tag.impl.TableDict;
import ytb.manager.templateexcel.model.tag.impl.TagEnum;
import ytb.manager.templateexcel.model.tag.impl.value.TagCheckValueDefault;
import ytb.manager.templateexcel.model.tag.impl.value.TagRadioValueDefault;
import ytb.manager.templateexcel.model.tag.impl.value.TagTableValueDefault;
import ytb.manager.templateexcel.model.tag.impl.value.TagValue;
import ytb.manager.templateexcel.model.xls.TemplateDocumentHeader;
import ytb.manager.templateexcel.model.xls.TemplateDocumentModel;
import ytb.manager.templateexcel.service.TemplateDocumentService;
import ytb.common.context.model.YtbError;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static ytb.manager.tagtable.service.impl.TagTableServiceImpl.getTagTableService;

/**
 * LXZ
 */
@Service
public class TemplateDocumentServiceImpl implements TemplateDocumentService {


    private static TemplateDocumentService templateDocumentService = new TemplateDocumentServiceImpl();

    public static TemplateDocumentService getTemplateDocumentService() {
        return templateDocumentService;
    }

    private TemplateService templateService = new TemplateServiceImpl();
    private ReqItemService reqItemService = new ReqItemServiceImpl();
    private ProjectTypeService projectTypeService = new ProjectTypeServiceImpl();

    String findCellText(String fieldName, DBTagTable.DBTagValue rows) {

        for (DBTagTable.TagField tagField : rows.getItems()) {

            if (tagField.getRefField() != null && tagField.getRefField().equals(fieldName)) {
                return tagField.getRefValue();
            }

            if (tagField.getFieldName() != null && tagField.getFieldName().equals(fieldName)) {
                return tagField.getText();
            }


        }
        return "";

    }

    //获取唯一索引字段
    String[] findUniqueKeys(String tableName) {
        List<MetadataDict> dictTableAndField = TagTableManagerService.getTagTableService().getDictTableAndField(tableName);
        MetadataDict metadataDict = dictTableAndField.get(0);
        String uniqueKey = metadataDict.getMetadataIndex();
        if (uniqueKey == null || uniqueKey.isEmpty()) {
            return new String[]{};
        }
        String[] ukeys = uniqueKey.split(",");
        for (int i = 0; i < ukeys.length; i++) {
            ukeys[i] = ukeys[i].trim();
        }

        return ukeys;
    }


    //获取唯一索引字段
    String[] findSortKeys(String tableName) {
        List<MetadataDict> dictTableAndField = TagTableManagerService.getTagTableService().getDictTableAndField(tableName);
        MetadataDict metadataDict = dictTableAndField.get(0);
        String sortFields = metadataDict.getMetadataSortFields();
        if (sortFields == null || sortFields.isEmpty()) {
            return new String[]{};
        }
        String[] sortFieldArray = sortFields.split(",");
        for (int i = 0; i < sortFieldArray.length; i++) {
            sortFieldArray[i] = sortFieldArray[i].trim();
        }

        return sortFieldArray;
    }

    List<Tag>  parseTagList(byte[] document) throws IOException {

        try (InputStream is = new ByteArrayInputStream(document)) {
            return ExcelAnalysis.analysis(is);
        }

    }

    @Override
    public int modifyDocument(Dict_document document) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            DocumentMapper mapper = session.getMapper(DocumentMapper.class);
            mapper.modifyDocument(document);
            return document.getDocumentId();
        }
    }

    @Override
    public Dict_document getDocumentBy(int documentId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            DocumentMapper mapper = session.getMapper(DocumentMapper.class);
            return mapper.getMngrReDocument(documentId);
        }
    }

    @Override
    public void addDocument(Dict_document document) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            DocumentMapper mapper = session.getMapper(DocumentMapper.class);
            mapper.addMngrReDocument(document);
        }
    }

    @Override
    public void removeDocumentBy(int documentId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            DocumentMapper mapper = session.getMapper(DocumentMapper.class);
            mapper.delMngrReDocument(documentId);
        }
    }

    @Override
    public boolean parseExcel(int templateId) throws IOException {
        Dict_TemplateModel template = templateService.getTemplateBy(templateId);
        Dict_document xlsDocument = getDocumentBy(template.getDocXls());
        List<Tag> tagList;
        try (InputStream is = new ByteArrayInputStream(xlsDocument.getDocument())){
            tagList = ExcelAnalysis.analysis(is);
        }
        List<Radio> radioList = ExcelAnalysis.getRadioList(tagList);
        //删除当前模板的所有需求因子数据
        reqItemService.removeListByTemplateId(templateId);
        //将需求因子数据存入header
        List<Dict_Req_Item> reqItemList = new ArrayList<>();
        for (Radio r : radioList) {
            Dict_Req_Item reqItem = new Dict_Req_Item();
            reqItem.setReqItemNo(r.getReqItemNo());
            reqItem.setReqItemDesc(r.getReqItemDesc());
            reqItem.setValue(r.isChecked() ? 1 : 0);
            reqItem.setTemplateId(template.getTemplateId());
            //导出需求因子表,插入需求因子表,将reqItemId注入Dict_Req_Item对象中
            reqItemService.add(reqItem);
            //将需求因子表中的数据的ID存储在TagRadio单选按钮Radio上
            r.setReqItemId(reqItem.getItemId());
            reqItemList.add(reqItem);
        }
        //标签TagTableParamRadio
        List<TagTableParamRadio> tagTableParamRadioList = ExcelAnalysis.getTagTableParamRadioList(tagList);
        List<CellData> tagTableParamRadio_value_one_items = new ArrayList<>();
        for(TagTableParamRadio tagTableParamRadio : tagTableParamRadioList){
            TableDict tableDict = tagTableParamRadio.getTableDict();
            String tableName = tableDict.getTableName();
            //获取元数据字典字段数据
            List<MetadataDict> dictTableAndField = TagTableManagerService.getTagTableService().getDictTableAndField(tableName);
            MetadataDict metadataDict = dictTableAndField.get(0);
            tableDict.setDbName(metadataDict.getMetadataDb());
            tableDict.setRefSrc(metadataDict.getRefSrc());
            tableDict.setRefObject(metadataDict.getRefObject());
            tableDict.setRefParam(metadataDict.getRefParam());
            tableDict.setMetadataAddDel(metadataDict.getMetadataAddDel());
            List<MetadataField> fieldList = (List<MetadataField>) metadataDict.getField();
            //筛选可见属性
            List<MetadataField> visibleFieldList = new ArrayList<>();
            for (MetadataField metadataField : fieldList) {
                if (metadataField.getFieldVisible()) {
                    visibleFieldList.add(metadataField);
                }
            }

            List<TagValue> tagTableParamRadioValues = tagTableParamRadio.getValue();
            for (TagValue e : tagTableParamRadioValues) {
                if (e instanceof TagRadioValueDefault) {
                    MetadataField metadataField = null;
                    for (MetadataField m : visibleFieldList) {
                        m.setFieldName(m.getFieldName().trim());
                        tagTableParamRadio.setFieldName(tagTableParamRadio.getFieldName().trim());
                        if (m.getFieldName().equals(tagTableParamRadio.getFieldName())) {
                            metadataField = m;
                            break;
                        }
                    }
                    if (metadataField == null) {
                        throw new RuntimeException("字段：" + tagTableParamRadio.getFieldName() + "不存在");
                    }
                    List<Metadata> metadataList = ((TagRadioValueDefault) e).getItems();
                    for(Metadata metadata : metadataList){
                        Radio radio = (Radio)metadata;
                        if(radio.isChecked()){
                            CellData cellData = new CellData();
                            cellData.setFieldName(tagTableParamRadio.getFieldName().trim());
                            cellData.setFieldMemo(radio.getDesc());
                            cellData.setRefValue((radio.isChecked()==true ? YtbTemplate.TAG_RADIO_CHECKED_TEXT_VALUE : YtbTemplate.TAG_RADIO_UNCHECKED_TEXT_VALUE));
                            cellData.setText(radio.getDesc());
                            cellData.injectMetadataField(metadataField);
                            tagTableParamRadio_value_one_items.add(JSON.parseObject(JSON.toJSONString(cellData), CellData.class));
                        }
                    }
                }
            }
        }
        //标签tagTableParamCheck
        List<TagTableParamCheck> tagTableParamCheckList = ExcelAnalysis.getTagTableParamCheckList(tagList);
        List<CellData> tagTableParamCheck_value_one_items = new ArrayList<>();
        for(TagTableParamCheck tagTableParamCheck : tagTableParamCheckList){
            TableDict tableDict = tagTableParamCheck.getTableDict();
            String tableName = tableDict.getTableName();
            //获取元数据字典字段数据
            List<MetadataDict> dictTableAndField = TagTableManagerService.getTagTableService().getDictTableAndField(tableName);
            MetadataDict metadataDict = dictTableAndField.get(0);
            tableDict.setDbName(metadataDict.getMetadataDb());
            tableDict.setRefSrc(metadataDict.getRefSrc());
            tableDict.setRefObject(metadataDict.getRefObject());
            tableDict.setRefParam(metadataDict.getRefParam());
            tableDict.setMetadataAddDel(metadataDict.getMetadataAddDel());
            List<MetadataField> fieldList = (List<MetadataField>) metadataDict.getField();
            //筛选可见属性
            List<MetadataField> visibleFieldList = new ArrayList<>();
            for (MetadataField metadataField : fieldList) {
                if (metadataField.getFieldVisible()) {
                    visibleFieldList.add(metadataField);
                }
            }
            List<TagValue> tagTableParamCheckValues = tagTableParamCheck.getValue();
            for (TagValue e : tagTableParamCheckValues) {
                if (e instanceof TagCheckValueDefault) {
                    List<Metadata> metadataList = ((TagCheckValueDefault) e).getItems();
                    String checkDesc = "";
                    for(Metadata metadata : metadataList){
                        Check check = (Check)metadata;
                        if(check.isChecked()){
                            checkDesc +="|"+check.getDesc();
                        }
                    }
                    MetadataField metadataField = null;
                    for (MetadataField m : visibleFieldList) {
                        m.setFieldName(m.getFieldName().trim());
                        tagTableParamCheck.setFieldName(tagTableParamCheck.getFieldName().trim());
                        if (m.getFieldName().equals(tagTableParamCheck.getFieldName())) {
                            metadataField = m;
                            break;
                        }
                    }
                    if (metadataField == null) {
                        throw new RuntimeException("字段：" + tagTableParamCheck.getFieldName() + "不存在");
                    }
                    if(metadataList.size()>0 && !checkDesc.equals("")){
                        CellData cellData = new CellData();
                        cellData.setFieldName(tagTableParamCheck.getFieldName().trim());
                        cellData.setFieldMemo(checkDesc.substring(1));
                        cellData.setRefValue(YtbTemplate.TAG_RADIO_CHECKED_TEXT_VALUE);
                        cellData.setText(checkDesc.substring(1));
                        cellData.injectMetadataField(metadataField);
                        tagTableParamCheck_value_one_items.add(JSON.parseObject(JSON.toJSONString(cellData), CellData.class));
                    }
                }
            }
        }
        //标签关联表数据
        List<TagTable> tagTableList = ExcelAnalysis.getTagTableList(tagList);
        JSONObject docTagTableParam = new JSONObject();
        JSONArray tagTableParam_value = new JSONArray();
        TagTableValueDefault tagTableParam_value_one = new TagTableValueDefault();
        List<CellData> tagTableParam_value_one_items = new ArrayList<>();
        //tagTableParamRadio  -->>tagTableParam
        if(tagTableParamRadio_value_one_items.size()>0){
            tagTableParam_value_one_items.addAll(tagTableParamRadio_value_one_items);
        }
        //tagTableParamCheck  -->>tagTableParam
        if(tagTableParamCheck_value_one_items.size()>0){
            tagTableParam_value_one_items.addAll(tagTableParamCheck_value_one_items);
        }
        tagTableParam_value_one.setItems(tagTableParam_value_one_items);
        tagTableParam_value.add(tagTableParam_value_one);
        docTagTableParam.put("value", tagTableParam_value);
        for (TagTable tagTable : tagTableList) {
            TableDict tableDict = tagTable.getTableDict();
            String tableName = tableDict.getTableName();

            //获取元数据字典字段数据
            List<MetadataDict> dictTableAndField = TagTableManagerService.getTagTableService().getDictTableAndField(tableName);
            MetadataDict metadataDict = dictTableAndField.get(0);
            tableDict.setDbName(metadataDict.getMetadataDb());
            tableDict.setRefSrc(metadataDict.getRefSrc());
            tableDict.setRefObject(metadataDict.getRefObject());
            tableDict.setRefParam(metadataDict.getRefParam());
            tableDict.setMetadataAddDel(metadataDict.getMetadataAddDel());
            List<MetadataField> fieldList = (List<MetadataField>) metadataDict.getField();
            //筛选可见属性
            List<MetadataField> visibleFieldList = new ArrayList<>();
            for (MetadataField metadataField : fieldList) {
                if (metadataField.getFieldVisible()) {
                    visibleFieldList.add(metadataField);
                }
            }

            //设置元数据字段信息
            List<CellData> fields = new ArrayList<>();
            tableDict.setFields(fields);
            Map<String, Integer> fieldNameMapCounter = new HashMap<>();
            for (MetadataField metadataField : visibleFieldList) {
                CellData cellData = new CellData(metadataField);
                fields.add(cellData);
                //初始化存在序号的表格计数器
                if (cellData.getFieldFormat().equals("3")) {//序号列
                    fieldNameMapCounter.put(cellData.getFieldName(), 0);
                    tagTable.setContainsNoColumn(true);
                }
            }

            if (!(tagTable instanceof TagTableParam)) {
                if (tagTable.getValue() == null || tagTable.getValue().size() == 0) {
                    throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "excel [" + tableName + "]表格必须至少配置一行");
                }
                TagTableValueDefault tbValRow = (TagTableValueDefault) tagTable.getValue().get(0);
                int dbColumnNum = visibleFieldList.size();
                int tbColumnNum = tbValRow.getShowCellCount();
                if (tbColumnNum != dbColumnNum) {
                    String sb = "excel表格列数与元数据字典配置可见字段数不一致[" + tableName + "]" +
                            "excel表格列数：" + tbValRow.getShowCellCount() + ";" +
                            "元数据字典配置可见字段数：" + visibleFieldList.size();
                    throw new YtbError(YtbError.CODE_DEFINE_ERROR, sb);

                } /*else if (tbColumnNum > dbColumnNum) {
                    int deleteColumn;
                    List<TagValue> value = tagTable.getValue();
                    for (TagValue e : value) {
                        TagTableValueDefault row = (TagTableValueDefault) e;
                        if (row.getItems().size() > dbColumnNum) {
                            deleteColumn = row.getItems().size() - dbColumnNum;
                            while ((deleteColumn--) > 0) {
                                row.getItems().remove(row.getItems().size() - 1);
                            }
                        }
                    }
                }*/
            }


            final Pattern ptNumber = Pattern.compile("^[0-9]+(\\.)?[0-9]+$");
            List<TagValue> tagTableValues = tagTable.getValue();
            for (TagValue e : tagTableValues) {
                if (e instanceof TagTableValueDefault) {
                    TagTableValueDefault tagTableValueDefault = (TagTableValueDefault) e;

                    if (tableDict.isMetadataAddDel()) {
                        tagTableValueDefault.setAddable(true);
                        tagTableValueDefault.setRemoveable(false);
                    } else {
                        tagTableValueDefault.setAddable(false);
                        tagTableValueDefault.setRemoveable(false);
                    }

                    List<CellData> items = tagTableValueDefault.getItems();

                    //tagTableParam
                    if (tagTable.getTagType().equals(TagEnum.TAG_TABLE_PARAM)) {
                        CellData cellData = items.get(0);
                        TagTableParam tagTableParam = (TagTableParam) tagTable;
                        MetadataField metadataField = null;
                        for (MetadataField m : visibleFieldList) {
                            m.setFieldName(m.getFieldName().trim());
                            tagTableParam.setTableParamName(tagTableParam.getTableParamName().trim());
                            if (m.getFieldName().equals(tagTableParam.getTableParamName())) {
                                metadataField = m;
                                break;
                            }
                        }
                        if (metadataField == null) {
                            throw new RuntimeException("字段：" + tagTableParam.getTableParamName() + "不存在");
                        }
                        cellData.injectMetadataField(metadataField);

                        if (!docTagTableParam.containsKey("tableDict")) {
                            docTagTableParam.put("tableDict", JSON.parseObject(JSON.toJSONString(tagTableParam.getTableDict())));
                        }
                        tagTableParam_value_one_items.add(JSON.parseObject(JSON.toJSONString(cellData), CellData.class));
                        break;
                    }

                    for (int i = 0; i < visibleFieldList.size(); i++) {
                        CellData cellData = items.get(i);
                        MetadataField metadataField = visibleFieldList.get(i);
                        cellData.injectMetadataField(metadataField);

                        String refField = cellData.getRefField();
                        if (refField != null && !"".equals(refField)) {
                            //cellData.put(refField, 0);//页面需要
                            cellData.setRefValue(0);//导出表需要
                        }

                        //数字类型字段,如果text值为字符串,则隐藏字符串,text赋值为默认值0
                        if (YtbTemplate.numberTypeList.contains(cellData.getFieldType())) {
                            String text = cellData.getText();
                            if (!ptNumber.matcher(text).matches()) {
                                cellData.setPlaceholder(text);
                                String fieldDefault = cellData.getFieldDefault();
                                if (fieldDefault == null || !fieldDefault.equals("0")) {
                                    fieldDefault = "0";
                                }
                                cellData.setText(fieldDefault);
                            }
                        }

                        //表格是否包含序号列
                        if (tagTable.isContainsNoColumn()) {
                            Integer counter = fieldNameMapCounter.get(cellData.getFieldName());
                            if (counter != null) {
                                counter += 1;
                                cellData.setText(counter + "");
                                fieldNameMapCounter.put(cellData.getFieldName(), counter);
                            }
                        }

                    }

                    //去除多余的数据(比如 乙方工作组表)
                    /*int count = items.size() - visibleFieldList.size();
                    while (count-- > 0) {
                        items.remove(items.size() - 1);
                    }*/
                }

            }
        }

        TemplateDocumentModel templateDocumentModel = new TemplateDocumentModel();
        TemplateDocumentHeader header = new TemplateDocumentHeader();

        header.setTemplateId(template.getTemplateId());
        header.setDocType(template.getDocType());
        header.setDocumentId(xlsDocument.getDocumentId());

        List<Tag> tagDocumentList = ExcelAnalysis.getTagList(tagList, TagEnum.TAG_DOCUMENT);
        if (tagDocumentList != null && tagDocumentList.size() > 0) {
            TagDocument tagDocument = (TagDocument) tagDocumentList.get(0);
            header.setTagDocument(tagDocument.getDocName());
        } else {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "需配置tagDocument标签");
        }

        List<Tag> tagDocumentRefList = ExcelAnalysis.getTagList(tagList, TagEnum.TAG_DOCUMENT_REF);
        if (tagDocumentRefList != null && tagDocumentRefList.size() > 0) {
            TagDocumentRef tagDocumentRef = (TagDocumentRef) tagDocumentRefList.get(0);
            header.setTagDocumentRef(tagDocumentRef.getRefDocNameList());
        } else {
            header.setTagDocumentRef(new ArrayList<>());
        }

        List<Tag> tagVersionList = ExcelAnalysis.getTagList(tagList, TagEnum.TAG_VERSION);
        if (tagVersionList.size() > 0) {
            TagVersion tagVersion = (TagVersion) tagVersionList.get(0);
            header.setTagVersion(tagVersion.getVersion());
        } else {
            header.setTagVersion("1.0");
        }

        templateDocumentModel.setReqItemList(reqItemList);
        templateDocumentModel.setHeader(header);
        templateDocumentModel.setBody(tagList);
        templateDocumentModel.setTagTableParam(docTagTableParam);

        byte[] bytes;
        try {
            bytes = JSON.toJSONString(templateDocumentModel, SerializerFeature.WriteMapNullValue).getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }

        Dict_document documentModel = new Dict_document();
        documentModel.setName(template.getTitle());
        documentModel.setDocument(bytes);
        documentModel.setDocType(4);//json类型
        documentModel.setSaveMode(2);//按表存取
        removeDocumentBy(template.getDocumentId());
        addDocument(documentModel);

        //保存结果到数据库
        Dict_TemplateModel t = new Dict_TemplateModel();
        t.setTemplateId(templateId);
        t.setDocumentId(documentModel.getDocumentId());//文档id存到文档模板表
        t.setAlias(TagEnum.TAG_DOCUMENT + "@" + header.getTagDocument());
        templateService.modifyDocumentIdAlias(t);

        //修改document中header的documentId
        header.setDocumentId(documentModel.getDocumentId());
        DocumentServiceImpl ds = new DocumentServiceImpl();
        try {
            ds.modifyReDocumentHeader(documentModel.getDocumentId(), header);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }
        return true;
    }

    @Override
    public void modifyDocHeader(int documentId, String k, Object v) {
        IDocumentService iDocumentService = new DocumentServiceImpl();
        Dict_document mngrReDocument = iDocumentService.getMngrReDocument(documentId);
        try {
            String s = new String(mngrReDocument.getDocument(), "utf-8");
            JSONObject document = JSONObject.parseObject(s);
            JSONObject header = (JSONObject) document.get("header");
            header.put(k, v);
            mngrReDocument.setDocument(document.toJSONString().getBytes("utf-8"));
            modifyDocument(mngrReDocument);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }

    }

//    @Override
//    public TagTable getTemplateTable(TemplateDocumentModel documentModel, String tableName) throws IOException {
//        List<Tag> bodyTags = documentModel.getBody();
//
//        List<TagTable> tagTables = ExcelAnalysis.getTagTableList(bodyTags);
//        for (TagTable tagTable : tagTables) {
//            TableDict tableDict = tagTable.getTableDict();
//            if (tableDict != null) {
//                String realTableName = tableDict.getTableName();
//                if (realTableName != null && realTableName.equals(tableName)) {
//                    return tagTable;
//                }
//            }
//        }
//
//        throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
//    }

//    @Override
//    public TagTable getTemplateTable(int documentId, String tableName) throws IOException {
//        TemplateRepositoryService templateRepositoryService = new TemplateRepositoryServiceImpl();
//        Dict_document document = templateRepositoryService.getDocument(documentId);
//        String sDocument = new String(document.getDocument(), "utf-8");
//
//        TemplateDocumentModel documentModel = JSONObject.parseObject(sDocument, TemplateDocumentModel.class);
//        return getTemplateTable(documentModel,tableName);
//    }

    @Override
    public JSONObject getDict_document(int documentId) throws IOException {
        System.err.println(documentId);
        Dict_document document = getDocumentBy(documentId);
        if (document == null) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
        }
        String s = new String(document.getDocument(), "UTF-8");

        return JSONObject.parseObject(s);
    }

    @Override
    public TemplateDocumentInfo innerModifyJsonParam(TemplateDocumentInfo tdInfo, Map<String, Object> param) {
        JSONObject docJson = tdInfo.getDocJson();
        JSONObject tagTableParam = docJson.getJSONObject("tagTableParam");
        JSONArray value = tagTableParam.getJSONArray("value");
        JSONArray items = value.getJSONObject(0).getJSONArray("items");//tableDict中的fields

        Map<String, JSONObject> fieldNameMap = new HashMap<>();
        for (int i = 0; i < items.size(); i++) {
            JSONObject e = items.getJSONObject(i);
            fieldNameMap.put(e.getString("fieldName"), e);
        }

        //改tableDict的字段值
        for (Map.Entry<String, Object> field : param.entrySet()) {
            String f_key = field.getKey();
            Object f_value = field.getValue();
            if (fieldNameMap.containsKey(f_key)) {
                JSONObject e = fieldNameMap.get(f_key);
                e.put("text", f_value);
            }
        }

        //改body的字段值
        List<JSONObject> tagTableAll = getTagTableAll(tdInfo);
        for (JSONObject innerTagTableParam : tagTableAll) {
            JSONObject cellData = innerTagTableParam.getJSONArray("value").getJSONObject(0).getJSONArray("items").getJSONObject(0);
            String filedName = cellData.getString("fieldName");
            if (fieldNameMap.containsKey(filedName)) {//存在此字段,修改字段值和TextBox中input的值
                //修改字段值
                String text = fieldNameMap.get(filedName).getString("text");
                cellData.put("text", text);
                //修改input的value值
                JSONArray textBoxArr = cellData.getJSONObject("textBox").getJSONArray("items");
                for (int i = 0; i < textBoxArr.size(); i++) {
                    JSONObject textBoxItem = textBoxArr.getJSONObject(i);
                    if (textBoxItem.containsKey("type") && textBoxItem.getString("type").equals("input")) {
                        textBoxItem.put("value", text);
                    }
                }

            }
        }

        return tdInfo;
    }


    public void inner_tagTableRefHandler(int projectId, int documentId) throws UnsupportedEncodingException {
        SysMetaDataServiceImpl sysMetaDataService = new SysMetaDataServiceImpl();
        Dict_document documentModel = getDocumentBy(documentId);
        byte[] document = documentModel.getDocument();
        String  docStr = new String(document, "UTF-8");

        JSONObject docJsonObj = JSON.parseObject(docStr);
        JSONArray body = docJsonObj.getJSONArray("body");
        //获取需要构建的tagTableRef列表
        List<JSONObject> tagTableRefList = ExcelAnalysis.getTagTableRef(body);
        //调service获取表格的数据
        //构建表格的数据
        for (JSONObject tagTableRef : tagTableRefList) {
            JSONObject tableDict = tagTableRef.getJSONObject("tableDict");
            //System.out.println(JSONObject.toJSONString(tagTableRef));
            String refParam = tableDict.getString("refParam");
            JSONObject params = JSON.parseObject(refParam);
            if (params != null) {
                String pTable = params.getString("table");
                List<Map<String, Object>> records =
                        TagTableManagerService.getTagTableService().selectByTable_metadata(projectId, documentId, pTable);
                ExcelAnalysis.buildTagTableData(tagTableRef, records);
            }
        }
        try {
            documentModel.setDocument(docJsonObj.toJSONString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }
        modifyDocument(documentModel);
    }

    @Override
    public void tagTableRefHandler(int projectId, int documentId) throws UnsupportedEncodingException {
        if (projectId == 0) {
            Dict_document documentModel = getDocumentBy(documentId);
            documentModel.setDocumentId(documentId);
            new TagTableProjectService().tagTableRefHandler(documentModel, projectId, documentId);
            return;
        }
        new TagTableProjectService().tagTableRefHandler_project(projectId, documentId);


    }

    public List<JSONObject> getTagTableAll(TemplateDocumentInfo tdInfo) {
        JSONObject docJson = tdInfo.getDocJson();
        JSONArray body = docJson.getJSONArray("body");
        return ExcelAnalysis.getTagList(body, TagEnum.TAG_TABLE_PARAM);
    }

    @Override
    public void modifyWorkplanWorkJob(int projectId, int documentId, List<Map<String, Object>> list) {
        Dict_document dictDocument = getDocumentBy(documentId);
        byte[] document = dictDocument.getDocument();
        initWorkplanWorkJobByReq(document, projectId, documentId, list);
    }

    //根据需求书初始化工作计划书任务之岗位
    public void initWorkplanWorkJobByReq(byte[] document, int projectId, int documentId, List<Map<String, Object>> list) {
        if (list.size() == 0) {
            return;
        }
        try {
            String documentStr = new String(document, "UTF-8");
            JSONObject documentJsonObj = JSONObject.parseObject(documentStr);
            JSONArray body = documentJsonObj.getJSONArray("body");
            JSONObject workGroupTask = ExcelAnalysis.getTagTable(body, DbTableEnum.TableEnum.WORK_GROUP_TASK);
            JSONObject tableDict = workGroupTask.getJSONObject("tableDict");
            JSONArray fields = tableDict.getJSONArray("fields");
            JSONArray value = workGroupTask.getJSONArray("value");
            JSONObject zeroRowData = value.getJSONObject(0);
            value.clear();
            JSONArray items = zeroRowData.getJSONArray("items");
            Map<String, JSONObject> fieldNameMapCellData = new HashMap<>();
            for (int i = 0; i < items.size(); i++) {
                JSONObject cellDataJsonObj = items.getJSONObject(i);
                String fieldName = cellDataJsonObj.getString("fieldName");
                fieldNameMapCellData.put(fieldName, cellDataJsonObj);
            }
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> workJobData = list.get(i);//岗位数据

                JSONObject rowData = JSONObject.parseObject(JSONObject.toJSONString(zeroRowData));//表格行数据
                value.add(i, rowData);

                //修改岗位数据 和 查询修改岗位任务默认值
                JSONObject workJobCellData = rowData.getJSONArray("items").getJSONObject(0);//岗位
                String workJobRefField = workJobCellData.getString("refField");
                String workJobRefDisplayid = workJobCellData.getString("refDisplayid");

                workJobCellData.put(workJobRefField, workJobData.get(workJobRefField));
                workJobCellData.put("refValue", workJobData.get(workJobRefField));
                workJobCellData.put("text", workJobData.get(workJobRefDisplayid));
                workJobCellData.put("title_alias", workJobData.get("title_alias"));
                workJobCellData.put("is_default", workJobData.get("is_default"));


                JSONObject workJobTaskCellData = rowData.getJSONArray("items").getJSONObject(2);//岗位任务
                String workJobTaskRefField = workJobTaskCellData.getString("refField");
                String workJobTaskRefDisplayid = workJobTaskCellData.getString("refDisplayid");

                JSONObject work_task_json = new JSONObject();
                JSONArray defaultTaskList = new JSONArray();
                work_task_json.put("defaultTaskList", defaultTaskList);
                work_task_json.put("optionalTaskList", new JSONArray());
                work_task_json.put("customTaskList", new JSONArray());
                StringBuilder workJobTaskText = new StringBuilder();

                List<Map<String, Object>> lst = getTagTableService().selectSp(
                        projectId, documentId,
                        "ytb_project.spSelectProjectDutyTask"); //fnSelectProjectWorkjobType
                //筛选当前岗位关联的岗位任务
                for (Map<String, Object> taskItem : lst) {
                    int refId = (int) taskItem.get(workJobRefField);
                    if (workJobCellData.getIntValue("refValue") == refId) {
                        int is_optional = (int) taskItem.get("is_optional");
                        if (is_optional == 0) {//默认任务
                            defaultTaskList.add(taskItem);
                            workJobTaskText.append(taskItem.get(workJobTaskRefDisplayid)).append(";");
                        }
                    }
                }

                workJobTaskCellData.put(workJobTaskRefField, work_task_json.toJSONString());
                workJobTaskCellData.put("refValue", work_task_json.toJSONString());
                workJobTaskCellData.put("text", workJobTaskText.toString());

            }

            Dict_document doc = new Dict_document();
            doc.setDocumentId(documentId);
            doc.setDocument(documentJsonObj.toString().getBytes("UTF-8"));
            modifyDocument(doc);
        } catch (Exception e) {
            e.printStackTrace();
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }

    }

    @Override
    public JSONArray getDocumentDirList(int projectId, int documentId) {
        Dict_document documentBy = getDocumentBy(documentId);
        return getDocumentDirList(documentBy, projectId, documentId);
    }

    @Override
    public JSONArray getDocumentDirList(Dict_document documentBy, int projectId, int documentId) {
        //Dict_document documentBy = getDocumentBy(documentId);
        try {
            JSONObject documentJsonObj = JSON.parseObject(new String(documentBy.getDocument(), "UTF-8"));
            JSONArray body = documentJsonObj.getJSONArray("body");
            List<JSONObject> tagTextList = ExcelAnalysis.getTagList(body, TagEnum.TAG_TEXT);
            JSONArray dbList = new JSONArray();
            for (JSONObject tagText : tagTextList) {
                JSONObject dbRowData = new JSONObject();

                JSONObject tagTitle = tagText.getJSONArray("tagTitle").getJSONObject(0);
                JSONArray items = tagTitle.getJSONArray("items");
                JSONObject num = items.getJSONObject(0);//编号
                JSONObject title = items.getJSONObject(1);//标题
                String numText = num.getString("text");
                String titleText = title.getString("text");
                String dir = numText + titleText;

                //dbRowData.put("project_id", projectId);
                //dbRowData.put("document_id", documentId);
                dbRowData.put("title", dir);

                dbList.add(dbRowData);
            }
            return dbList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, e.getMessage());
        }
    }


}


