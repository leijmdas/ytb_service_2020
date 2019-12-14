package ytb.manager.template.dao;



import ytb.manager.template.model.Template_Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TemplateRepositoryMapper {

    //获取模板仓库列表
    List<Template_Repository> getTemplateRepositoryList();

    //获取已发布模板仓库列表
    List<Template_Repository> getPubTemplateRepositoryList();

    //添加模板仓库
    void addTemplateRepository(Template_Repository tr);

    //修改模板仓库
    void modifyTemplateRepository(Template_Repository tr);

    //删除模板仓库
    void delTemplateRepository(int repositoryId);

    //获取模板仓库数据
    Template_Repository getTemplateRepository(int repositoryId);

    //根据id修改模板仓库状态
    int modifySateById(@Param("repositoryId") int repositoryId, @Param("state") int state);


}
