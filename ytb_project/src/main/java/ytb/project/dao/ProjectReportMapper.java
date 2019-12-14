package ytb.project.dao;

import ytb.project.model.ProjectReportModel;

/**
 * Package: ytb.project.dao
 * Author: ZCS
 * Date: Created in 2018/12/24 14:26
 */
public interface ProjectReportMapper {
    //添加举报
    void addProReport(ProjectReportModel reportModel);
}
