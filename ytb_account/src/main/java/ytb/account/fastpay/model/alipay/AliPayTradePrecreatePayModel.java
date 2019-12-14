package ytb.account.fastpay.model.alipay;

import java.math.BigDecimal;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/3/14
 */
public class AliPayTradePrecreatePayModel {


    private String out_trade_no;

    private BigDecimal total_amount;

    private String subject;

    private String bizContent;

    private String timeout_express;


    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBizContent() {
        return bizContent;
    }

    public void setBizContent(String bizContent) {
        this.bizContent = bizContent;
    }

    public String getTimeout_express() {
        return timeout_express;
    }

    public void setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
    }
}
