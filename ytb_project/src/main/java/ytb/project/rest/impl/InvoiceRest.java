package ytb.project.rest.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ytb.manager.charges.pojo.Paging;
import ytb.project.model.Invoice;
import ytb.project.model.InvoiceInfo;
import ytb.project.model.InvoicePojo;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Package: ytb.manager.metadata.rest.impl
 * Author: XZW
 * Date: Created in 2018/8/23 18:11
 */
public class InvoiceRest {

    public MsgResponse process(MsgRequest req, RestHandler handler, HttpServletRequest request, HttpServletResponse response) {

        int retcode = 0;
        String retmsg = "成功";
        String msgBody = null;

        if (req.cmd.equals("selectAll")) {

            InvoiceRestService invoiceRestService = new InvoiceRestService();
            List<InvoicePojo> a = invoiceRestService.selectAllAndInfo();
            msgBody = "{\"list\":" + JSONArray.toJSONString(a) + "}";

        }


        if (req.cmd.equals("selectAllByKey")) {

            Integer id = req.getMsgBody().getInteger("id");

            if (id != null) {

                InvoiceRestService invoiceRestService = new InvoiceRestService();
                InvoicePojo a = invoiceRestService.selectAllByKey(id);
                msgBody = "{\"list\":" + JSONArray.toJSONString(a) + "}";
            } else {
                retcode = 1;
                retmsg = "失败";
            }


        }


        if (req.cmd.equals("addAll")) {

            InvoiceRestService invoiceRestService = new InvoiceRestService();
            //    InvoicePojo pojo = JSONObject.parseObject(req.getMsgBody().toJSONString(), InvoicePojo.class);
            boolean a = invoiceRestService.addInfoAll(req.getMsgBody().toJSONString());
            if (a != true) {
                retcode = 1;
                retmsg = "失败";
            }

        }

        if (req.cmd.equals("addInfo")) {


            InvoiceRestService invoiceRestService = new InvoiceRestService();
            InvoiceInfo data = JSONObject.parseObject(req.getMsgBody().toJSONString(), InvoiceInfo.class);
            boolean a = invoiceRestService.addInfo(data);
            if (a != true) {
                retcode = 1;
                retmsg = "失败";
            }


        }

        if (req.cmd.equals("add")) {

            InvoiceRestService invoiceRestService = new InvoiceRestService();
            Invoice data = JSONObject.parseObject(req.getMsgBody().toJSONString(), Invoice.class);
            boolean a = invoiceRestService.add(data);

            if (a != true) {
                retcode = 1;
                retmsg = "失败";
            }

        }


        if (req.cmd.equals("editInvoiceInfo")) {


            InvoiceRestService invoiceRestService = new InvoiceRestService();
            InvoiceInfo data = JSONObject.parseObject(req.getMsgBody().toJSONString(), InvoiceInfo.class);
            if (data != null) {
                boolean a = invoiceRestService.editInvoiceInfo(data);
                if (a != true) {
                    retcode = 1;
                    retmsg = "失败";
                }
            } else {

                retcode = 1;
                retmsg = "失败";
            }


        }

        if (req.cmd.equals("editInvoice")) {

            InvoiceRestService invoiceRestService = new InvoiceRestService();
            Invoice data = JSONObject.parseObject(req.getMsgBody().toJSONString(), Invoice.class);
            if (data != null) {
                boolean a = invoiceRestService.editInvoice(data);

                if (a != true) {
                    retcode = 1;
                    retmsg = "失败";
                }
            } else {

                retcode = 1;
                retmsg = "失败";
            }

        }


        if (req.cmd.equals("delete")) {

            InvoiceRestService invoiceRestService = new InvoiceRestService();
            Integer id = req.getMsgBody().getInteger("id");

            if (id != null) {
                boolean a = invoiceRestService.delete(id);
                if (a != true) {
                    retcode = 1;
                    retmsg = "失败";
                }
            } else {
                retcode = 1;
                retmsg = "失败";
            }


        }

        if (req.cmd.equals("deleteInfo")) {

            InvoiceRestService invoiceRestService = new InvoiceRestService();
            Integer id = req.getMsgBody().getInteger("id");

            if (id != null) {
                boolean a = invoiceRestService.deleteinfo(id);
                if (a != true) {
                    retcode = 1;
                    retmsg = "失败";
                }
            } else {
                retcode = 1;
                retmsg = "失败";
            }

        }


        return handler.buildMsg(retcode, retmsg, msgBody);
    }

    Paging structurePaging(MsgRequest req) {
        Paging paging = new Paging();
        if (
                req.msgBody.getInteger("currPage") != null &
                        req.msgBody.getInteger("pageSize") != null &
                        req.msgBody.getString("toOrder") != null &
                        req.msgBody.getString("orderBy") != null
        ) {
            paging.setCurrPage(req.msgBody.getInteger("currPage"));
            paging.setPageSize(req.msgBody.getInteger("pageSize"));
            paging.setToOrder(req.msgBody.getString("toOrder"));
            paging.setOrderBy(req.msgBody.getString("orderBy"));
            return paging;
        } else {
            return null;
        }

    }


}
