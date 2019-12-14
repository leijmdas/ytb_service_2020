package ytb.manager.template.dao;


import ytb.manager.template.model.Dict_WorkJob_Check;

import java.util.List;

public interface WorkJobCheckMapper {

    List getConstraintList(int WorkJobTypeId);

    Dict_WorkJob_Check getConstraint(int ConstraintId);

    //添加
    void addConstraint(Dict_WorkJob_Check constraint);

    void delConstraint(int ConstraintId);

    void modifyConstraint(Dict_WorkJob_Check constraint);

    //查询
    List<Dict_WorkJob_Check> getWorkJobCheckDetailsListBy(int templateId);


    //删除
    void delWorkJobCheckBy(int checkId);

    //修改
    void modifyWorkJobCheck(Dict_WorkJob_Check workJobCheck);


}
