package ytb.project.rest.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import ytb.bangbang.model.FriendsInfoModel;
import ytb.common.utils.pageutil.PageUtils;
import ytb.common.utils.pageutil.Query;
import ytb.common.utils.YtbUtils;
import ytb.project.common.ProjectConstState;
import ytb.project.context.ProjectContext;
import ytb.project.context.ProjectSrvContext;
import ytb.project.context.UserProjectContext;
import ytb.project.model.projectFolderView.templateFolder.TemplateFolderModel;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectReportModel;
import ytb.project.model.UserCollectionProModel;
import ytb.project.model.projectview.ProjectResultViewModel;
import ytb.project.service.ProjectFlowService;
import ytb.project.service.impl.release.ReleaseInfo;
import ytb.project.service.impl.release.ReleaseResult;
import ytb.project.service.template.TemplateFolderService;
import ytb.project.service.IReleaseView;
import ytb.project.service.project.request.ProjectApply;
import ytb.manager.template.model.Dict_ProjectTypeModel;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectRelease {

    static ProjectSrvContext getInst(){
        return ProjectSrvContext.getInst();
    }


    public MsgResponse process(MsgRequest req, MsgHandler handler) throws UnsupportedEncodingException {
        int retcode = 0;
        String retmsg = "成功";
        String msgBody = "{}";

        IReleaseView releaseView = getInst().getReleaseView();

        //获取用户项目上下文
        UserProjectContext context = UserProjectContext.pre(handler);
        Integer talkId = context.getTalkId();
        Integer userId = context.getUserId();
        Integer companyId = context.getCompanyId();

        //查询项目分类列表（项目中心页面列表数据展示）
        if (req.cmd.equals("getProjectList")) {
            Integer status = req.msgBody.getInteger("status");

            ProjectResultViewModel resultViewModel = releaseView.getProjectLists(context, status);
            msgBody = "{\"list\":" +resultViewModel+ "}";
        }


        //查询邀请、申请列表
        else if(req.cmd.equals("getInviteList")){
            int status = req.msgBody.getInteger("type");
            int reqType = req.msgBody.getInteger("reqType");//reqType:1：邀请  2：申请

            Map queryMap =new HashMap();
            queryMap.put("userId",userId);
            queryMap.put("page",req.msgBody.getInteger("currPage"));
            queryMap.put("limit",req.msgBody.getInteger("pageSize"));

            if (reqType == 1) {
                PageUtils pageUtils = releaseView.getProjectInviteDAOService().getInviteList(queryMap, status);
                msgBody = "{\"list\":" + YtbUtils.toJSONStringSkipNull(pageUtils) + "}";

            } else if (reqType == 2) {

                PageUtils pageUtils = releaseView.getProjectInviteDAOService().getApplyList(queryMap, status);
                msgBody = "{\"list\":" + YtbUtils.toJSONStringSkipNull(pageUtils) + "}";

            }

        }


        //任务发布加工项目
        else if(req.cmd.equals("releaseProjectPp")){
            int projectTypeId = req.msgBody.getInteger("projectTypeId");
            int templateId = req.msgBody.getInteger("templateId");
            int customTaskId = req.msgBody.getInteger("customTaskId");
            String projectName = req.msgBody.getString("projectName");
            ReleaseInfo releaseInfo=new ReleaseInfo();
            releaseInfo.setProjectTypeId(projectTypeId);
            releaseInfo.setReqTemplateId(templateId);
            releaseInfo.setProjectName(projectName);
            releaseInfo.setCustomTaskid(customTaskId);
            ReleaseResult releaseProjectResult=releaseView.releaseProjectPp(context,releaseInfo,"");
            msgBody="{\"list\":"+ YtbUtils.toJSONStringSkipNull(releaseProjectResult)+ "}";
        }

        //发布项目第一步
        else if(req.cmd.equals("releaseProject")){

            Integer projectTypeId = req.msgBody.getInteger("projectTypeId");
            String projectName = req.msgBody.getString("projectName");
            boolean isPublish = req.msgBody.getBoolean("isPublish");//是否平台发布
            boolean isInvite = req.msgBody.getBoolean("isInvite");//是否邀请好友
            String friends = req.msgBody.getString("friends");

            ProjectModel pm = new ProjectModel();
            pm.setProjectName(projectName);
            pm.setProjectTypeId(projectTypeId);
            pm.setIsPublish(isPublish);
            pm.setInvite(isInvite);
            pm.setUserId1(userId);
            pm.setCompanyId1(companyId);
            pm.setOldTalkId(0);
            context.setProjectModel(pm);
            context.buildDict_ProjectTypeModel();

            msgBody = releaseView.releaseProject(context,friends).toString();
        }

        //第二步(保存,预览,发布)
        else if (req.cmd.equals("releaseProjectNext")) {
            int publishState = req.msgBody.getInteger("type");
            Integer templateId = req.msgBody.getInteger("templateId");
            Boolean isTaskPublish = req.msgBody.getBoolean("isTaskPublish");
            isTaskPublish = isTaskPublish == null ? false : isTaskPublish;
            String ret = "";
            if (isTaskPublish) {
                Integer newProjectId = req.msgBody.getInteger("newProjectId");
                context = new UserProjectContext().buildModelProject(newProjectId);
                context.setProjectTalkModel(null);
                ret = releaseView.releaseProjectNext(context, publishState, templateId, isTaskPublish);

            } else {

                ret = releaseView.releaseProjectNext(context, publishState, templateId, isTaskPublish);
            }
            msgBody = "{\"list\":" + YtbUtils.toJSONStringSkipNull(ret) + "}";
        }


        //多条件搜索项目
        else if(req.cmd.equals("selectProjectList")){

            Map map =new HashMap();
            map.put("week",req.msgBody.getString("week"));
            map.put("month",req.msgBody.getString("month"));
            map.put("year",req.msgBody.getString("year"));
            map.put("projectName",req.msgBody.getString("projectName"));
            map.put("projectTypeId",req.msgBody.getString("projectTypeId"));
            map.put("page",req.msgBody.getInteger("currPage"));
            map.put("limit",req.msgBody.getInteger("pageSize"));

            Query query = new Query(map);
            List<Map<String, Object>> list = releaseView.getProjectDAOService().selectProjectList(query);
            int count = releaseView.getProjectDAOService().getProjectListCount(map);
            PageUtils pageUtil = new PageUtils(list, count, query.getLimit(), query.getPage());
            msgBody="{\"list\":"+ JSONObject.toJSONString(pageUtil, SerializerFeature.WriteMapNullValue)+"}";
        }
        //查询项目分类
        else if(req.cmd.equals("getProjectTypeList")){

            int parentId = req.msgBody.getInteger("parentId");
            List<Dict_ProjectTypeModel> list =  getInst().getProjectCacheManager().findDictProjectTypeModels(parentId);
            msgBody="{\"list\":"+JSON.toJSON(list)+"}";
        }

        //获取通讯录好友列表
        else if(req.cmd.equals("getUserFriendList")){

            String remark = req.msgBody.getString("remark");
            List<FriendsInfoModel> list = releaseView.getUserFriends(userId,remark);
            msgBody = "{\"list\":" + JSON.toJSON(list) + ",\"count\":"+list.size()+"}";
        }

        //审核
        else if(req.cmd.equals("verifyProject")){

            int projectId = req.msgBody.getInteger("projectId");
            releaseView.auditProject(projectId, 4);
        }

        //审核邀请、申请
        else if (req.cmd.equals("approvalPrjApply")) {
            String type = req.msgBody.getString("type");//pb、
            int projectId = req.msgBody.getInteger("projectId");
            int eventType = req.msgBody.getInteger("eventType");//通过不通过的状态（1邀请中/2已邀请/3已拒绝）

            if (talkId == null) {
                throw new YtbError(YtbError.REQ_PARAMETER_NULL, "talkId");
            }
            if (("pm").equals(type)) { //工作组选人的邀请

                releaseView.confirmInvitePm(context,projectId, eventType, userId);

            } else {                //int inviteType = req.msgBody.getInteger("invityType");//1邀请  2申请
                //发布项目时邀请的确认
                ProjectContext pc = releaseView.confirmProjectApply(context, eventType);
                msgBody = "{'result':" + pc + "}";
            }
        }

        //终止项目列表
        else if(req.cmd.equals("getEndProject")){
            Integer status = req.msgBody.getInteger("status");
            Map<String,Object> queryMap = new HashMap<>();
            queryMap.put("userId",userId);
            queryMap.put("status",status);
            queryMap.put("page",req.msgBody.getInteger("currPage"));
            queryMap.put("limit",req.msgBody.getInteger("pageSize"));
            Query query = new Query(queryMap);
            List<Map<String, Object>> list = releaseView.getProjectDAOService().getEndProject(query);
            for(Map map : list){
                if((Integer)map.get("status") ==ProjectConstState.PUBLISH_STOP){
                    map.put("phaseStatus","发布终止");
                    map.put("finishTime",map.get("stopTime"));
                }else{
                    if((Integer)map.get("phase") == ProjectConstState.CHNAGE_TYPE_TALK_TERM){
                        map.put("phaseStatus","洽谈终止");
                        map.put("finishTime",map.get("updateTime"));
                    }else if((Integer)map.get("phase") == ProjectConstState.CHNAGE_TYPE_STOP){
                        map.put("phaseStatus","协作终止");
                        map.put("finishTime",map.get("updateTime"));
                    }
                }
                if(null != map.get("companyName")){
                    map.put("nickName",map.get("nickName")+"-"+map.get("companyName"));
                }
            }
            ProjectApply projectRequest = new ProjectApply();
            int count =projectRequest.getEndProjectCount(queryMap);
            PageUtils pageUtil = new PageUtils(list, count, query.getLimit(), query.getPage());
            msgBody="{\"list\":"+ JSONObject.toJSONString(pageUtil, SerializerFeature.WriteMapNullValue)+"}";
        }

        //申请项目
        else if(req.cmd.equals("applyPrj")){
            String reqRemark = req.msgBody.getString("reqRemark");
            int retTalkId = releaseView.applyProject(context,reqRemark);
            msgBody="{'talkId':" + retTalkId + "}";

        }

        //判断是否重复申请下面项目
        else if (req.cmd.equals("judgeApplyPro")) {
            Integer projectId = req.msgBody.getInteger("projectId");
            userId = null;
            companyId = null;
            Integer userType = handler.getUserContext().getLoginSso().getLoginSsoJson().getUserType();
            if (userType == 1) {
                userId = handler.getUserContext().getLoginSso().getLoginSsoJson().getUserId();
            } else {
                companyId = handler.getUserContext().getLoginSso().getLoginSsoJson().getCompanyId();

            }
            Integer count = ProjectFlowService.getTalkService().getTalkByProjectAndUser(userId, companyId,
                    projectId);
            msgBody = "{\"list\":" + JSONObject.toJSONString(count, SerializerFeature.WriteMapNullValue) + "}";

        }


        else if(req.cmd.equals("selectCompletePro")){

            Map<String,Object> map = new HashMap<>();
            map.put("userId",userId);
            map.put("offset",null);
            map.put("limit",null);
            List<Map<String, Object>> list =  releaseView.getProjectDAOService().selectFinishProject(map);
            msgBody ="{\"list\":" + JSON.toJSONString(list) + "}";

        }

        //获取收到的申请的详情记录
        else if(req.cmd.equals("clickInviteList")){

            int projectId = req.msgBody.getInteger("projectId");
            Map<String,Object> queryMap = new HashMap<>();
            queryMap.put("userId",userId);
            queryMap.put("projectId",projectId);
            queryMap.put("page",req.msgBody.getInteger("currPage"));
            queryMap.put("limit",req.msgBody.getInteger("pageSize"));

            Query query = new Query(queryMap);
            List<Map<String, Object>> list =  releaseView .getProjectInviteDAOService().getInviteRecDetailsList( query);
            int count = releaseView.getProjectInviteDAOService().countInviteRecordsRec(projectId);
            PageUtils pageUtil = new PageUtils(list, count, query.getLimit(), query.getPage());

            msgBody="{\"list\":"+ JSONObject.toJSONString(pageUtil, SerializerFeature.WriteMapNullValue)+"}";
        }

        //我的关注右侧项目列表数据
        else if(req.cmd.equals("getUserSendProList")){
            Map<String,Object> map = new HashMap<>();
            map.put("userId",req.msgBody.getInteger("userId"));
            map.put("page",req.msgBody.getInteger("currPage"));
            map.put("limit",req.msgBody.getInteger("pageSize"));

            Query query = new Query(map);
            List<Map<String, Object>> list = releaseView.getProjectDAOService().getUserSendPro(query);

            int count = releaseView.getProjectDAOService().getUserSendProCount(req.msgBody.getInteger("userId"));
            PageUtils pageUtil = new PageUtils(list, count, query.getLimit(), query.getPage());
            msgBody="{\"list\":"+ JSONObject.toJSONString(pageUtil, SerializerFeature.WriteMapNullValue)+"}";

        }

        //获取用户的收藏列表
        else if(req.cmd.equals("getUserCollectionList")){

            Integer userType = handler.getUserContext().getLoginSso().getLoginSsoJson().getUserType();
            if (userType == 1) {
                companyId = null;
            } else {
                userId = null;
            }

            Map<String,Object> map = new HashMap<>();
            map.put("page",req.msgBody.getInteger("currPage"));
            map.put("limit",req.msgBody.getInteger("pageSize"));
            map.put("userId",userId);
            map.put("companyId",companyId);

            Query query = new Query(map);
            List<Map<String, Object>> list = releaseView.getUserCollectionList(query);

            int count = releaseView.getUserCollectionCount(query);
            PageUtils pageUtil = new PageUtils(list, count, query.getLimit(), query.getPage());
            msgBody="{\"list\":"+ JSONObject.toJSONString(pageUtil, SerializerFeature.WriteMapNullValue)+"}";
        }

        //添加用户收藏
        else if(req.cmd.equals("addUserCollection")){
            Integer projectId = req.msgBody.getInteger("projectId");
            Integer userType = handler.getUserContext().getLoginSso().getLoginSsoJson().getUserType();
            if (userType == 1) {
                companyId = 0;
            } else {
                userId = 0;
            }
            UserCollectionProModel userCollectionProModel = new UserCollectionProModel();
            userCollectionProModel.setCompanyId(companyId);
            userCollectionProModel.setUserId(userId);
            userCollectionProModel.setProjectId(projectId);
            releaseView.addUserCollection(userCollectionProModel);
            releaseView. addFavorite(context, projectId, 1);
        }

        //取消收藏
        else if(req.cmd.equals("deleteUserCollection")){
            Integer userType = handler.getUserContext().getLoginSso().getLoginSsoJson().getUserType();
            if (userType == 1) {
                companyId = null;
            } else {
                userId = null;
            }
            Map<String,Object> map = new HashMap<>();
            map.put("userId",userId);
            map.put("companyId",companyId);
            map.put("collectionId",req.msgBody.getInteger("collectionId"));

            releaseView.deleteUserCollection(map);
        }

        //添加举报
        else if(req.cmd.equals("addProReport")){

            Integer toUserId = req.msgBody.getInteger("toUserId");//被举报的人
            Integer toCompanyId = req.msgBody.getInteger("toCompanyId");//被举报的公司

            String reportType = req.msgBody.getString("reportText");//举报内容
            String remark = req.msgBody.getString("remark");//举报类别
            Integer userType = handler.getUserContext().getLoginSso().getLoginSsoJson().getUserType();
            if (userType == 1) {
                companyId = null;
            } else {
                userId = null;
            }
            ProjectReportModel  projectModel= new ProjectReportModel();
            projectModel.setUserId(userId);
            projectModel.setCompanyId(companyId);
            projectModel.setReportType(reportType);
            projectModel.setToUserId(toUserId);
            projectModel.setToCompanyId(toCompanyId);
            projectModel.setRemark(remark);
            releaseView.addProjectReport(projectModel);

        }else if(req.cmd.equals("getcompleteProInfo")){
            Integer projectId = req.msgBody.getInteger("projectId");
            releaseView.getFinishProjectView(userId,projectId);
        }

        //设置项目的浏览数
        else if(req.cmd.equals("setProjectViewNumber")){
            Integer projectId = req.msgBody.getInteger("projectId");

            if(projectId == null){
                throw new YtbError(YtbError.CODE_INVALID_REST);
            }
            releaseView.addViews(projectId);

        }
        //获取发布、洽谈的项目
        else if(req.cmd.equals("getCompanySendAndTalkPro")){

            List<Map<String,Object>> list = releaseView.getProjectDAOService().getCompanySendAndTalkPro(companyId);
            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";

        }

        //我的文件库
        else if(req.cmd.equals("myFileFactory")){

            Integer userType = context.getLoginSso().getLoginSsoJson().getUserType();
            releaseView.getProjectDAOService().getUserSendAndTalkPro(userId);
            //获取用户ID
            List<TemplateFolderModel> lst = userType == 1 ?
                    TemplateFolderService.getTemplateFolderService().findMyFileDocument(context ):
                    TemplateFolderService.getTemplateFolderService().findMyFileDocument_company(context);

            msgBody="{\"list\":"+ YtbUtils.toJSONStringSkipNull(lst)+"}";
        }

        //工作组邀请好友
        else if(req.cmd.equals("inviteMember")){
            int projectId = req.msgBody.getInteger("projectId");
            int documentId = req.msgBody.getInteger("documentId");
            String userIds = req.msgBody.getString("userId");
            releaseView.inviteMember(context,userIds,documentId);
        }


        else if(req.cmd.equals("getProjectById")){
            int projectId = req.msgBody.getInteger("projectId");
            ProjectModel projectModel = releaseView.getProjectDAOService().getProjectById(projectId);
            msgBody ="{\"projectModel\":"+ JSON.toJSONString(projectModel)+"}";
        }
        return handler.buildMsg(retcode, retmsg, msgBody);
    }



}
