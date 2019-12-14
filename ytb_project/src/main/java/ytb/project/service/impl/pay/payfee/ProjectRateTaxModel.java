package ytb.project.service.impl.pay.payfee;

import ytb.project.context.UserProjectContext;
import ytb.manager.tagtable.model.business.ProjectRateModel;
import ytb.account.wallet.model.project.TradeInfo;

public final class ProjectRateTaxModel extends ProjectRateModel implements TradeInfo.IFeeRate {
    UserProjectContext context;


    protected float invoiceFeeRate = 15;

    public ProjectRateTaxModel(ProjectRateModel model, UserProjectContext context) {
        this.context = context;
        this.projectId = model.getProjectId();
        this.feeRate = model.getFeeRate();
        this.taxRate = model.getTaxRate();
        this.sTaxRate = model.getsTaxRate();
        this.cRate = model.getcRate();
        this.pRate = model.getpRate();


    }

    public float getFeeRate() {

             return feeRate / 100;

    }

    public float getTaxRate() {

        if (new ProjectTax().needInvoice(context)) {
            return taxRate / 100;
        }
        return 0f;
    }

    public float getInvoiceFeeRate() {
        if (new ProjectTax().needInvoice(context)) {
            return invoiceFeeRate / 100;
        }
        return 0f;
    }

    public void setInvoiceFeeRate(float invoiceFeeRate) {
        this.invoiceFeeRate = invoiceFeeRate;
    }

}
