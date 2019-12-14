package ytb.project.daoservice;

import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.dao.tagtable.ICostSumInvoiceModelService;
import ytb.project.model.tagtable.CostSumInvoiceModel;

import java.util.List;

public class CostSumInvoiceModelServiceImpl  extends DAOService implements ICostSumInvoiceModelService {

	public int insert(CostSumInvoiceModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_project.cost_sum_invoice");
		sql.append("(invoice_no1,invoice_no2,invoice_y1,invoice_y2,invoice_pf1,invoice_pf2,invoice_desc,project_id,document_id,talk_id)");
		sql.append("values");		
		sql.append("(#{invoiceNo1},#{invoiceNo2},#{invoiceY1},#{invoiceY2},#{invoicePf1},#{invoicePf2},#{invoiceDesc},#{projectId},#{documentId},#{talkId})");
		return YtbSql.insert(sql,m);

	}
	public void update(CostSumInvoiceModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.cost_sum_invoice");
		sql.append("set invoice_no1=#{invoiceNo1},invoice_no2=#{invoiceNo2},invoice_y1=#{invoiceY1},invoice_y2=#{invoiceY2},invoice_pf1=#{invoicePf1},invoice_pf2=#{invoicePf2},invoice_desc=#{invoiceDesc},project_id=#{projectId},document_id=#{documentId},talk_id=#{talkId}");
		sql.append(" where invoice_id=#{invoiceId}");
		YtbSql.update(sql,m);

	}

	public void delete(int invoiceId){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_project.cost_sum_invoice");
		sql.append(" where invoice_id=#{invoiceId}");
		YtbSql.delete(sql,invoiceId);

	}

	public CostSumInvoiceModel selectOne(int talkId,int documentId) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.cost_sum_invoice");
		sql.append(" where talk_id=#{talkId}");
		sql.append(" and document_id=#{documentId}");
		return YtbSql.selectOne(sql,documentId,CostSumInvoiceModel.class);

	}


	public List<CostSumInvoiceModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.cost_sum_invoice");
		sql.append(" where invoice_id=#{invoiceId}");
		return YtbSql.selectList(sql,id,CostSumInvoiceModel.class);

	}

	public List<CostSumInvoiceModel>  selectList(CostSumInvoiceModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.cost_sum_invoice");
		sql.append(" where invoice_id=#{invoiceId}");
		return YtbSql.selectList(sql,m,CostSumInvoiceModel.class);

	}

}
