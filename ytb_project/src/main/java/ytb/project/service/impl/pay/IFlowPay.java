package ytb.project.service.impl.pay;

import ytb.project.context.UserProjectContext;
import ytb.project.model.tagtable.CostModel;
import ytb.project.service.impl.pay.payfee.ProjectCostFee;
import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.pojo.ProjectBalanceProjectAgent;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface IFlowPay {

    Integer getTradeIdByTalk(int talkId, int userId);

    ProjectCostFee getProjectCostFee();

    //正常流程支付：
    //1 PA项目首次启动支付预付款
    PayResult paPayOpenProject(UserProjectContext context, String password) throws UnsupportedEncodingException;

    //2 项目 阶段支付
    PayResult payPhase(UserProjectContext context, int talkId, String password) throws UnsupportedEncodingException;

    ProjectBalanceProjectAgent payPhasePp(UserProjectContext context, String passWord) throws UnsupportedEncodingException ;


    //3 最后完成支付： 结项解冻,乙方是公司，只支付给乙方一个人的公司帐户
    List<AccountTradeProject> payFinishProject(UserProjectContext context);


    //60 computePA计算解冻金额
    Map<Integer, CostModel> sumPaUnfreeze(UserProjectContext context);

    //61 computePB计算解冻金额
    Map<Integer, CostModel> sumPbUnfreeze(UserProjectContext context);


    //4 项目变更支付：按新项目支付
    //PayResult paPayChange(UserProjectContext newContext, UserProjectContext oldContext, String password) throws UnsupportedEncodingException;

    //5 项目终止支付
    //ViewPayResult paPayStop(UserProjectContext context, String password) throws UnsupportedEncodingException;


}