package ytb.account.wallet.model;

/**
 * @Author zc
 * @Description //TODO Wallet 模块的查询条件
 * @Date 2019/3/15
 **/
public class WalletCondition {
    /**
     * 用户内部账户
     * account_user_inner inner_Id
     */
    private Integer innerId;

    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 支付密码
     */
    private String payPassword;


    public Integer getInnerId() {
        return innerId;
    }

    public void setInnerId(Integer innerId) {
        this.innerId = innerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }
}
