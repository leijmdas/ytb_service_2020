package ytb.manager.template.dao;



import ytb.manager.template.model.Dict_ConstraintModel;

import java.util.List;

public interface ConstraintMapper {

    List getConstraintList(int WorkJobTypeId);

    Dict_ConstraintModel getConstraint(int ConstraintId);

    void addConstraint(Dict_ConstraintModel constraint);

    void delConstraint(int ConstraintId);

    void modifyConstraint(Dict_ConstraintModel constraint);

}
