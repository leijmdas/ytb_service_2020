package ytb.project.model;

import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.List;

/**
 * Copyright@ vipcchua.github.io
 * Author:Cchua
 * Date:2019/1/23
 */
public class InvoicePojo  extends Ytb_ModelSkipNull {

    private Invoice invoice;

    private List<InvoiceInfo> invoiceInfos;

    private InvoiceInfoProject invoiceInfoProject;

    public InvoiceInfoProject getInvoiceInfoProject() {
        return invoiceInfoProject;
    }

    public void setInvoiceInfoProject(InvoiceInfoProject invoiceInfoProject) {
        this.invoiceInfoProject = invoiceInfoProject;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<InvoiceInfo> getInvoiceInfos() {
        return invoiceInfos;
    }

    public void setInvoiceInfos(List<InvoiceInfo> invoiceInfos) {
        this.invoiceInfos = invoiceInfos;
    }
}
