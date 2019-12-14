package ytb.account.cashpay.integration.rest.impl;

import com.alibaba.fastjson.JSONObject;
import ytb.account.cashpay.alipay.model.ComeTransferAccountsModel;
import ytb.account.cashpay.alipay.model.TransferAccountsModel;
import ytb.account.cashpay.alipay.pojo.AliPayTransferAccounts;
import ytb.account.cashpay.integration.service.IntegratedPay;
import ytb.account.cashpay.integration.service.impl.IntegratedPayService;
import ytb.account.wallet.model.AccountTrade;
import ytb.account.wallet.service.sq.basics.AccountTradeService;
import ytb.account.wallet.service.sq.basics.impl.AccountTradeServiceImpl;
import ytb.account.wallet.rest.impl.sq.SysAccountPfInnerServer;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Package: ytb.manager.metadata.rest.impl
 * Author: XZW
 * Date: Created in 2018/8/23 18:11
 */
public class SysAliPayRequest {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    private static final Byte ADOPT = 3;

    private static final Byte SUCCESS = 10;

    private static final Byte ERROR = 11;


    public MsgResponse process(MsgRequest req, RestHandler handler, HttpServletRequest request, HttpServletResponse response) {


        //获取主表中的所有记录
        if (req.cmd.equals("transferAccounts")) {
            ComeTransferAccountsModel model = JSONObject.parseObject(req.msgBody.toString(), ComeTransferAccountsModel.class);

            tradeSta(ADOPT, model.getTradeId(), null);

            AliPayTransferAccounts aliPayRefundModel = new AliPayTransferAccounts();

            aliPayRefundModel.setOutBizNo(String.valueOf(model.getTradeId()));//订单号
            aliPayRefundModel.setPayeeAccount(model.getCardHolderId());//手机号
            aliPayRefundModel.setAmount(model.getBalance());//钱
            aliPayRefundModel.setPayerShowName("youtobon");
            aliPayRefundModel.setPayeeRealName(model.getCardHolder());//真实姓名
            aliPayRefundModel.setRemark("youtobon 提现成功");


            try {
                IntegratedPay aliPayService = new IntegratedPayService();
                String from = aliPayService.transferFacility(aliPayRefundModel);


                if (from.equals("") == true) {
                    tradeSta(ERROR, model.getTradeId(), null);
                    retcode = 0;
                    retmsg = "ERROR";


                } else {
                    TransferAccountsModel transferAccountsModel = JSONObject.parseObject(from, TransferAccountsModel.class);

                    tradeSta(SUCCESS, transferAccountsModel.getOut_biz_no(), transferAccountsModel.getOrder_id());


                    retmsg = "SUCCESS";
                }
            } catch (Exception e) {
                tradeSta(ERROR, model.getTradeId(), null);
                retmsg = "ERROR";
            }


        } else if (req.cmd.equals("newInnerInfo")) {

            SysAccountPfInnerServer server = new SysAccountPfInnerServer();

            return server.newInnerInfo(req, handler, request, response);

        }

        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    private boolean tradeSta(Byte sta, Integer tradeId, String tradeNo) {

        try {
            AccountTradeService tradeService = new AccountTradeServiceImpl();
            AccountTrade dd = new AccountTrade();

            dd.setStatus(sta);
            dd.setTradeId(tradeId);
            //外部生成，银行，微信，支付宝、项目产生
            if (tradeNo != null || !tradeNo.equals("")) {
                dd.setTradeNo(tradeNo);

            }
            int trade = tradeService.updateByPrimaryKeySelective(dd);
            if (trade > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
