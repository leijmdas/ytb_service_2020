package ytb.project.dao.tagtable;


import ytb.project.model.tagtable.WorkGroupModel;

public interface WorkGroupMapper {

    int addWorkGroup(WorkGroupModel workGroup);

    void modifyWorkGroup(WorkGroupModel workGroup);

    WorkGroupModel getWorkGroupById(int groupId);

    WorkGroupModel getWorkGroupByTalkId(int talkId);

}
