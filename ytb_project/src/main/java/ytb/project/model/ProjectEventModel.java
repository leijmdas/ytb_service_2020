package ytb.project.model;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ytb.common.context.model.Ytb_ModelSkipNull;

import java.math.BigDecimal;
import java.util.Date;

@Component
@Scope("prototype")
public class ProjectEventModel extends Ytb_ModelSkipNull {
    public final static int EVENT_SERVICE_START = 0;
    public final static int EVENT_SERVICE_END = 10;
    //0默认
    public final static int EVENT_SERVICE_DEFUALT = EVENT_SERVICE_START;
    //1审核
    public final static int EVENT_SERVICE_AUDIT = 1;
    //支付
    public final static int EVENT_SERVICE_PAY = EVENT_SERVICE_END;
    //协助
    public final static int EVENT_SERVICE_ASSIST = 100;


    //表主建
    private int eventId;

    //项目阶段id
    private int phaseId;

    //子状态
    private int phaseStatus;

    //事件类型(15审核通过 16审核不通过 )
    private int eventType;

    //事件发起人
    private int eventSponsor;

    //事件关联人
    private int eventAnother;

    //备注
    private String remark;

    //事件相关费用
    private BigDecimal fee;

    //事件发生时间
    private Date eventTime;

    //0--默认 1--审核 10--支付 100--协助
    private int serviceType = 0;

    //0--正常流程701--普通变更702--重大变更800--项目终止
    private int changeType = 0;

    public int getChangeType() {
        return changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }


    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee.setScale(2,BigDecimal.ROUND_HALF_UP);

    }

    public int getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(int phaseId) {
        this.phaseId = phaseId;
    }

    public int getPhaseStatus() {
        return phaseStatus;
    }

    public void setPhaseStatus(int phaseStatus) {
        this.phaseStatus = phaseStatus;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public int getEventSponsor() {
        return eventSponsor;
    }

    public void setEventSponsor(int eventSponsor) {
        this.eventSponsor = eventSponsor;
    }

    public int getEventAnother() {
        return eventAnother;
    }

    public void setEventAnother(int eventAnother) {
        this.eventAnother = eventAnother;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }
}
