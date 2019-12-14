package ytb.project.model;

import ytb.common.context.model.Ytb_ModelSkipNull;

import java.math.BigDecimal;

/**
 * Package: ytb.project.model
 * Author: ZCS
 * Date: Created in 2019/1/8 18:57
 * e.event_type as eventType,e.fee,e.event_user_id,p.p_phase_id
 */


public class ProjectEventViewModel extends Ytb_ModelSkipNull {

    private Integer phaseId = 0;
    private String partyA = "";
    private String partyB = "";
    private Integer eventUserId = 0;
    private Integer eventType = 0;
    private String eventTime = "";
    private String remark = "";
    private BigDecimal totalFee = BigDecimal.ZERO;
    private BigDecimal fee = BigDecimal.ZERO;

    private int eventId = 0;
    //0--默认、1--审核、10--支付、100--协助
    private int serviceType =0;

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;

    }

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getPartyA() {
        return partyA;
    }

    public void setPartyA(String partyA) {
        this.partyA = partyA;
    }

    public String getPartyB() {
        return partyB;
    }

    public void setPartyB(String partyB) {
        this.partyB = partyB;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }



    public Integer getEventUserId() {
        return eventUserId;
    }

    public void setEventUserId(Integer eventUserId) {
        this.eventUserId = eventUserId;
    }


    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public Integer getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(Integer phaseId) {
        this.phaseId = phaseId;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

}
