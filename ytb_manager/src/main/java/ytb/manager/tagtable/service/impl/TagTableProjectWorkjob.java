package ytb.manager.tagtable.service.impl;

import com.alibaba.fastjson.JSONObject;
import ytb.common.utils.YtbSql;
import ytb.manager.tagtable.model.DBReqItem;
import ytb.manager.tagtable.model.DBReqItemList;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentInfo;
import ytb.manager.template.model.Dict_Req_Item;
import ytb.manager.template.service.impl.ReqItemServiceImpl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class TagTableProjectWorkjob extends TagTableWorkGroup {


    boolean find(DBReqItemList dbList, Dict_Req_Item item) {
        for (DBReqItem ritem : dbList.getReqItemList()) {
            if (item.getReqItemNo().equals(ritem.getReqItemNo())
                    && item.getValue() == ritem.getValue()) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> exportTableProjectWorkjob( TemplateDocumentInfo docInfo,int projectId, int documentId) throws UnsupportedEncodingException {

        DBReqItemList dbList =
                DBReqItemList.parseDBReqItemList(JSONObject.toJSONString(docInfo.getDocJson()).getBytes("UTF-8"));
        List<Dict_Req_Item>  stdLst =
                new ReqItemServiceImpl().queryListByTemplateId(dbList.getHeader().getTemplateId());
        List<Dict_Req_Item> itemList = new ArrayList<Dict_Req_Item>();
        for (Dict_Req_Item item : stdLst) {
            if (find(dbList, item)) {
                itemList.add(item);
            }
        }
        deleteTablePrjectWorkjob(projectId,documentId);
        int talkId=docInfo.getdBDocHeader().getTalkId();
        inner_exportTablePrjectWorkjob_default(talkId,projectId, documentId);
        List<Integer> integerList = inner_exportTablePrjectWorkjob(talkId,projectId, documentId, itemList);
        YtbSql.spDb("ytb_project.spUpdateProjectWorkjobAlias",projectId,documentId);
        return integerList;
    }

    void deleteTablePrjectWorkjob( int projectId, int documentId ) {
        StringBuilder sql=new StringBuilder();
        sql.append(" delete from ").append(TABLE_NAME_project_workjob);
        sql.append(" where project_id=").append(projectId);
        sql.append(" and document_id=").append(documentId);
        this.updateSql(sql);
    }

    List<Integer> inner_exportTablePrjectWorkjob(int talkId,int projectId, int documentId, List<Dict_Req_Item> items) {

        List<Integer> workJobs =new ArrayList<>();
        for (Dict_Req_Item item : items) {
            StringBuilder sql = new StringBuilder(256);
            sql.append("insert into ").append(TABLE_NAME_project_workjob);
            sql.append("(talk_id,project_id,document_id,req_item_id,work_job_id,work_job,is_default) ");
            sql.append("values(").append(talkId).append(",").append(projectId).append(",").append(documentId).append(",");
            sql.append(item.getItemId()).append(",").append(item.getWorkJobId()).append(",'");
            sql.append(item.getWorkJobName()).append("'").append(",").append(0).append(")");

            int workJobId = updateSql(sql);
            workJobs.add(workJobId);

        }
        return workJobs;
    }

    public void inner_exportTablePrjectWorkjob_default(int talkId, int projectId, int documentId) {

        StringBuilder sql = new StringBuilder(256);

        sql.append("insert into ").append(TABLE_NAME_project_workjob);
        sql.append("(talk_id,project_id,document_id,req_item_id,work_job_id,work_job,is_default) ");
        sql.append("select ").append(talkId).append(",").append(projectId).append(",");
        sql.append(documentId).append(",0,");
        sql.append(" b.work_job_id,b.title,b.is_default ");
        sql.append(" from  ytb_project.vw_project_type_by_project a, ");
        sql.append(" vw_template_repository_workjob b ");
        sql.append(" where a.projectId=").append(projectId);
        sql.append(" and a.repositoryId=b.repository_id ");
        sql.append(" and b.is_default=1 ");

        YtbSql.update(sql);
    }
}

