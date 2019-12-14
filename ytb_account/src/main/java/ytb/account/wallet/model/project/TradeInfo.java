package ytb.account.wallet.model.project;

import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.model.AccountUserInner;
import ytb.account.wallet.pojo.ProjectBalanceOutAc;
import ytb.account.wallet.pojo.ProjectBalanceToAc;
import ytb.account.wallet.rest.impl.sq.SysAccountUserInnerServer;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.common.context.model.YtbError;
import ytb.common.context.model.Ytb_Model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TradeInfo extends Ytb_Model {
    transient SysAccountUserInnerServer innerServer = new SysAccountUserInnerServer();
    //feeType: add, minus default: add 1 pf ac
    public static interface IFeeRate {
        float getFeeRate();

        float getTaxRate();

        float getInvoiceFeeRate();
    }


    public final static int BALANCE_FLAG_NONE = 0;
    public final static int BALANCE_FLAG_ADD = 1;//+
    public final static int BALANCE_FLAG_MINUS = -1;//-


    //    int tradeType;
    //    int tradeSubtype;
    Byte serviceType = TradeConst.SERVICE_TYPE_project;

    int balanceFlag ;

    int tradeIdPre;//原订单

    int userId;
    int companyId;
    String password;

    int projectId;
    Integer talkId ;
    Integer phase;

    BigDecimal totalBalance = BigDecimal.ZERO;
    BigDecimal balance = BigDecimal.ZERO;

    BigDecimal serviceFee = BigDecimal.ZERO;
    BigDecimal tax = BigDecimal.ZERO;
    BigDecimal fee = BigDecimal.ZERO;


    public int getBalanceFlag() {
        return balanceFlag;
    }

    public void setBalanceFlag(int balanceFlag) {
        this.balanceFlag = balanceFlag;
    }

    public TradeInfo(TradeInfo that) {
        this.tradeIdPre = that.tradeIdPre;
        this.userId = that.userId;
        this.companyId = that.companyId;
        this.password = that.password;
        this.serviceType = that.serviceType;
        this.balanceFlag = that.balanceFlag;

        this.projectId = that.projectId;
        this.talkId = that.talkId;
        this.phase = that.phase;

        this.totalBalance = that.totalBalance;
        this.balance = that.balance;
        this.serviceFee = that.serviceFee;
        this.fee = that.fee;
        this.tax = that.tax;

    }

    public TradeInfo(){

    }


    public TradeInfo computeServiceFee(IFeeRate feeRate, int balanceFlag) {
        setBalanceFlag(balanceFlag);
        serviceFee = totalBalance.multiply(BigDecimal.valueOf(feeRate.getFeeRate())) .setScale(2,BigDecimal.ROUND_HALF_UP);
        computeBalance();
        return this;
    }

    public TradeInfo computeAllFee(IFeeRate feeRate, int balanceFlag) {
        setBalanceFlag(balanceFlag);
        // 服务费
        serviceFee = totalBalance.multiply(BigDecimal.valueOf(feeRate.getFeeRate())).setScale(2,BigDecimal.ROUND_HALF_UP);
        // 税
        tax = totalBalance.multiply(BigDecimal.valueOf(feeRate.getTaxRate())).setScale(2,BigDecimal.ROUND_HALF_UP);
        // tax = totalBalance.multiply(BigDecimal.valueOf(0.12)).setScale(2,BigDecimal.ROUND_HALF_UP);

        // 发票手续费
        fee = totalBalance.multiply(BigDecimal.valueOf(feeRate.getInvoiceFeeRate())).setScale(2,BigDecimal.ROUND_HALF_UP);
        computeBalance();
        return this;
    }

    public BigDecimal computeBalance(){

        balance = totalBalance.subtract(serviceFee).subtract(tax).subtract(fee).setScale(2,BigDecimal.ROUND_HALF_UP);;
        return  balance;
    }


    public AccountTradeProject toAccountTrade(Byte serviceType) {
        TradeInfo info= this;
        AccountTradeProject tradeProject = new AccountTradeProject();
        tradeProject.setServiceType(serviceType);

        tradeProject.setStatus(TradeConst.status_success);
        tradeProject.setProjectId(info.getProjectId());
        tradeProject.setTalkId(info.getTalkId());
        tradeProject.setPhase(Short.valueOf(info.getPhase().toString()));

        tradeProject.setUserId(info.getUserId());
        tradeProject.setCompanyId(info.getCompanyId());

        tradeProject.setAcId(info.getAcID());
        tradeProject.setToAcId(info.getAcID());

        tradeProject.setTotalBalance(info.getBalance().add(info.getServiceFee()));
        tradeProject.setBalance(info.getBalance());
        tradeProject.setServiceFee(info.getServiceFee());
        tradeProject.setTax(info.getTax());
        tradeProject.setFee(info.getFee());

        tradeProject.setCreateBy(info.getUserId());
        tradeProject.setCreateTime(new Date());

        return tradeProject;
    }

    public ProjectBalanceToAc toProjectBalanceToAc() {
        TradeInfo tradeInfo = this;

        ProjectBalanceToAc toAc = new ProjectBalanceToAc();
        toAc.setProjectId(tradeInfo.getProjectId());
        toAc.setTalkId(tradeInfo.getTalkId());
        toAc.setPhase(tradeInfo.getPhase());

        toAc.setUserId(tradeInfo.getUserId());
        toAc.setCompanyId(tradeInfo.getCompanyId());

        toAc.setToInnerId(tradeInfo.getAcID());
        toAc.setOutInnerId(tradeInfo.getAcID());

        toAc.setTotalBalance(tradeInfo.getTotalBalance());
        toAc.setBalance(tradeInfo.getBalance());
        toAc.setServiceFee(tradeInfo.getServiceFee());
        toAc.setTax(tradeInfo.getTax());
        toAc.setFee(tradeInfo.getFee());


        return toAc;
    }

    public ProjectBalanceOutAc toProjectBalanceOutAc(){
        TradeInfo outTradeInfo = this;

        //甲方参数
        ProjectBalanceOutAc paOutAc = new ProjectBalanceOutAc();
        paOutAc.setTradeId(outTradeInfo.getTradeIdPre());
        paOutAc.setUserId(outTradeInfo.getUserId());
        paOutAc.setCompanyId(outTradeInfo.getCompanyId());
        paOutAc.setPassword(outTradeInfo.getPassword());

        paOutAc.setProjectId(outTradeInfo.getProjectId());
        paOutAc.setTalkId(outTradeInfo.getTalkId());
        paOutAc.setPhase( outTradeInfo.getPhase() );


        paOutAc.setTotalBalance(outTradeInfo.getTotalBalance());
        paOutAc.setBalance(outTradeInfo.getBalance());
        paOutAc.setServiceFee(outTradeInfo.getServiceFee());
        paOutAc.setTax(outTradeInfo.getTax());
        paOutAc.setFee(outTradeInfo.getFee());

        return paOutAc;
    }


    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    public Integer getPhase() {
        return phase;
    }

    public void setPhase(Integer phase) {
        this.phase = phase;
    }
    public Integer getTalkId() {
        return talkId;
    }

    public void setTalkId(Integer talkId) {
        this.talkId = talkId;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTradeIdPre() {
        return tradeIdPre;
    }

    public void setTradeIdPre(int tradeIdPre) {
        this.tradeIdPre = tradeIdPre;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public Byte getServiceType() {
        return serviceType;
    }

    public void setServiceType(Byte serviceType) {
        this.serviceType = serviceType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee.setScale(2,BigDecimal.ROUND_HALF_UP);;
    }

    public int getAcID() {
        return getAcID(userId, companyId);
    }

    public int getAcID(int userId, int companyId) {
        List<AccountUserInner> inners = companyId == 0 ? innerServer.getInnerIdByUserById(userId, companyId)
                : innerServer.getInnerIdByUserById(0, companyId);
        if (inners.size() == 0) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_ACCOUNT, userId + "");
        }
        return inners.get(0).getInnerId();
    }


}
