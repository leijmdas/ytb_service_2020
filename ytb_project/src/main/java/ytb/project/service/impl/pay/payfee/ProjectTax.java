package ytb.project.service.impl.pay.payfee;

import ytb.project.context.UserProjectContext;
import ytb.project.daoservice.BuyPriceListPModelServiceImpl;
import ytb.project.daoservice.CostSumInvoiceModelServiceImpl;
import ytb.project.daoservice.ProcessListPModelServiceImpl;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.tagtable.BuyPriceListPModel;
import ytb.project.model.tagtable.CostSumInvoiceModel;
import ytb.project.model.tagtable.ProcessListPModel;

public class ProjectTax {

//    结项时（条件是：最后一个阶段，甲方验收通过，且完成了开具发票任务（原型图未做）），根据谁开发票，分三种情况：
//    1）乙方是公司/单位用户，乙方给甲方开发票，只扣除服务费，余款解冻到乙方的余额账户中。
//    2）乙方是个人用户，只能由平台代开发票，扣除服务费、税、开票手续费，余款解冻到乙方每个人的余额账户中。
//    3）不要发票，扣除服务费后，余款解冻到乙方账户中。
    public boolean needInvoice(UserProjectContext context) {
        if (context.pbIsCompanyUser()) {
            return false;
        }

        return needInvoicePersonUser(context);
    }

    //返回发票类型
    boolean needInvoicePersonUser(UserProjectContext context) {
        ProjectTalkModel ptm = context.getProjectTalkModel();

        if (context.isPurchase()) {
            BuyPriceListPModel pModel = new BuyPriceListPModelServiceImpl().selectOne(ptm.getTalkId(),
                    ptm.getWorkplanId());
            return pModel != null && !pModel.getInvoiceType().equals("0");

        } else if (context.isProcessing()) {
            ProcessListPModel listPModel = new ProcessListPModelServiceImpl().selectOne(ptm.getTalkId(),
                    ptm.getWorkplanId());
            return listPModel != null && !listPModel.getInvoiceType().equals("0");
        }

        CostSumInvoiceModel model = new CostSumInvoiceModelServiceImpl().selectOne(ptm.getTalkId(), ptm.getWorkplanId());

        //cost_sum_service
        return model != null && model.getInvoicePf1() != null && model.getInvoicePf1().equals(1);
    }
}