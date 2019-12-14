package ytb.common;

import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.testframe.ITestImpl;

import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

public class TagTable extends ITestImpl {
    static String url_project = TestProjectConst.url_project_tagTableService;//"http://localhost/rest/tagTableService/project";
    static String url_manager = TestProjectConst.url_manager_tagTableService;//"http://localhost/rest/tagTableService/manager";


    int projectId = 465;
    int documentId = 2133;

    public String exportAllTablesPa(HttpClientNode httpclient, MsgRequest req,
                                    Flow f) throws Exception {
        exportAllTablesPa(httpclient, req, f.getProjectId(), f.getReq_id());
        return exportAllTablesPa(httpclient, req, f.getProjectId(), f.getWorkplan_id());
    }

    public String exportAllTablesPa(HttpClientNode httpclient, MsgRequest req,
                                    int projectId, int documentId) throws Exception {
        return exportAllTables(httpclient, req, projectId, documentId, true);
    }


    //Flow f
    public String exportAllTables(HttpClientNode httpclient, MsgRequest req, Flow f) throws Exception {
        exportAllTables(httpclient, req, f.getProjectId(), f.getReq_id(), false);
        return exportAllTables(httpclient, req, f.getProjectId(), f.getWorkplan_id(), false);
    }

    public String exportAllTables(HttpClientNode httpclient,MsgRequest req,int projectId,int documentId) throws Exception {
        return exportAllTables(httpclient,req,projectId,documentId,false);
    }

    String exportAllTables(HttpClientNode httpclient, MsgRequest reqCopy,
                           int projectId, int documentId, Boolean isPa) throws Exception {
        this.projectId = projectId;
        this.documentId = documentId;
        MsgRequest req = new MsgRequest();
        req.token = reqCopy.token;
        //req.cmdtype = "tagTableServiceProject";
        req.cmdtype = "tagTableServiceManager";
        req.cmd = "exportAllTables";
        req.msgBody.fluentPut("projectId", projectId);
        req.msgBody.fluentPut("documentId", documentId);
        req.msgBody.fluentPut("isPa", isPa);

        String ret = httpclient.post(url_manager, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
//       dbNodeYtbProject.appendSql("select * from ytb_project.work_group_member where project_id=" ).
//                appendSql(projectId).appendSql(" and  document_id=").appendSql(documentId)
//          .checkRecordNumber(3).execSelect().checkRecord("project_id="+projectId);
//
//        dbNodeYtbProject.appendSql("select * from ytb_project.work_group_member where project_id=" ).
//                appendSql(projectId).appendSql(" and document_id=").appendSql(documentId).appendSql(" and is_admin=2")
//                .checkColumn("project_id="+projectId );
        MsgResponse resp = MsgResponse.parseResponse(ret);
        System.out.println(url_manager);
        System.out.println(resp.toJSONStringPretty());
        System.out.println(req.toJSONStringPretty());
        checkEQ(0, resp.getRetcode());
        return ret;
    }
}
