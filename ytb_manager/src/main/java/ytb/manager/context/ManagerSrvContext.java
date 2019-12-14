package ytb.manager.context;

import ytb.manager.tagtable.service.ITagTableService;
import ytb.manager.tagtable.service.impl.TagTableServiceImpl;
import ytb.manager.template.service.*;
import ytb.manager.template.service.impl.*;
import ytb.manager.templateexcel.service.TemplateDocumentService;
import ytb.manager.templateexcel.service.impl.TemplateDocumentServiceImpl;

public class ManagerSrvContext {

    static ManagerSrvContext inst = new ManagerSrvContext();

    public static ManagerSrvContext getInst() {
        return inst;
    }
    private ManagerSrvContext(){

    }


    protected ProjectTypeService projectTypeService = new ProjectTypeServiceImpl();
    protected TemplateRepositoryService templateRepositoryService = new TemplateRepositoryServiceImpl();
    protected WorkJobTypeService workJobTypeService = new WorkJobTypeServiceImpl();

    TemplateService templateService = new TemplateServiceImpl();

    TemplateDocumentService templateDocumentService = TemplateDocumentServiceImpl.getTemplateDocumentService();

    ITagTableService tagTableService = new TagTableServiceImpl();
    IDocumentService documentService  = new DocumentServiceImpl();

    public TemplateService getTemplateService() {
        return templateService;
    }

    public IDocumentService getDocumentService() {
        return documentService;
    }

    public ITagTableService getTagTableService() {
        return tagTableService;
    }

    public TemplateDocumentService getTemplateDocumentService() {
        return templateDocumentService;
    }


    public ProjectTypeService getProjectTypeService() {
        return projectTypeService;
    }


    public TemplateRepositoryService getTemplateRepositoryService() {
        return templateRepositoryService;
    }



    public WorkJobTypeService getWorkJobTypeService() {
        return workJobTypeService;
    }




}
