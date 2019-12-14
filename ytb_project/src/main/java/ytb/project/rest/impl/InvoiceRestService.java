package ytb.project.rest.impl;

import com.alibaba.fastjson.JSONObject;
import ytb.project.model.Invoice;
import ytb.project.model.InvoiceInfo;
import ytb.project.model.InvoicePojo;
import ytb.project.service.invoice.InvoiceInfoService;
import ytb.project.service.invoice.InvoiceService;
import ytb.project.service.invoice.impl.InvoiceInfoServiceImpl;
import ytb.project.service.invoice.impl.InvoiceServiceImpl;

import java.util.List;

/**
 * Package: ytb.manager.metadata.rest.impl
 * Author: XZW
 * Date: Created in 2018/8/23 18:11
 */
public class InvoiceRestService {


    public boolean add(Invoice invoice) {
        InvoiceService invoiceService = new InvoiceServiceImpl();

        int a = invoiceService.insertSelective(invoice);


        if (a > 0) {
            return true;
        } else return false;

    }

    public boolean addInfo(InvoiceInfo invoice) {
        InvoiceInfoService invoiceService = new InvoiceInfoServiceImpl();

        int a = invoiceService.insertSelective(invoice);

        if (a > 0) {
            return true;
        } else return false;

    }

    public boolean addInfoAll(String data) {
        InvoiceService invoiceService = new InvoiceServiceImpl();


        InvoicePojo pojo = JSONObject.parseObject(data, InvoicePojo.class);


        return invoiceService.insertAllSelective(pojo.getInvoice(), pojo.getInvoiceInfos(), pojo.getInvoiceInfoProject());


    }


    public boolean delete(Integer id) {

        InvoiceService invoiceService = new InvoiceServiceImpl();

        int a = invoiceService.deleteByPrimaryKey(id);

        if (a > 0) {
            return true;
        } else return false;

    }

    public boolean deleteinfo(Integer id) {

        InvoiceInfoService invoiceService = new InvoiceInfoServiceImpl();

        int a = invoiceService.deleteByPrimaryKey(id);

        if (a > 0) {
            return true;
        } else return false;

    }


    //修改
    public boolean editInvoice(Invoice invoice) {

        InvoiceService invoiceService = new InvoiceServiceImpl();

        int a = invoiceService.updateByPrimaryKey(invoice);

        if (a > 0) {
            return true;
        } else return false;

    }

    //修改
    public boolean editInvoiceInfo(InvoiceInfo invoice) {

        InvoiceInfoService invoiceService = new InvoiceInfoServiceImpl();

        int a = invoiceService.updateByPrimaryKey(invoice);

        if (a > 0) {
            return true;
        } else return false;

    }

    public InvoicePojo selectAllByKey(Integer id) {

        InvoiceService invoiceService = new InvoiceServiceImpl();

        InvoicePojo a = invoiceService.selectInvoiceAllByKey(id);

        return a;


    }


    public List<Invoice> selectAll() {

        InvoiceService invoiceService = new InvoiceServiceImpl();

        List<Invoice> a = invoiceService.selectAll();

        return a;


    }

    public List<InvoicePojo> selectAllAndInfo() {

        InvoiceService invoiceService = new InvoiceServiceImpl();

        List<InvoicePojo> a = invoiceService.selectAllAndInfo();

        return a;

    }


}
