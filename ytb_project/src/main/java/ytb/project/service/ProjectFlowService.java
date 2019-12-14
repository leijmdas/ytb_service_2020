package ytb.project.service;


import ytb.project.context.UserProjectContext;
import ytb.project.model.CustomTaskResult;
import ytb.project.service.impl.talk.ProjectTalkService;
import ytb.project.service.project.stop.StopApplyResult;
import ytb.project.service.project.stop.impl.StopType;
import ytb.project.view.dao.ITaskDAOService;
import ytb.project.view.model.ProjectTaskViewModel.ViewTaskModel;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface ProjectFlowService extends ITaskDAOService {

   static ProjectTalkService projectTalkService = new ProjectTalkService();
   public static ProjectTalkService getTalkService() {
      return projectTalkService;
   }

   //进入项目终止
   StopApplyResult applyStopProject(UserProjectContext context, int userId, StopType stopType) throws
           UnsupportedEncodingException;

   //确定,取消项目终止状态
   String confirmStopProject(UserProjectContext context,int userId, int status);

   //项目终止页面
   String stopProject(UserProjectContext context,int userId );

   //乙方同意或拒绝,项目正式终止
   void confirmStopProjectPB(UserProjectContext context,int status,int userId);

   //获取集合采购任务
   Map getTask(UserProjectContext context, int userId, int talkId);

   //点击加工采购子任务
   CustomTaskResult chooseTask(UserProjectContext context) throws   UnsupportedEncodingException;

   //去发布
   String goReleasePro(UserProjectContext context, int projectTypeId, int talkId);


   //修改物流单号
   void modifyShipmentNumber(int taskId, String number);

   //获取项目过程记录
   List<Map<String, Object>> getProjectEventList(UserProjectContext context, int talkId, int stage);

   //点击查看任务
   List<Map<String, Object>> clickViewTask(UserProjectContext context, int talkId);

   //添加物流单号
   void addShipmentNumber(int talkId,String remark,String number,String goodsName,String documentName,int userId,int toUserId);

   //添加自定义任务
   void addCustomTask(UserProjectContext context,int talkId ,String receiver,String remark,int docListId,int userId);

   //添加会议任务
   void addMeetingTask(UserProjectContext context,int talkId,int userId,String participant ,String issue ,String startTime, String stopTime,int docListId);

   //查询任务
   ViewTaskModel selectTask(UserProjectContext context, int talkId, int userId);

}
