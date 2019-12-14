package ytb.manager.template.dao;


import ytb.manager.template.model.Dict_WorkJobModel;

import java.util.List;

public interface WorkJobMapper {

    List<Dict_WorkJobModel> getWorkJobList(int WorkJobTypeId);

    Dict_WorkJobModel getWorkJob(int WorkJobId);

    Dict_WorkJobModel getWorkJobBy(int WorkJobTypeId);

    void addWorkJob(Dict_WorkJobModel WorkJob);

    void delWorkJob(int WorkJobId);

    void modifyWorkJob(Dict_WorkJobModel WorkJob);

}
