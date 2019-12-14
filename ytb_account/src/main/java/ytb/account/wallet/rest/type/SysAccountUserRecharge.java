package ytb.account.wallet.rest.type;

import com.alibaba.fastjson.JSONObject;
import ytb.account.cashpay.alipay.model.ComeTransferAccountsModel;
import ytb.account.wallet.rest.impl.SysAccountUserRechargeService;
import ytb.account.wallet.rest.impl.SysRechargeServer;
import ytb.account.wallet.rest.impl.SysWithdrawCash;
import ytb.account.wallet.service.service.autoSettlement.SettlementTrade;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Package: ytb.manager.metadata.rest.impl
 * Author: XZW
 * Date: Created in 2018/8/23 18:11
 */
public class SysAccountUserRecharge {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public MsgResponse process(MsgRequest req, MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {

        //充值
        if (req.cmd.equals("accountIncrease")) {
            SysRechargeServer sysAccountUserRecharge = new SysRechargeServer();

            return sysAccountUserRecharge.accountIncrease(req, handler, request, response);
        }

        //////提现审核
        if (req.cmd.equals("withdrawCash")) {
            SysWithdrawCash sysAccountUserRecharge = new SysWithdrawCash();
            return sysAccountUserRecharge.withdrawCash(req, handler, request, response);
        }


        //提现申请通过操作
        if (req.cmd.equals("withdrawCashAdopt")) {
            SysWithdrawCash sysAccountUserRecharge = new SysWithdrawCash();

            //   AccountTrade data = JSONObject.parseObject(req.msgBody.toString(), AccountTrade.class);
            ComeTransferAccountsModel model = JSONObject.parseObject(req.msgBody.toString(), ComeTransferAccountsModel.class);
            boolean sta = sysAccountUserRecharge.withdrawCashAdopt(model);

            if (sta == true) {

            } else {
                msgBody = "失败";
            }

            return handler.buildMsg(retcode, retmsg, msgBody);

        }




        /*new*/


        //获取主表中的所有记录
        if (req.cmd.equals("accountReduce")) {

            SysAccountUserRechargeService sysAccountUserRecharge = new SysAccountUserRechargeService();

            return sysAccountUserRecharge.accountReduce(req, handler, request, response);


        }


        //客户收入

        if (req.cmd.equals("customerReceivables")) {

            SysAccountUserRechargeService sysAccountUserRecharge = new SysAccountUserRechargeService();

            return sysAccountUserRecharge.customerReceivables(req, handler, request, response);

        }


        if (req.cmd.equals("newOrder")) {
            SysAccountUserRechargeService sysAccountUserRecharge = new SysAccountUserRechargeService();

            return sysAccountUserRecharge.newOrder(req, handler, request, response);
        }


        //receivables   应收款项
//        if (req.cmd.equals("receivables")) {
//            SysAccountUserRechargeService sysAccountUserRecharge = new SysAccountUserRechargeService();
//
//            return sysAccountUserRecharge.receivables(req, handler, request, response);
//        }


        //退款
        if (req.cmd.equals("customerRefund")) {
            SysAccountUserRechargeService sysAccountUserRecharge = new SysAccountUserRechargeService();

            return sysAccountUserRecharge.customerRefund(req, handler, request, response);
        }
        //部分退款
        if (req.cmd.equals("customerRefundPart")) {
            SysAccountUserRechargeService sysAccountUserRecharge = new SysAccountUserRechargeService();

            return sysAccountUserRecharge.customerRefundPart(req, handler, request, response);
        }

        //业务退款
        if (req.cmd.equals("businessRefund")) {
            SysAccountUserRechargeService sysAccountUserRecharge = new SysAccountUserRechargeService();

            return sysAccountUserRecharge.businessRefund(req, handler, request, response);
        }

        //转移
        if (req.cmd.equals("transfer")) {
            SysAccountUserRechargeService sysAccountUserRecharge = new SysAccountUserRechargeService();

            return sysAccountUserRecharge.transfer(req, handler, request, response);
        }


        //用户结算交易
        if (req.cmd.equals("UserSettlementTrade")) {
            SettlementTrade asd = new SettlementTrade();
            asd.UserSettlementTrade();
        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }


}
