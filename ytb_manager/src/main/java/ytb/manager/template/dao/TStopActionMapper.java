package ytb.manager.template.dao;



import ytb.manager.template.model.Dict_ConstraintModel;
import ytb.manager.template.model.T_Stop_ActionModel;

import java.util.List;

public interface TStopActionMapper {

    List<T_Stop_ActionModel> getList(int templateId);

    T_Stop_ActionModel get(int actionId);

    int add(T_Stop_ActionModel sam);

    void del(int actionId);

    void modify(T_Stop_ActionModel sam);

}