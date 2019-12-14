package ytb.manager.template.model;

import ytb.common.context.model.Ytb_Model;

public class TemplateModel extends Ytb_Model {

    final transient public static int Template_TYPE_req_START = 100;//需求模板
    final transient public static int Template_TYPE_req_END = 199;//需求模板
    final transient public static int Template_TYPE_req = Template_TYPE_req_START;//需求模板
    //采购
    final transient public static int Template_TYPE_req_purchase = 101;
    //加工
    final transient public static int Template_TYPE_req_process = 102;

    final transient public static int Template_TYPE_workplan_START = 200;//工作组计划模板
    final transient public static int Template_TYPE_workplan_END = 299;//工作组计划模板
    final transient public static int Template_TYPE_workplan = Template_TYPE_workplan_START;//工作组计划模板
    //采购报价单
    final transient public static int Template_TYPE_workplan_purchasePrice = 201;
    //加工报价单
    final transient public static int Template_TYPE_workplan_processPrice = 202;

    final transient public static int Template_TYPE_design = 300; // 设计文档
    final transient public static int Template_TYPE_delivered = 400; // 交付文档模板
    //other
    final transient public static int Template_TYPE_other = 500;

    final transient public static int Template_TYPE_integrated_START = 600;//集成文档
    final transient public static int Template_TYPE_integrated_END = 699;//集成文档
    final transient public static int Template_TYPE_integrated = Template_TYPE_integrated_START;//集成文档
    final transient public static int Template_TYPE_integratedDesign = 601;//集成设计文档
    final transient public static int Template_TYPE_integratedDelivered = 602;//集成交付模板
    //(集成)测试方案及测试报告
    final transient public static int Template_TYPE_testReport = 603;//测试方案及测试报告

    //物料总清单
    final transient public static int Template_TYPE_MaterialList = 604;
    //发票模板文档
    final transient public static int Template_TYPE_invoice = 605;
    //发票汇总文档
    final transient public static int Template_TYPE_invoice_total = 606;


    //开题报告
    final transient public static int Template_TYPE_openReport = 701;
    //毕业论文
    final transient public static int Template_TYPE_graduatePaper = 710;
    final transient public static int Template_TYPE_archive = 800;//存档文件

    final transient public static int Template_TYPE_keepSecret= 901;//保密协议模板
    final transient public static int Template_TYPE_thirdTractact = 902;//三方协作合同模板
    final transient public static int Template_TYPE_stop = 904;//项目终止
    final transient public static int Template_TYPE_chng = 903;//需求变更

    //文档名称
    protected String title ;
    //文档类型
    protected int docType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getDocType() {
        return docType;
    }

    public void setDocType(int docType) {
        this.docType = docType;
    }


    public boolean isArchive() {
        return  getDocType() == Dict_TemplateModel.Template_TYPE_archive;
    }

    public boolean isTemplateReq() {
        return getDocType() >= Dict_TemplateModel.Template_TYPE_req_START
                && getDocType() <= Dict_TemplateModel.Template_TYPE_req_END;
    }

    public boolean isTemplateWorkplan() {
        return getDocType() >= Dict_TemplateModel.Template_TYPE_workplan_START
                && getDocType() <= Dict_TemplateModel.Template_TYPE_workplan_END;
    }
    public boolean isTemplateWorkplan_nonePp() {
        return getDocType() == Dict_TemplateModel.Template_TYPE_workplan ;
    }

    public boolean isTemplateComposite() {
        return getDocType() >= Dict_TemplateModel.Template_TYPE_integrated_START
                && getDocType() <= Dict_TemplateModel.Template_TYPE_integrated_END;
    }

}
