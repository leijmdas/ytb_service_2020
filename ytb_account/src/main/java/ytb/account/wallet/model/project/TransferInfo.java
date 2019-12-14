package ytb.account.wallet.model.project;

import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.model.AccountUserInner;
import ytb.common.context.model.YtbError;

import java.math.BigDecimal;
import java.util.List;

public class TransferInfo extends TradeInfo {

    Short tradeSubType;

    //对方用户，以获取账户
    int toUserId;
    int toCompanyId;

    public AccountTradeProject toAccountTrade(TransferInfo info, Byte serviceType) {
        AccountTradeProject accountTrade = super.toAccountTrade(serviceType);
        accountTrade.setAcId(info.getAcID());
        accountTrade.setToAcId(info.getToAcID());

        return accountTrade;
    }

    public int getToAcID() {
        List<AccountUserInner> inners = companyId == 0
                ? innerServer.getInnerIdByUserById(getToUserId(), getToCompanyId())
                : innerServer.getInnerIdByUserById(0, getToCompanyId());
        if (inners.size() == 0) {
            throw new YtbError(YtbError.CODE_NOTEXISTS_ACCOUNT);
        }
        return inners.get(0).getInnerId();
    }
    public Short getTradeSubType() {
        return tradeSubType;
    }

    public void setTradeSubType(Short tradeSubType) {
        this.tradeSubType = tradeSubType;
    }


    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public int getToCompanyId() {
        return toCompanyId;
    }

    public void setToCompanyId(int toCompanyId) {
        this.toCompanyId = toCompanyId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Byte getServiceType() {
        return serviceType;
    }

    public void setServiceType(Byte serviceType) {
        this.serviceType = serviceType;
    }


}
