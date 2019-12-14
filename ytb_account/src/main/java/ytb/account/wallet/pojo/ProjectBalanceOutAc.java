package ytb.account.wallet.pojo;

import ytb.common.context.model.Ytb_Model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author
 */
public class ProjectBalanceOutAc extends Ytb_Model implements Serializable {


    private Integer tradeId;//y用来返回的交易号

    private Integer userId;


    private Integer companyId;
    private Integer projectId;
    private Integer talkId;

    private Integer phase;


    private BigDecimal totalBalance = BigDecimal.ZERO;//交易金额  totalFee
    private BigDecimal balance = BigDecimal.ZERO;//交易金额  totalFee
    private BigDecimal serviceFee = BigDecimal.ZERO;//项目支付-服务费
    private BigDecimal tax = BigDecimal.ZERO;//项目支付-税费
    private BigDecimal fee = BigDecimal.ZERO;//项目支付-手续费

    private String password;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
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
        this.tax = tax;
    }
}