package ytb.manager.templateexcel.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ytb.common.utils.YtbSql;
import ytb.manager.context.ManagerSrvContext;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentInfo;
import ytb.manager.template.model.Dict_WorkJobModel;
import ytb.manager.template.model.Dict_document;
import ytb.manager.templateexcel.analysis.ExcelAnalysis;
import ytb.manager.templateexcel.common.YtbTableDict;
import ytb.manager.templateexcel.common.YtbTemplate;
import ytb.manager.templateexcel.service.CheckTemplateJobRepeatService;
import ytb.common.context.model.YtbError;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CheckTemplateJobRepeatServiceImpl
 * @Description TODO
 * @Author qsy
 * @Date 2019/4/22 15:10
 **/
public class CheckTemplateJobRepeatServiceImpl extends TemplateDocumentServiceImpl implements CheckTemplateJobRepeatService {


    // 计数
    Dict_WorkJobModel countDictWorkJobModel(Map<Dict_WorkJobModel, Integer> workJobModelMap,
                                            int workJobId,
                                            String workJobName) {
        for (Dict_WorkJobModel model : workJobModelMap.keySet()) {
            if (model.getWorkJobId() == workJobId) {
                workJobModelMap.put(model,workJobModelMap.get(model)+1);
                return model;
            }
        }
        Dict_WorkJobModel workJobModel = new Dict_WorkJobModel();
        workJobModel.setWorkJobId(workJobId);
        workJobModel.setWorkJobTypeName(workJobName);
        workJobModelMap.put(workJobModel, 1);
        return workJobModel;

    }

    public void checkJobRepeat (TemplateDocumentInfo info) {

        JSONObject documentBody = parseBodyByDocument(info.getDocJson().toJSONString());

        //表格行内容
        Map<Dict_WorkJobModel, Integer> workJobModelMap = new HashMap<>();
        for (Object row : documentBody.getJSONArray("value")) {
            if (row != null) {
                int workJobId = getWorkJobId(((JSONObject) row));
                String workJobName = getWorkJobName(((JSONObject) row));
                countDictWorkJobModel(workJobModelMap, workJobId, workJobName);
            }
        }

        for (Dict_WorkJobModel model : workJobModelMap.keySet()) {
            int workJobNumberMax = getWorkJobNumberMax(model.getWorkJobId());
            if (workJobModelMap.get(model) > workJobNumberMax) {
                String msg = model.getWorkJobTypeName() + YtbTemplate.CHECK_JOB_MAX_NUMBER_MSG + workJobNumberMax;
                throw new YtbError(YtbError.CODE_USER_ERROR, msg);
            }
        }

    }

    public void checkJobRepeat(int documentId) throws UnsupportedEncodingException {
        Dict_document dict_document = ManagerSrvContext.getInst().getTemplateDocumentService().getDocumentBy(documentId);
        TemplateDocumentInfo info = JSONObject.parseObject(new String(dict_document.getDocument(),
                "UTF-8"), TemplateDocumentInfo.class);
        checkJobRepeat(info);
    }


    public JSONObject parseBodyByDocument(String document) {
        //获取表格的body
        return ExcelAnalysis.getTagTable(JSONObject.parseObject(document).getJSONArray("body"),
                YtbTableDict.PROJECT_TABLE_WORK_GROUP_TASK);

    }

    String findCell(JSONArray cellDataArray, String keyFieldName) {
        for (Object object : cellDataArray) {
            JSONObject cellData = (JSONObject) object;
            if (cellData.get("refField") != null && cellData.get("refField").equals(keyFieldName)) {
                return cellData.get("refValue") == null ? "" : cellData.get("refValue").toString();

            }
            if (cellData.get("fieldName") !=null && cellData.get("fieldName").equals(keyFieldName)) {
                return cellData.get("text") == null ? "" : cellData.get("text").toString();

            }
        }
        return "";
    }

    public String getWorkJobName(JSONObject row) {
        return findCell(row.getJSONArray("items"), YtbTableDict.WORK_GROUP_TASK_FIELD_WORK_JOB);
    }


    public int getWorkJobId(JSONObject row) {

        String workJobId = findCell(row.getJSONArray("items"), YtbTableDict.WORK_GROUP_TASK_FIELD_WORK_JOB_ID);
        return Integer.valueOf(workJobId);
    }


    public int getWorkJobNumberMax(int workJobId ) {
        if (workJobId == YtbTemplate.UNCHECK_WORK_JOB_ID) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, YtbTemplate.WORK_JOB_ID_DISC + workJobId);
        }
        StringBuilder sql = new StringBuilder();
        sql.append("select  *  from  ytb_manager.dict_work_job  where  work_job_id=" + workJobId);
        return YtbSql.selectOne(sql, Dict_WorkJobModel.class).getWorkJobNumber();
    }

}
