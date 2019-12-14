package ytb.manager.tagtable.service.tagtemplate;

public class ProjectDocumentRefService extends TemplateDocumentRefService {
    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    String tableName = "ytb_project.project_document_ref";



}
