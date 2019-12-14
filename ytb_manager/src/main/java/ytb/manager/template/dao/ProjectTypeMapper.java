package ytb.manager.template.dao;


import org.apache.ibatis.annotations.Param;
import ytb.manager.template.model.Dict_ProjectTypeModel;

import java.util.List;
import java.util.Map;

public interface ProjectTypeMapper {

    List<Dict_ProjectTypeModel> getProjectTypeList();

    Dict_ProjectTypeModel getProjectType(int projectTypeId);

    void addProjectType(Dict_ProjectTypeModel projectTypeModel);

    void delProjectType(int projectTypeId);

    void modifyProjectType(Dict_ProjectTypeModel projectTypeModel);

    List<Dict_ProjectTypeModel> getProjectTypeByParent(int ParentId);

    int modifySateById(@Param("projectTypeId") int projectTypeId, @Param("state") int state);


}
