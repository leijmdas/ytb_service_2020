package ytb.project.service.project.stop.impl;

import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.common.context.model.YtbError;
import ytb.common.context.model.Ytb_Model;
import ytb.project.model.ProjectTalkModel;

import java.util.HashMap;
import java.util.Map;

public class StopType extends Ytb_Model {
    static Map<Short, String> causeMap = new HashMap<>();

    // 1 甲方 2 乙方
    public final static Byte PaPbUser_PA = 1;
    public final static Byte PaPbUser_PB = 2;
    // 200	取消项目
    public final static Short SUBTYPE_PA_CANCEL = 801;
    // 300	乙方交付不合格
    public final static Short SUBTYPE_PB_OFFGRADE = 802;
    // 400	延期率>500%
    public final static Short SUBTYPE_DELAY_500 = 803;
    // 500	甲方不满意	（甲方超过3次审核,仅用于造型界面类）
    public final static Short SUBTYPE_PA_Unsatisfy = 804;
    // 600	乙方不满意
    public final static Short SUBTYPE_PB_Unsatisfy = 805;

    static {
        causeMap.put(SUBTYPE_PA_CANCEL,"甲方取消项目");
        causeMap.put(SUBTYPE_PB_OFFGRADE,"乙方交付不合格");
        causeMap.put(SUBTYPE_DELAY_500,"延期率超过500%");
        causeMap.put(SUBTYPE_PA_Unsatisfy,"造型界面类甲方超过3次审核");
        causeMap.put(SUBTYPE_PB_Unsatisfy,"乙方不满意");
    }

    ComputeStopResult computeResult = new ComputeStopResult();


    // 项目阶段数 	0通用 3阶段 6阶段
    private Byte phase = 0;

    // 当前阶段1-6
    private Byte phaseNow = 0;
    // pa pb 终止提出方 0通用 1甲方 2乙方
    private Byte paPbUser = 0;
    //	项目终止原因   变更类型
    private Short subType = 0;

    private Byte modelFlag =  0;

    //终止原因
    public String getCause(){
        return  causeMap.get(subType);
    }

    public StopType(UserProjectContext context,Byte paPbUser,Short subType){
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        setPhase((byte)pm.getPhaseNo());
        setModelFlag(pm.getModelFlag());
        //setModelFlag((byte)(context.checkExistsModel()?1:0));

        if(pm.getPhaseNo()==3){
            setPhase(getModelFlag() == 0 ? (byte) 30 : (byte) 31);
        }
        setPhaseNow((byte) (ptm.getPhase() - pm.getPhaseStart() + 1));
        this.paPbUser = paPbUser;
        this.subType = subType;
    }

    public void checkParam(UserProjectContext context, IComputeStop computeStop) {
        ProjectModel pm = context.getProjectModel();

        if ( phase != 3 && phase / 10 !=3 && phase != 6) {
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "项目阶段数(3,6)");
        }

        if (phaseNow <= 0 || phaseNow > pm.getPhaseNo()) {
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "项目当前阶段(1--" + pm.getPhaseNo() + ")");
        }

        if (!paPbUser.equals(PaPbUser_PA) && !paPbUser.equals(PaPbUser_PB)) {
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "提出方(1-甲方2-乙方)");
        }
        if (paPbUser.equals(PaPbUser_PA)) {
            if (!subType.equals(SUBTYPE_PA_CANCEL)
                    && !subType.equals(SUBTYPE_PB_OFFGRADE)
                    && !subType.equals(SUBTYPE_DELAY_500)
                    && !subType.equals(SUBTYPE_PA_Unsatisfy)) {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "甲方变更原因(取消项目,乙方交付不合格, 延期率>500%,甲方不满意)");
            }


        } else if (paPbUser.equals(PaPbUser_PB)) {
            if (!subType.equals(SUBTYPE_PB_Unsatisfy)) {
                throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG, "乙方变更原因(乙方不满意)");
            }
        }

        if (SUBTYPE_DELAY_500.equals(subType)) {
            long delay = computeStop.computeDelayRate(context);
            if (delay < 500) {
                throw new YtbError(YtbError.CODE_DEFINE_ERROR, "延期率低于500%,>=500%才可以成为终止理由!");
            }
        }
    }

    public Byte getModelFlag() {
        return modelFlag;
    }

    public void setModelFlag(Byte modelFlag) {
        this.modelFlag = modelFlag;
    }

    public Byte getPhaseNow() {
        return phaseNow;
    }

    public void setPhaseNow(Byte phaseNow) {
        this.phaseNow = phaseNow;
    }

    public Byte getPhase() {
        return phase;
    }

    public void setPhase(Byte phase) {
        this.phase = phase;
    }

    public Byte getPaPbUser() {
        return paPbUser;
    }

    public void setPaPbUser(Byte paPbUser) {
        this.paPbUser = paPbUser;
    }

    public Short getSubType() {
        return subType;
    }

    public void setSubType(Short subType) {
        this.subType = subType;
    }


    public ComputeStopResult getComputeResult() {
        return computeResult;
    }

    public void setComputeResult(ComputeStopResult computeResult) {
        this.computeResult = computeResult;
    }


}
