package ytb.manager.templateexcel.service.impl;

import ytb.manager.tagtable.model.DBTagTable;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentInfo;
import ytb.manager.templateexcel.service.ICheckTemplate;

import java.io.IOException;
import java.util.Map;

public class CheckTemplate implements ICheckTemplate {

    /**
     * @Author qsy

     * @Description sortTemplateTable
     * 对模板中表格的数据进行排序
     * @Date 10:08 2019/4/19
     * @Param [documentId,sortModel]:文档id、排序类型--->>>asc、desc
     * @return void
     **/
    public Map<String, DBTagTable> sortTable(int documentId, String ascDesc) throws IOException {
        return new SortTemplateTable().sort(documentId,ascDesc);
    }


    public Map<String, DBTagTable> sortTable(TemplateDocumentInfo info, String ascDesc) throws IOException{
        return new SortTemplateTable().sort(info,ascDesc);

    }

    /**
     * @return void
     * checkTemplateTableUnique
     * @Author qsy
     * @Description checkTemplateTable
     * 检查模板中表格的数据唯一性
     * @Date 18:28 2019/4/17
     * @Param [documentId]:文档id
     **/
    @Override
    public void checkTable(int documentId) throws IOException {
        new CheckTemplateTableUnique().check(documentId);
    }

    public void checkTable(TemplateDocumentInfo info) throws IOException {
        CheckTemplateTableUnique checkTemplateTableUnique = new CheckTemplateTableUnique();
        checkTemplateTableUnique.check(info);
        if (info.isTemplate_TYPE_workplan_NonePp()) {
            checkTemplateTableUnique.checkJobRepeat(info);
        }
    }

    @Override
    public void checkJobRepeat(TemplateDocumentInfo info) {
        if(info.isTemplate_TYPE_workplan_NonePp()){
            new CheckTemplateTableUnique().checkJobRepeat( info );
        }
    }

}
