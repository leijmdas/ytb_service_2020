package ytb.manager.tagtable.rest.impl;


import com.alibaba.fastjson.JSONObject;
import ytb.common.utils.YtbSql;
import ytb.common.ytblog.YtbLog;
import ytb.common.utils.YtbUtils;
import ytb.manager.context.ManagerSrvContext;
import ytb.manager.tagtable.model.DBTagTable;
import ytb.manager.tagtable.model.business.ProjectRateModel;
import ytb.manager.tagtable.model.tagtemplate.RefTagTableParam;
import ytb.manager.tagtable.service.ITagTableService;
import ytb.manager.tagtable.service.ITemplateDocumentFactroy;
import ytb.manager.tagtable.service.tagtemplate.TagDocumentRefService;
import ytb.manager.tagtable.service.tagtemplate.DocumentRefService;
import ytb.manager.tagtable.service.impl.*;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentFactroy;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentInfo;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.manager.templateexcel.analysis.ExcelAnalysis;
import ytb.manager.templateexcel.service.impl.CheckTemplate;
import ytb.manager.templateexcel.service.impl.TemplateDocumentServiceImpl;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Package: ytb.common.testcase.Rest.impl
 * Author: leijianming
 * Date: Created in 2018/10/20 16:21
 */
public class TagTableServiceRestProcess implements ITagTableServiceRestProcess {
    ITemplateDocumentFactroy templateDocumentFactroy = new TemplateDocumentFactroy();
    final static String TagTableServiceManager = "tagTableServiceManager";
    final static String TagTableServiceProject = "tagTableServiceProject";


    TemplateDocumentInfo documentInfo;

    public ITagTableService getTagTableService() {
        return ManagerSrvContext.getInst().getTagTableService();
    }

    public TemplateDocumentInfo parseTemplate(Integer projectId, Integer documentId ) throws IOException {
        if(documentInfo==null) {
            documentInfo = templateDocumentFactroy.parseTemplate(projectId, documentId,true);
        }
        return  documentInfo;
    }

    public void checkTemplate(int projectId, int documentId) throws IOException {
        parseTemplate(projectId, documentId);
        checkTemplate(documentInfo);
    }

    public void checkTemplate(TemplateDocumentInfo documentInfo) throws IOException {
        CheckTemplate checkTemplate = new CheckTemplate();
        checkTemplate.checkTable(documentInfo);

        //排序后保存，然后界面可以显示
        Map<String, DBTagTable> tagTableMap = checkTemplate.sortTable(documentInfo, "asc");
        Map<String, JSONObject> tableMap = new LinkedHashMap<>();
        if (false && tagTableMap.keySet().size() > 0) {
            for (String tablename : tagTableMap.keySet()) {
                JSONObject table = JSONObject.parseObject(tagTableMap.get(tablename).toString());
                tableMap.put(table.getString("key"), table);
            }

            JSONObject document = ExcelAnalysis.parseDocument(documentInfo.getDocJson().toJSONString());
            ExcelAnalysis.modifyBodyReplaceTag(document, tableMap);
            //save document 有BUG
            templateDocumentFactroy.modifyProjectTemplate(documentInfo.getdBDocHeader().getDocumentId(), document);
        }

    }

    //项目中心模板不是自己的文档不能导出表数据
    //协助文档的文件头header要修改 documentId,否则会导出数据删除别人的数据
    public void checkTemplateOwner(MsgHandler handler,Integer projectId, Integer documentId){
        //管理模板不判断
        if(projectId==0){
            return ;
        }
        StringBuilder sql=new StringBuilder(128);
        sql.append("select 1 from ytb_project.project_template ");
        sql.append(" where document_id=").append(documentId);
        sql.append(" and author=").append(handler.getUserContext().getLoginSso().getUserId());
        List<Dict_TemplateModel> models = YtbSql.selectList(sql,Dict_TemplateModel.class);
        if(models.size()==0){
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD,"不是自己的文档不能操作！");
        }
    }

    //导出文档中所有关联表：一次扫描
    public String exportAllTables(MsgHandler handler, Integer projectId, Integer documentId) throws Exception {
        YtbLog.logDebug("Enter TagTableServiceRestProcess::exportAllTables",handler.req);

        //检查唯一索引等
        checkTemplate(documentInfo);

        //项目中心模板不是自己的文档不能导出表数
        checkTemplateOwner(handler,projectId,documentId);

        String msgBody = "";

        //需求说明书导出工作岗位
        if (documentInfo.isTemplateReq()) {

            // 需求文档导出工作岗位
            TagTableProjectWorkjob tagTableProjectWorkjob = new TagTableProjectWorkjob();
            List<Integer> workjobList = tagTableProjectWorkjob.exportTableProjectWorkjob(documentInfo, projectId, documentId);

            // 修改工作计划书的工作岗位
            new TagTableProjectService().initWorkplanByreq(documentInfo, templateDocumentFactroy, documentInfo,
                    handler.getUserContext(), projectId, documentId);
            // 导出需求目录
            new TagDocumentRefService().exportDocumentDirList(projectId, documentId);
            msgBody = "{\"project_workjob\":" + workjobList + "}";

        } else {

            //甲方计划书不用导出工作组
            if(documentInfo.isTemplateWorkplan()) {
                Boolean isPa = handler.req.getMsgBody().getBoolean("isPa");
                documentInfo.setPa(isPa == null ? false : isPa);
            }

            List<String> tables = getTagTableService().exportAllTables(documentInfo, projectId, documentId);
            //tagTableRef处理
            TemplateDocumentServiceImpl.getTemplateDocumentService().tagTableRefHandler(projectId, documentId);

            msgBody = "{\"list\":" + YtbUtils.toJSONString(tables) + "}";

        }

        YtbLog.logDebug("Exit TagTableServiceRestProcess::exportAllTables", msgBody);
        return msgBody;

    }

    public MsgResponse process(MsgHandler handler) throws Exception {

        int retcode = 0;
        String retmsg = "成功";
        String msgBody = "{}";

        RefTagTableParam rp = RefTagTableParam.parse (handler.req.msgBody.toJSONString());
        Integer projectId = rp.getProjectId();
        Integer documentId = rp.getDocumentId();
        //Integer repositoryId = rp.getRepositoryId();
        if (documentId!=null &&documentId != 0)
        {
            parseTemplate(projectId, documentId);
        }

        switch (handler.req.cmd) {

            //导出文档中所有关联表：一次扫描
            case "exportAllTables": {

                msgBody = exportAllTables(handler, projectId, documentId);

                break;
            }

            case "refTagTableParam": {
                msgBody = JSONObject.toJSONString(new TagDocumentRefService().refTagTableParam(rp));
                break;
            }
            //测试函数
            case "testRefTagTableParam": {
                msgBody = JSONObject.toJSONString(new TagDocumentRefService().testRefTagTableParam(rp));
                break;
            }
            case "refDocumentReqDir": {
                msgBody = "{\"list\":" + JSONObject.toJSONString(new TagDocumentRefService().refReqDir(rp)) + "}";
                break;
            }

            case "selectDocument": {
                String tableName = handler.req.msgBody.getString("tableName");
                List<Map<String, Object>> lst = getTagTableService().selectDocument(0, documentId, tableName);
                msgBody = "{\"list\":" + JSONObject.toJSONString(lst) + "}";
                break;
            }

            case "selectTable": {
                String tableName = handler.req.msgBody.getString("tableName");
                Integer refDcumentId = handler.req.msgBody.getInteger("refDocumentId");
                String dbName = handler.req.msgBody.getString("dbName");
                if (dbName != null) {
                    tableName = dbName + "." + tableName;
                }
                List<Map<String, Object>> lst = getTagTableService().selectTable(projectId,
                                refDcumentId != null && refDcumentId > 0 ? refDcumentId : documentId, tableName);
                msgBody = "{\"list\":" + JSONObject.toJSONString(lst) + "}";
                break;
            }

            //下拉框查询岗位 来自需求说明书
            case "project_workjob": {
                Integer repositoryId = documentInfo.getdBDocHeader().getRepositoryId();
                Integer reqId = DocumentRefService.getDocumentRefService().findRef(repositoryId, projectId,
                        documentId, (short) Dict_TemplateModel.Template_TYPE_req);

                List<Map<String, Object>> lst = getTagTableService().selectTable(projectId, reqId, "project_workjob");
                msgBody = "{\"list\":" + JSONObject.toJSONString(lst) + "}";
                break;
            }

            //下拉框选 人员
            case "project_member": {

                YtbLog.logDebug("Enter TagTableServiceRestProcess::project_member",handler.req);

                Collection<SelectProjectMember.DayPayInfoResult> dayPayInfos =
                        new TagTableWorkGroup() .selectProjectMember(handler.getUserContext().getLoginSso(), projectId, documentId);
                msgBody = "{\"list\":" + JSONObject.toJSONString(dayPayInfos) + "}";

                YtbLog.logDebug("Exit TagTableServiceRestProcess::project_member",msgBody);
                break;

            }

            //下拉框选 岗位任务
            case "selectProjectDutyTask": {
                List<Map<String, Object>> lst = getTagTableService().selectSp(   projectId, documentId,
                        "ytb_project.spSelectProjectDutyTask");
                msgBody = "{\"list\":" + JSONObject.toJSONString(lst) + "}";
                break;
            }
            //项目费率
            case "project_rate": {
                ProjectRateModel project_Rate = getTagTableService().getProjectRate (projectId);
                msgBody = "{\"list\":[" + JSONObject.toJSONString(project_Rate) + "]}";
                break;
            }

            //标签参数表更新文档接口
            case "modifyDocByTagTableParam": {
                String tableName = getTagTableService().modifyDocByTagTableParam(documentInfo, projectId, documentId);
                msgBody = "{'tableName':'" + tableName + "'}";
                break;

            }
            default: {
                throw new YtbError(YtbError.CODE_INVALID_REST);
            }

        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }


}
