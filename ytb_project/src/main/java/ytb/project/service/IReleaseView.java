package ytb.project.service;


import ytb.project.context.UserProjectContext;
import ytb.project.model.projectview.ProjectResultViewModel;

import java.util.List;
import java.util.Map;

public interface IReleaseView extends ProjectReleaseService {
    List<Map<String, Object>> getProjectListOfCompany(Map map);

    //查询所有项目列表
    ProjectResultViewModel getProjectLists(UserProjectContext context, Integer status);

    //已完成项目简介
    List<Map<String, Object>> getFinishProjectView(int userId, int projectId);

    void addViews(int projectId);

    void addFavorite(UserProjectContext context, int projectId, int status);

}
