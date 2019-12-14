package ytb.manager.tagtable.service.tagtemplate;

import ytb.manager.tagtable.model.tagtemplate.Document_RefModel;
import ytb.manager.tagtable.service.IDocumentRefService;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.manager.templateexcel.model.xls.TemplateDocumentHeader;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;

public class DocumentRefService implements IDocumentRefService {
    static TemplateDocumentRefService dictDocS = new TemplateDocumentRefService();
    static ProjectDocumentRefService projectDocS = new ProjectDocumentRefService();
    static DocumentRefService documentRefService = new DocumentRefService();

    public static DocumentRefService getDocumentRefService() {
        return documentRefService;
    }
    //模板转换测试使用projectId=0
    static TemplateDocumentRefService getDocumentRefService(int projectId) {
        return projectId == 0 ? dictDocS : projectDocS;
    }
    private DocumentRefService(){

    }

    @Override
    public void setUpRefAll(int repositoryId, int projectId,long userId) throws
            UnsupportedEncodingException {
        //找出所有表建立关系
        getDocumentRefService(projectId).setUpRefAll(repositoryId, projectId,userId);
    }

    @Override
    public void updateRefDocTimeSync(int projectId,int documentId) {
        getDocumentRefService(projectId).updateRefDocTimeSync( projectId,documentId);

    }

    @Override
    public void updateRefPDocTime(int projectId,int pDocumentId) {
        getDocumentRefService(projectId).updateRefPDocTime( projectId,pDocumentId);

    }

    @Override
    public List<Dict_TemplateModel> spFindAllTemplateByRepository(int repositoryId, int projectId) {
       return getDocumentRefService(projectId).spFindAllTemplateByRepository(repositoryId, projectId);
    }

    @Override
    public List<Dict_TemplateModel> spFindAllTemplateByWorkjobType(int workjobType, int projectId) {
        return getDocumentRefService(projectId).spFindAllTemplateByWorkjobType(workjobType, projectId);
    }

    @Override
    public int insertRefReqWorkplan(TemplateDocumentHeader header) {
        return getDocumentRefService(header.getProjectId()).insertRefReqWorkplan(header);

    }

    @Override
    public int insertRefReq(TemplateDocumentHeader header) {
        return getDocumentRefService(header.getProjectId()).insertRefReq(header);
    }

    @Override
    public int insertRefWorkplan(TemplateDocumentHeader header) {
        return getDocumentRefService(header.getProjectId()).insertRefWorkplan(header);
    }


    public int findRefProject(int projectId, int documentId, short pDocType) {
        return getDocumentRefService(projectId).findRef(0, projectId, documentId, pDocType);
    }

    @Override
    public int findChildWorkplanId(int repositoryId, int projectId, int pDocumentId) {
        Document_RefModel m = getDocumentRefService(projectId).findDocumentChildModel(repositoryId, projectId, pDocumentId, (short) 200);
        return m == null ? 0 : m.getDocumentId();
    }

    @Override
    public int insertRef(Document_RefModel m) {

        return getDocumentRefService(m.getProjectId()).insertRef(m);

    }

    @Override
    public void updateRef(Document_RefModel m) {
        getDocumentRefService(m.getProjectId()).updateRef(m);

    }

    @Override
    public void deleteRefById(int projectId,int refId) {
          getDocumentRefService(projectId).deleteRefById(projectId,refId);
    }

    @Override
    public void deleteRef(TemplateDocumentHeader docHeader ) {
        getDocumentRefService(docHeader.getProjectId()).deleteRef(docHeader);
    }


    @Override
    public void deleteRefByDocumentId(int projectId,int documentId) {
        getDocumentRefService(projectId).deleteRefByDocumentId(projectId,documentId);

    }

    @Override
    public void deleteRefByPDocId(int projectId, int pDocId) {
        getDocumentRefService(projectId).deleteRefByPDocId(projectId,pDocId);

    }

    public int findRef(int repositoryId, int projectId, int documentId, short pDocType) {
        return getDocumentRefService(projectId).findRef(repositoryId, projectId, documentId, pDocType);
    }

    public int findRefReqId(int repositoryId, int projectId, int documentId) {
        return findRef(repositoryId, projectId, documentId, (short) Dict_TemplateModel.Template_TYPE_req);
    }


    public int findRefWorkplanId(int repositoryId,int projectId, int documentId) {
        return findRef(repositoryId, projectId, documentId, (short) Dict_TemplateModel.Template_TYPE_workplan);

    }


    @Override
    public Document_RefModel findDocumentRefModel(int repositoryId, int projectId, int documentId, short pDocType) {
        return getDocumentRefService(projectId).findDocumentRefModel(repositoryId, projectId, documentId, pDocType);

    }

    @Override
    public Document_RefModel findDocumentChildModel(int repositoryId, int projectId, int pDocumentId, short docType) {
        return getDocumentRefService(projectId).findDocumentRefModel(repositoryId, projectId, pDocumentId, docType);
    }

    public boolean needSyncRef(int repositoryId, int projectId, int documentId, short pDocType) {
        Document_RefModel m =  findDocumentRefModel(repositoryId, projectId, documentId, pDocType);
        return m.getDocTimeSync().before( m.getpDocTime() );

    }

    public void updateSyncTime(int repositoryId, int projectId, int documentId, short pDocType, Timestamp syncTime) {
        Document_RefModel m = findDocumentRefModel(repositoryId, projectId, documentId, pDocType);
        m.setDocTimeSync(syncTime);
        updateRef(m);
    }

    public void updatePDocTime(int repositoryId, int projectId, int documentId, short pDocType,Timestamp pDocTime) {
        Document_RefModel m = findDocumentRefModel(repositoryId, projectId, documentId, pDocType);
        m.setpDocTime(pDocTime);
        updateRef(m);
    }

}
