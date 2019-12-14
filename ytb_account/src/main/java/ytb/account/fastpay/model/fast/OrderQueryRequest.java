package ytb.account.fastpay.model.fast;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/3/14
 */
public class OrderQueryRequest {

    private String orderId;


    private String outOrderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }
}
