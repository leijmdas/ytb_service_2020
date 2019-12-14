package ytb.project.service;

import ytb.project.model.UserShareModel;

import java.util.List;

public interface IFlowFolderAssist extends IFlowFolder {

    //查询共享
    List<UserShareModel> getUserShareList(int projectId, int userId);

    //查询协助
    List<UserShareModel> getUserAssitList(int projectId, int userId);

    //查询审核
    List<UserShareModel> getUserAuditList(int projectId, int userId);

}
