package ytb.account.cashpay.alipay.model;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/1/22
 */
public class TransferAccountsModel {

    private String order_id;

    private Integer out_biz_no;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Integer getOut_biz_no() {
        return out_biz_no;
    }

    public void setOut_biz_no(Integer out_biz_no) {
        this.out_biz_no = out_biz_no;
    }
}
