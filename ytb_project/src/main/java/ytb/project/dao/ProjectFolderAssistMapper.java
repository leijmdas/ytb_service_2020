package ytb.project.dao;

import ytb.project.model.projectview.ProjectAssistViewModel;

import java.util.List;

/**
 * Package: ytb.project.dao
 * Author: ZCS
 * Date: Created in 2019/2/26 13:10
 */
public interface ProjectFolderAssistMapper {

    //获取协助的项目列表
    List<ProjectAssistViewModel> getProjectAssistList(int userId);

}
