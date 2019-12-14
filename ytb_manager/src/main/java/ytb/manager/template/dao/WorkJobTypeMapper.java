package ytb.manager.template.dao;


import org.apache.ibatis.annotations.Param;
import ytb.manager.template.model.Dict_WorkJobTypeModel;

import java.util.List;

public interface WorkJobTypeMapper {

    List <Dict_WorkJobTypeModel> getWorkJobTypeList();

    Dict_WorkJobTypeModel getWorkJobType(int workJobTypeId);

    void addWorkJobType(Dict_WorkJobTypeModel workjobtype);

    void delWorkJobType(int workJobTypeId);

    void modifyWorkJobType(Dict_WorkJobTypeModel workjobtype);

    //根据id修改模板仓库状态
    int modifySateById(@Param("WorkJobTypeId") int WorkJobTypeId, @Param("state") int state);

}
