package ytb.manager.tagtable.service;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.manager.template.model.Dict_TemplateModel;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ITemplateSetUpRefAll {

    void updateRefDocTimeSync(int projctId,int documentId);
    //更新目标文档同步时间
    void updateRefPDocTime(int projctId,int pDocumentId);

    void setUpRefAll(int repositoryId, int projectId,long userId) throws
            UnsupportedEncodingException;

    List<Dict_TemplateModel> spFindAllTemplateByRepository(int repositoryId);

    //void spDeleteAllTemplateByRepository(int repositoryId);


}

