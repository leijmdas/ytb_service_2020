package ytb.manager.tagtable.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import ytb.common.MyBatis.ISqlSessionBuilder;
import ytb.common.utils.YtbSql;
import ytb.manager.context.ManagerSrvContext;
import ytb.common.context.service.impl.YtbContext;
import ytb.manager.tagtable.service.ITemplateDocumentFactroy;
import ytb.manager.tagtable.service.tagtemplate.DocumentRefService;
import ytb.manager.tagtable.service.tagtemplate.TemplateSetUpRefAll;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentInfo;
import ytb.manager.template.model.Dict_document;
import ytb.manager.templateexcel.analysis.ExcelAnalysis;
import ytb.manager.templateexcel.model.tag.impl.DbTableEnum;
import ytb.manager.templateexcel.model.xls.TemplateDocumentHeader;
import ytb.manager.templateexcel.service.TemplateDocumentService;
import ytb.common.context.model.YtbError;
import ytb.common.context.service.IUserContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class TagTableProjectService extends TagTableServiceImpl {

    static TemplateDocumentService getTemplateDocumentService() {
        return ManagerSrvContext.getInst().getTemplateDocumentService();
    }

    String db_name = ISqlSessionBuilder.DB_NAME_PROJECT;

    @Override
    public SqlSession getSession() {
        return YtbContext.getSqlSessionBuilder().getSession_project(true);
    }



    public List<JSONObject> listTagTitleRed(TemplateDocumentInfo info, int projectId,
                                            int documentId) throws Exception {
        return info.listTagTitleRed();
    }


    public Dict_document getProjectDocument(int projectId, int documentId) {
        String tableName = "ytb_project.project_document";
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from ").append(tableName);
        sql.append(" where document_id=").append(documentId);
        return YtbSql.selectOne(sql, Dict_document.class);
    }

    public void modifyReDocumentHeader(int documentId, TemplateDocumentHeader tagDocumentHeader) throws UnsupportedEncodingException {

        Dict_document dict_document = getProjectDocument(0, documentId);

        String doc = new String(dict_document.getDocument(), "utf-8");
        JSONObject jdoc = JSONObject.parseObject(doc);
        for (Map.Entry<String, Object> e : tagDocumentHeader.toJSONObject().entrySet()) {
            if (e.getValue() != null) {
                jdoc.getJSONObject("header").put(e.getKey(), e.getValue());
            }
        }
        dict_document.setDocument(JSONObject.toJSONString(jdoc).getBytes("utf-8"));
        modifyProjectTemplate(dict_document);


    }


    //暂时只支持同文档内引用
    public void tagTableRefHandler_project(int projectId, int documentId) throws UnsupportedEncodingException {
        Dict_document documentModel = getProjectDocument(projectId, documentId);
        documentModel.setDocumentId(documentId);
        tagTableRefHandler(documentModel, projectId, documentId);
    }

    public void tagTableRefHandler(Dict_document documentModel, int projectId, int documentId) throws UnsupportedEncodingException {

        String docStr = new String(documentModel.getDocument(), "UTF-8");

        JSONObject docJsonObj = JSON.parseObject(docStr);
        JSONArray body = docJsonObj.getJSONArray("body");
        //获取需要构建的tagTableRef列表
        List<JSONObject> tagTableRefList = ExcelAnalysis.getTagTableRef(body);
        //调service获取表格的数据
        //构建表格的数据
        for (JSONObject tagTableRef : tagTableRefList) {
            JSONObject tableDict = tagTableRef.getJSONObject("tableDict");
            String refParam = tableDict.getString("refParam");
            JSONObject params = JSON.parseObject(refParam);

            if (params != null) {
                String pTable = params.getString("table");
                List<Map<String, Object>> records = selectByTable_metadata(projectId, documentId, pTable);
                ExcelAnalysis.buildTagTableData(tagTableRef, records);
            } else {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "tagTableRef需要配置表的REST URL");
            }
        }
        documentModel.setDocument(docJsonObj.toJSONString().getBytes("UTF-8"));
        if (projectId == 0) {
            modifyManagerDocument(documentModel);

        } else {
            modifyProjectTemplate(documentModel);
        }

    }


    //根据需求书初始化工作计划书任务之岗位
    int initWorkplanWorkJobByReq(byte[] bDocument, int projectId, int documentId, List<Map<String, Object>> list) throws UnsupportedEncodingException {
        if (list.size() == 0) {
            return 0;
        }
        String documentStr = new String(bDocument, "UTF-8");
        JSONObject documentJsonObj = JSONObject.parseObject(documentStr);
        JSONArray body = documentJsonObj.getJSONArray("body");
        JSONObject workGroupTaskTable = ExcelAnalysis.getTagTable(body, DbTableEnum.TableEnum.WORK_GROUP_TASK);
        JSONArray value = workGroupTaskTable.getJSONArray("value");
        JSONObject zeroRowData = value.getJSONObject(0);
        value.clear();

        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> data = list.get(i);
            JSONObject rowData = JSONObject.parseObject(JSONObject.toJSONString(zeroRowData));//表格行数据
            value.add(i, rowData);

            JSONArray items = rowData.getJSONArray("items");

            JSONObject workJobCellData = items.getJSONObject(0);
            JSONObject userCellData = items.getJSONObject(1);
            JSONObject taskCellData = items.getJSONObject(2);

            String workJobRefField = workJobCellData.getString("refField");
            String workJobRefDisplayid = workJobCellData.getString("refDisplayid");
            workJobCellData.put(workJobRefField, data.get(workJobRefField));
            workJobCellData.put("refValue", data.get(workJobRefField));
            workJobCellData.put("text", data.get(workJobRefDisplayid));
            workJobCellData.put("title_alias", data.get("title_alias"));
            workJobCellData.put("is_default", data.get("is_default"));


            String userRefField = userCellData.getString("refField");
            String userRefDisplayid = userCellData.getString("refDisplayid");
            userCellData.put(userRefField, data.get(userRefField));
            userCellData.put("refValue", data.get(userRefField));
            userCellData.put("text", data.get(userRefDisplayid));


            String workJobTaskRefField = taskCellData.getString("refField");
            String workJobTaskRefDisplayid = taskCellData.getString("refDisplayid");
            taskCellData.put(workJobTaskRefField, data.get(workJobTaskRefField));
            taskCellData.put("refValue", data.get(workJobTaskRefField));
            taskCellData.put("text", data.get(workJobTaskRefDisplayid));


        }

        Dict_document document = new Dict_document();
        document.setDocumentId(documentId);
        document.setDocument(documentJsonObj.toString().getBytes("UTF-8"));
        return projectId == 0 ? modifyManagerDocument(document) : modifyProjectTemplate(document);

    }

    public void initWorkplanByreq(TemplateDocumentInfo tdi,ITemplateDocumentFactroy templateDocumentFactroy,
                                  TemplateDocumentInfo documentInfo,
                                  IUserContext userContext, int projectId,
                                  int documentId) throws IOException {
        // 修改工作计划书的工作岗位
        Integer repositoryId = projectId == 0 ? documentInfo.getdBDocHeader().getRepositoryId() : projectId;
        Integer planId = DocumentRefService.getDocumentRefService().findChildWorkplanId(repositoryId, projectId, documentId);

        planId = planId == null ? 0 : planId;
        if (planId == 0) {
            return;
        }

        if (fnCheckWorkjobChange(projectId, documentId, planId)) {
            initWorkplanWorkJob(tdi,userContext, projectId, planId);
        }
    }

    //select fnCheckWorkjobChange(83,318,319);
    boolean fnCheckWorkjobChange(int projectId, int reqid, int planid) {
        String ret = YtbSql.fnDb("ytb_project.fnCheckWorkjobChange", projectId, reqid, planid);
        return ret.equalsIgnoreCase("1");
    }


    void initWorkplanWorkJob(TemplateDocumentInfo documentInfo, IUserContext userContext, int projectId, int documentId) throws IOException {

        Dict_document dict_document = projectId == 0 ?
                getTemplateDocumentService().getDocumentBy(documentId) :
                getProjectDocument(projectId, documentId);

        Integer reqid = DocumentRefService.getDocumentRefService().findRefReqId(
                documentInfo.getdBDocHeader().getRepositoryId(), projectId, documentId);
        List<Map<String, Object>> list = new TagTableWorkGroup()
                .spInitWorkplanWorkjob(projectId, reqid, userContext.getLoginSso().getUserId(), userContext.getLoginSso().getLoginSsoJson().getNick_name());
        if (list.size() == 0) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "工作计划书对应需求说明书无一岗位!");
        }
        initWorkplanWorkJobByReq(dict_document.getDocument(), projectId, documentId, list);
        new TemplateSetUpRefAll().updateRefDocTimeSync(projectId, documentId);
    }


}
