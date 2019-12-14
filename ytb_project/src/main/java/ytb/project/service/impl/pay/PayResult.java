package ytb.project.service.impl.pay;

import ytb.project.common.PayConst;
import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.tagtable.WorkGroupMemberModel;
import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.model.project.TradeInfo;
import ytb.common.context.model.Ytb_ModelSkipNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class PayResult extends Ytb_ModelSkipNull {


    //PA转帐支付,解冻
    List<PayResult> paPayResults;
    //PB转帐支付 解冻
    List<PayResult> pbPayResults;

    int payType;
    int tradeId;

    int userId;
    int companyId;
    int projectId;
    int talkId;
    int phase;
    int phaseStatus;
    int changeStatus;

    Integer folderId;

    Integer documentId;//workplan

    //totalBalance=balance+servicefee+fee+tax
    BigDecimal totalBalance = BigDecimal.valueOf(0).setScale(2,BigDecimal.ROUND_HALF_UP);
    BigDecimal balance = BigDecimal.valueOf(0).setScale(2,BigDecimal.ROUND_HALF_UP);
    BigDecimal serviceFee =  BigDecimal.valueOf(0).setScale(2,BigDecimal.ROUND_HALF_UP);
    BigDecimal fee =  BigDecimal.valueOf(0).setScale(2,BigDecimal.ROUND_HALF_UP);
    BigDecimal tax =  BigDecimal.valueOf(0).setScale(2,BigDecimal.ROUND_HALF_UP);

    public BigDecimal computeBalance(){

        balance = totalBalance.subtract(serviceFee).subtract(tax).subtract(fee).setScale(2,BigDecimal.ROUND_HALF_UP);;
        return  balance;
    }

    public void addPaResults(UserProjectContext context, int payType, List<AccountTradeProject> accountTrades) {
        if(paPayResults==null){
            paPayResults=new ArrayList<>();
        }
        for (AccountTradeProject tradeProject : accountTrades) {
            PayResult payResult = new PayResult(context, tradeProject);
            payResult.setPayType(payType);
            getPaPayResults().add(payResult);
        }
    }


    public void addPbResults(UserProjectContext context, List<AccountTradeProject> accountTrades) {
        if (pbPayResults == null) {
            pbPayResults = new ArrayList<>();
        }

        for (AccountTradeProject tradeProject : accountTrades) {
            PayResult payResult = new PayResult(context, tradeProject);
            getPbPayResults().add(payResult);
        }
    }


    public List<PayResult> getPaPayResults() {
        return paPayResults;
    }

    public void setPaPayResults(List<PayResult> paPayResults) {
        this.paPayResults = paPayResults;
    }

    public List<PayResult> getPbPayResults() {
        return pbPayResults;
    }

    public void setPbPayResults(List<PayResult> pbPayResults) {
        this.pbPayResults = pbPayResults;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }
    public int getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(int changeStatus) {
        this.changeStatus = changeStatus;
    }


    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public PayResult(){

    }

    public PayResult(UserProjectContext context) {
        ProjectTalkModel ptm = context.getProjectTalkModel();

        PayResult payResult = this;
        payResult.setUserId(context.getUserId());
        payResult.setCompanyId(context.getCompanyId());
        payResult.setDocumentId(ptm.getWorkplanId());

        payResult.setProjectId(ptm.getProjectId());
        payResult.setTalkId(ptm.getTalkId());
        payResult.setPhase(ptm.getPhase());
        payResult.setPhaseStatus(ptm.getPhaseStatus());
        payResult.setChangeStatus(ptm.getChangeStatus());

    }
    public PayResult(UserProjectContext context,BigDecimal fee) {
        this(context);
        setTotalBalance(fee);
        setBalance(fee);
    }

    public PayResult(UserProjectContext context,int payType) {
        this(context);
        setPayType(payType);


    }

    public PayResult(UserProjectContext context, AccountTradeProject tradeProject) {
        this(context);
        setUserId(tradeProject.getUserId());
        setCompanyId(tradeProject.getCompanyId());

        setPayType(PayConst.PAYTYPE_FINISH);
        setTotalBalance(tradeProject.getTotalBalance());
        setBalance(tradeProject.getBalance());
        setServiceFee(tradeProject.getServiceFee());
        setTax(tradeProject.getTax());
        setFee(tradeProject.getFee());

    }


    public void sum(PayResult one){
        setTotalBalance(getTotalBalance().add(one.getTotalBalance()));
        setBalance(getBalance().add(one.getBalance()));
        setServiceFee(getServiceFee().add(one.getServiceFee()));
        setTax(getTax().add(one.getTax()));
        setFee(getFee().add(one.getFee()));
    }

    public TradeInfo buildTradeInfo() {

        TradeInfo info = new TradeInfo();
        info.setUserId(getUserId());
        info.setCompanyId(getCompanyId());

        info.setProjectId(getProjectId());
        info.setTalkId(getTalkId());
        info.setPhase(getPhase());

        info.setTotalBalance(getTotalBalance());
        info.setBalance(getBalance());

        return info;
    }

    TradeInfo buildTradeInfo(UserProjectContext context) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        PayResult payResult = this;
        TradeInfo info = new TradeInfo();
        info.setUserId(pm.getUserId1());
        info.setCompanyId(pm.getCompanyId1()); //公司只给一人名义的公司

        info.setProjectId(pm.getProjectId());
        info.setTalkId(ptm.getTalkId());
        info.setPhase(ptm.getPhase());

        info.setTotalBalance(payResult.getTotalBalance());
        info.setServiceFee(payResult.getServiceFee());
        info.setFee(payResult.getFee());
        info.setTax(payResult.getTax());
        info.computeBalance();
        //info.setBalance(payResult.getBalance());
        info.setBalanceFlag(TradeInfo.BALANCE_FLAG_ADD);
        return info;
    }


    public TradeInfo buildTradeInfoPa(UserProjectContext context){

        ProjectModel pm = context.getProjectModel();
        TradeInfo info = buildTradeInfo(context);
        info.setUserId(pm.getUserId1());
        info.setCompanyId(pm.getCompanyId1()); //公司只给一人名义的公司

        return info;
    }

    //info.setTradeIdPre(preTradeId);
    public TradeInfo buildTradeInfoPb(UserProjectContext context){
        ProjectTalkModel ptm = context.getProjectTalkModel();

        TradeInfo info = buildTradeInfo(context);
        info.setUserId(ptm.getUserId2());
        info.setCompanyId(ptm.getCompanyId2()); //公司只给一人名义的公司

        return info;
    }

    public TradeInfo buildTradeInfoPm(UserProjectContext context, WorkGroupMemberModel memberModel){
        ProjectTalkModel ptm = context.getProjectTalkModel();

        TradeInfo info = buildTradeInfo(context);
        info.setUserId(memberModel.getUserId());
        info.setCompanyId(memberModel.getCompanyId()); //公司只给一人名义的公司

        return info;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
    public BigDecimal getTax() {
        return tax  ;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }


    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public int getPhaseStatus() {
        return phaseStatus;
    }

    public void setPhaseStatus(int phaseStatus) {
        this.phaseStatus = phaseStatus;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getTalkId() {
        return talkId;
    }

    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }


}
