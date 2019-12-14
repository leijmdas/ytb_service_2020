package ytb.manager.tagtable.service.tagtemplate;

import ytb.common.utils.YtbSql;
import ytb.manager.context.ManagerSrvContext;
import ytb.manager.tagtable.service.ITemplateSetUpRefAll;
import ytb.manager.template.model.Dict_TemplateModel;
import ytb.manager.templateexcel.model.xls.TemplateDocumentHeader;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class TemplateSetUpRefAll implements ITemplateSetUpRefAll {
    protected List<Dict_TemplateModel> lstTemplate;
    protected TemplateDocumentRefService template_dr = new TemplateDocumentRefService();
    protected ProjectDocumentRefService  project_dr = new ProjectDocumentRefService();


    public void setUpRefAll(int repositoryId, int projectId, long userId) throws
            UnsupportedEncodingException {

        int ret = projectId == 0 ? setUpRefAll_template(repositoryId) : setUpRefAll_project(projectId,userId);

    }

    @Override
    public void updateRefDocTimeSync(int projectId, int documentId) {
        (projectId == 0 ? template_dr : project_dr).updateRefDocTimeSync(projectId, documentId);
    }

    @Override
    public void updateRefPDocTime(int projectId, int pDocumentId) {
        (projectId==0?template_dr:project_dr).updateRefPDocTime(projectId,pDocumentId);

    }

    //call ytb_manager.spFindAllTemplateByRepository(12);
    public List<Dict_TemplateModel> spFindAllTemplateByRepository(int repositoryId) {
        return YtbSql.spDb(Dict_TemplateModel.class, "ytb_manager.spFindAllTemplateByRepository", repositoryId);
    }

    public int setUpRefAll_project(List<Dict_TemplateModel> lst, TemplateDocumentHeader docHeader) throws UnsupportedEncodingException {
        lstTemplate = lst;
        docHeader.setRepositoryId(0);
        (docHeader.getProjectId() == 0 ? template_dr : project_dr).deleteRef(docHeader);
        return setUpRefAll_template(lstTemplate, docHeader);

    }

    int setUpRefAll_project(int projectId, long userId) throws
            UnsupportedEncodingException {
        TemplateDocumentHeader pHeader = new TemplateDocumentHeader();
        pHeader.setRepositoryId(0);
        pHeader.setProjectId(projectId);
        pHeader.setUserId(userId);
        lstTemplate = spFindAllTemplateByRepository(projectId);
        (projectId == 0 ? template_dr : project_dr).deleteRef(pHeader);
        return setUpRefAll_template(lstTemplate, pHeader);

    }

    int setUpRefAll_template(int repositoryId) throws UnsupportedEncodingException {
        lstTemplate = spFindAllTemplateByRepository(repositoryId);
        TemplateDocumentHeader pHeader=new TemplateDocumentHeader ();
        pHeader.setRepositoryId(repositoryId);
        pHeader.setProjectId(0);
        pHeader.setTalkId(0);
        pHeader.setUserId(0l);
        template_dr.deleteRef(pHeader);

        return setUpRefAll_template(lstTemplate,pHeader);
    }



    protected int setUpRefAll_template(List<Dict_TemplateModel> templateModels, TemplateDocumentHeader pHeader) throws UnsupportedEncodingException {


        TemplateDocumentHeader header = new TemplateDocumentHeader(pHeader);
        header.setUpReqWorkplan_dict(templateModels);
        //header.setReqId(findReq(templateModels)); header.setWorkplanId(findWorkplan(templateModels));

        for (Dict_TemplateModel templateModel : templateModels) {
            header.setDocType(templateModel.getDocType());
            header.setTemplateId(templateModel.getTemplateId());
            header.setDocumentId(templateModel.getDocumentId());
            if (!templateModel.isArchive()) {
                if (header.getProjectId() == 0) {
                     ManagerSrvContext.getInst().getDocumentService()
                             .modifyReDocumentHeader(header.getDocumentId(), header);
                }
                if (!header.checkTemplateReqTrue()) {
                    (header.getProjectId() == 0 ? template_dr : project_dr).insertRefReq(header);
                    if (!header.checkTemplateWorkplanTrue()) {
                        (header.getProjectId() == 0 ? template_dr : project_dr).insertRefWorkplan(header);
                    }
                }
            }

        }
        //增加模板关系记录
        return templateModels.size();
    }

//    int findWorkplan(List<Dict_TemplateModel> lst) {
//        for (Dict_TemplateModel m : lst) {
//            if (m.isTemplateWorkplan()) {
//                return m.getDocumentId();
//            }
//        }
//        return 0;
//    }
//
//    int findReq(List<Dict_TemplateModel> lst) {
//        for (Dict_TemplateModel m : lst) {
//            if (m.isTemplateReq()) {
//                return m.getDocumentId();
//            }
//        }
//        return 0;
//    }
    //    public void setUpReqWorkplan(TemplateDocumentHeader header,List<Dict_TemplateModel> models) {
//        for (Dict_TemplateModel model : models) {
//            if (model.isTemplateReq()) {
//                header.setReqId(model.getDocumentId());
//            }
//            if (model.isTemplateWorkplan()) {
//                header.setWorkplanId(model.getDocumentId());
//            }
//        }
//
//    }

}
