package ytb.project.view.service.impl;

import ytb.project.context.ProjectSrvContext;
import ytb.project.view.model.ProjectTaskViewModel.ViewCustomTaskModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewMeetingNoticeModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewShipmentNumberModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewTaskModel;
import ytb.user.model.UserInfoModel;
import ytb.user.service.UserCenterService;

import java.util.ArrayList;
import java.util.List;

public final class ViewTaskService {
    static ProjectSrvContext getInst() {
        return ProjectSrvContext.getInst();
    }


    public ViewTaskModel selectViewTaskModel(int projectId, int phase, int userId) {
        ViewTaskModel vm = new ViewTaskModel();
        UserCenterService userCenterService = getInst().getUserCenterService();

        //查询自定义任务
        List<ViewCustomTaskModel> cstList = getInst().getProjectFlowService().selectViewCustomTaskModel(projectId,
                phase, userId);

        //查询物流单号
        List<ViewShipmentNumberModel> shipList = getInst().getProjectFlowService().selectViewShipmentNumberModel
                (projectId, phase, userId);

        //查询会议通知
        List<ViewMeetingNoticeModel> mnList = getInst().getProjectFlowService().selectViewMeetingNoticeModel(projectId, phase, userId);

        for (ViewMeetingNoticeModel m : mnList) {
            UserInfoModel um = userCenterService.getUserInfoById(Integer.parseInt(m.getSponsor()));
            m.setSponsor(um.getNickName());

            if (!m.getParticipant().isEmpty()) {
                List<String> nameList = new ArrayList<>();
                for (String s : m.getParticipant().split(",")) {
                    um = userCenterService.getUserInfoById(Integer.parseInt(s));
                    nameList.add(um.getNickName());
                }
                m.setParticipantNameList(nameList);
            }

        }

        for (  ViewCustomTaskModel m: cstList) {

            if (!m.getReceiver().isEmpty()) {
                 List<String> nameList=new ArrayList<>();

                for (String s : m.getReceiver().split(",")) {
                    UserInfoModel userLoginModel = userCenterService.getUserInfoById(Integer.parseInt(s));
                    nameList.add(userLoginModel.getNickName());
                }
                m.setReceiverNameList(nameList);
            }

        }

        vm.setCustomTaskList (cstList);
        vm.setShipmentNumberList(shipList);
        vm.setMeetingNoticeList(mnList);

        return vm;
    }
}
