package ytb.project.service.impl.pay;

import ytb.common.ytblog.YtbLog;
import ytb.common.context.model.YtbError;
import ytb.common.context.model.Ytb_ModelSkipNull;

import java.math.BigDecimal;

public final class ViewPayResult extends Ytb_ModelSkipNull {


    ViewSumResult viewSumResult = new ViewSumResult();

    //甲方最加支付详情，=项目启动支付
    PayResult paOpenPayResult;
    //甲方阶段支付详情
    PayResult paPhasePayResult;
    //乙方阶段退款详情
    PayResult pbPhaseRefundResult;
    //甲方解冻款详情
    PayResult unfreezePaResult;
    //乙方解冻款详情
    PayResult unfreezePbResult;


    public void checkFeePaFrozen(BigDecimal feePaFrozen) {
        viewSumResult.feePaUnfreeze = feePaFrozen;
        YtbLog.logRun("ViewPayResult checkFeePaFrozen", feePaFrozen);
        if (feePaFrozen.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println(feePaFrozen);
             throw new YtbError(YtbError.CODE_DEFINE_ERROR,"feePaUnfreeze<0");
        }

    }

    public void checkFeePbFrozen(BigDecimal feePbFrozen) {
        viewSumResult.feePbUnfreeze = feePbFrozen;
        YtbLog.logRun ("ViewPayResult checkFeePbFrozen",feePbFrozen);
        if (feePbFrozen.compareTo(BigDecimal.ZERO) < 0) {

            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "feePbUnfreeze<0");
        }

    }


    public PayResult getPaOpenPayResult() {
        return paOpenPayResult;
    }

    public void setPaOpenPayResult(PayResult paOpenPayResult) {
        this.paOpenPayResult = paOpenPayResult;
    }



    public ViewSumResult getViewSumResult() {
        return viewSumResult;
    }

    public void setViewSumResult(ViewSumResult viewSumResult) {
        this.viewSumResult = viewSumResult;
    }


    public PayResult getPaPhasePayResult() {
        return paPhasePayResult;
    }

    public void setPaPhasePayResult(PayResult paPhasePayResult) {
        this.paPhasePayResult = paPhasePayResult;
    }

    public PayResult getPbPhaseRefundResult() {
        return pbPhaseRefundResult;
    }

    public void setPbPhaseRefundResult(PayResult pbPhaseRefundResult) {
        this.pbPhaseRefundResult = pbPhaseRefundResult;
    }

    public PayResult getUnfreezePaResult() {
        return unfreezePaResult;
    }

    public void setUnfreezePaResult(PayResult unfreezePaResult) {
        this.unfreezePaResult = unfreezePaResult;
    }

    public PayResult getUnfreezePbResult() {
        return unfreezePbResult;
    }

    public void setUnfreezePbResult(PayResult unfreezePbResult) {
        this.unfreezePbResult = unfreezePbResult;
    }



}
