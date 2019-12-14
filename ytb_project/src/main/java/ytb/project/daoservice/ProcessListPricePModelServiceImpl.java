package ytb.project.daoservice;

import java.util.List;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.dao.tagtable.IProcessListPricePModelService;
import ytb.project.model.tagtable.ProcessListPricePModel;

public class ProcessListPricePModelServiceImpl implements IProcessListPricePModelService {

	public int insert(ProcessListPricePModel m) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("insert into ytb_project.process_list_price_p");
		sql.append("(process_list_price_p_id,project_id,document_id,day,received_user,received_phone,received_addr,received_company_name,received_company_credit_code,received_company_addr,send_user,send_phone,send_addr,send_company_name,send_company_credit_code,send_company_addr,talk_id,invoice_type,package_requirement)");
		sql.append("values");		
		sql.append("(#{processListPricePId},#{projectId},#{documentId},#{day},#{receivedUser},#{receivedPhone},#{receivedAddr},#{receivedCompanyName},#{receivedCompanyCreditCode},#{receivedCompanyAddr},#{sendUser},#{sendPhone},#{sendAddr},#{sendCompanyName},#{sendCompanyCreditCode},#{sendCompanyAddr},#{talkId},#{invoiceType},#{packageRequirement})");
		return YtbSql.insert(sql,m);

	}
	public void update(ProcessListPricePModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("update ytb_project.process_list_price_p");
		sql.append("set process_list_price_p_id=#{processListPricePId},project_id=#{projectId},document_id=#{documentId},day=#{day},received_user=#{receivedUser},received_phone=#{receivedPhone},received_addr=#{receivedAddr},received_company_name=#{receivedCompanyName},received_company_credit_code=#{receivedCompanyCreditCode},received_company_addr=#{receivedCompanyAddr},send_user=#{sendUser},send_phone=#{sendPhone},send_addr=#{sendAddr},send_company_name=#{sendCompanyName},send_company_credit_code=#{sendCompanyCreditCode},send_company_addr=#{sendCompanyAddr},talk_id=#{talkId},invoice_type=#{invoiceType},package_requirement=#{packageRequirement}");
		sql.append(" where null");
		YtbSql.update(sql,m);

	}

	public void delete(int id ){
		StringBuilder sql=new StringBuilder(256);
		sql.append("delete from ytb_project.process_list_price_p");
		sql.append(" where null=null");
		YtbSql.delete(sql,id);

	}

	public ProcessListPricePModel selectOne(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.process_list_price_p");
		sql.append(" where null=null");
		return YtbSql.selectOne(sql,id,ProcessListPricePModel.class);

	}


	public List<ProcessListPricePModel> selectList(int id) {
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.process_list_price_p");
		sql.append(" where null=null");
		return YtbSql.selectList(sql,id,ProcessListPricePModel.class);

	}

	public List<ProcessListPricePModel>  selectList(ProcessListPricePModel m){
		StringBuilder sql=new StringBuilder(256);
		sql.append("select *  from ytb_project.process_list_price_p");
		sql.append(" where null=null");
		return YtbSql.selectList(sql,m,ProcessListPricePModel.class);

	}

}
