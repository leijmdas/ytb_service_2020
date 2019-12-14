package ytb.project.view.model.ProjectTaskViewModel;

import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.List;

public class ViewTaskModel extends Ytb_ModelSkipNull {
    List<ViewCustomTaskModel> customTaskList;
    List<ViewShipmentNumberModel> shipmentNumberList;
    List<ViewMeetingNoticeModel> meetingNoticeList;

    public List<ViewCustomTaskModel> getCustomTaskList() {
        return customTaskList;
    }

    public void setCustomTaskList(List<ViewCustomTaskModel> customTaskList) {
        this.customTaskList = customTaskList;
    }

    public List<ViewShipmentNumberModel> getShipmentNumberList() {
        return shipmentNumberList;
    }

    public void setShipmentNumberList(List<ViewShipmentNumberModel> shipmentNumberList) {
        this.shipmentNumberList = shipmentNumberList;
    }

    public List<ViewMeetingNoticeModel> getMeetingNoticeList() {
        return meetingNoticeList;
    }

    public void setMeetingNoticeList(List<ViewMeetingNoticeModel> meetingNoticeList) {
        this.meetingNoticeList = meetingNoticeList;
    }

}
