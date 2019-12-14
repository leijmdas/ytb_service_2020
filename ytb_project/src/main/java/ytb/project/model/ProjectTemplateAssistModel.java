package ytb.project.model;

import ytb.common.context.model.Ytb_Model;

import java.sql.Timestamp;

public class ProjectTemplateAssistModel extends Ytb_Model {
	public static String TABLE_NAME = "ytb_project.project_template_assist";

	public ProjectTemplateAssistModel() {

	}
	public ProjectTemplateAssistModel(ProjectTemplateModel ptm) {

		this.setFolderId(0);
		this.setDocType((short) ptm.getDocType());
		this.setStatus((short) ptm.getStatus());

		this.setDocSeq(ptm.getDocSeq());
		this.setTitle(ptm.getTitle());
		this.setDocVer(ptm.getDocVer());
		this.setDocumentId(ptm.getDocumentId());

		this.setAuthor(this.getAuthor());
		this.setAuditor(ptm.getAuditor());

		this.setUpdateTime(new Timestamp(System.currentTimeMillis()));

	}
	private  ProjectDocumentModel documentModel;
	public ProjectDocumentModel getDocumentModel() {
		return documentModel;
	}

	public void setDocumentModel(ProjectDocumentModel documentModel) {
		this.documentModel = documentModel;
	}
	private Integer templateId;

	private Integer templateIdIni;

	private Integer folderId;

	private String docSeq;

	private String title;

	private String docVer;

	private Integer documentId;

	private Short status;

	private Short docType;

	//协助接收人
	private Integer author;

	//协助发起人
	private Integer auditor;

	private Timestamp updateTime;

	//协助类型(0分享 1协助)
	private int assistType;

	private int tradeId = 0 ;

	public int getTradeId() {
		return tradeId;
	}

	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}

	public Integer  getTemplateId() {
		return templateId;
	}

	public ProjectTemplateAssistModel setTemplateId( Integer templateId ) {
		this.templateId = templateId;
		return this;
	}

	public Integer  getTemplateIdIni() {
		return templateIdIni;
	}

	public ProjectTemplateAssistModel setTemplateIdIni( Integer templateIdIni ) {
		this.templateIdIni = templateIdIni;
		return this;
	}

	public Integer  getFolderId() {
		return folderId;
	}

	public ProjectTemplateAssistModel setFolderId( Integer folderId ) {
		this.folderId = folderId;
		return this;
	}

	public String  getDocSeq() {
		return docSeq;
	}

	public ProjectTemplateAssistModel setDocSeq( String docSeq ) {
		this.docSeq = docSeq;
		return this;
	}

	public String  getTitle() {
		return title;
	}

	public ProjectTemplateAssistModel setTitle( String title ) {
		this.title = title;
		return this;
	}

	public String  getDocVer() {
		return docVer;
	}

	public ProjectTemplateAssistModel setDocVer( String docVer ) {
		this.docVer = docVer;
		return this;
	}

	public Integer  getDocumentId() {
		return documentId;
	}

	public ProjectTemplateAssistModel setDocumentId( Integer documentId ) {
		this.documentId = documentId;
		return this;
	}

	public Short  getStatus() {
		return status;
	}

	public ProjectTemplateAssistModel setStatus( Short status ) {
		this.status = status;
		return this;
	}

	public Short  getDocType() {
		return docType;
	}

	public ProjectTemplateAssistModel setDocType( Short docType ) {
		this.docType = docType;
		return this;
	}

	public Integer  getAuthor() {
		return author;
	}

	public ProjectTemplateAssistModel setAuthor( Integer author ) {
		this.author = author;
		return this;
	}

	public Integer  getAuditor() {
		return auditor;
	}

	public ProjectTemplateAssistModel setAuditor( Integer auditor ) {
		this.auditor = auditor;
		return this;
	}

	public Timestamp  getUpdateTime() {
		return updateTime;
	}

	public ProjectTemplateAssistModel setUpdateTime( Timestamp updateTime ) {
		this.updateTime = updateTime;
		return this;
	}

	public int  getAssistType() {
		return assistType;
	}

	public ProjectTemplateAssistModel setAssistType( int assistType ) {
		this.assistType = assistType;
		return this;
	}

}
