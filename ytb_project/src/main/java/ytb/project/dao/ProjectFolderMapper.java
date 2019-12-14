package ytb.project.dao;


import org.apache.ibatis.annotations.Param;
import ytb.project.model.ProjectFolderModel;

import java.util.List;
//folderType

public interface ProjectFolderMapper {

    //添加项目文件夹
    Integer addFolderModel(ProjectFolderModel folder);
    void deleteFolderModel(int folderId);
    void modifyFolderModel(ProjectFolderModel folder);

    //返回单个文件夹

    ProjectFolderModel getFolderModel(int folderId);
    ProjectFolderModel getPhaseFolderByParent(@Param("parentId") int parentId, @Param("phase") int phase, @Param("projectId") int projectId);
    ProjectFolderModel getStopFolder(@Param("talkFolder") int talkFolder, @Param("phase") int phase);

    //查询子文件夹返回多个文件夹
    List<ProjectFolderModel> getFolderModels(int parentId);

    List<ProjectFolderModel> getProjectFolders(@Param("projectId") int projectId);
    List<ProjectFolderModel> getProjectTalkFolders(@Param("talkId")int talkId);
    //优先使用
    List<ProjectFolderModel> getTalkFolders(@Param("projectId") int projectId,@Param("talkId") int talkid, @Param(  "phase") int phase);
    List<ProjectFolderModel> getTalkUserFolders(@Param("userId") int userId,@Param("projectId") int projectId,@Param(
            "talkId") int talkid, @Param( "phase") int phase);

    //根据userId和talk查询
    List<ProjectFolderModel> getPhaseFolders(@Param("talkId") int talkid, @Param("phase") int phase);
    List<ProjectFolderModel> getUserPhaseFolders(@Param("talkId") int talkid, @Param("userId") int userId, @Param("phase") int phase);
    //查询乙方阶段文件夹
    List<ProjectFolderModel> getProjectPhaseFolders(@Param("projectId") int projectId, @Param("userId") int userId, @Param("phase") int phase);

    //获取终止文件夹
    ProjectFolderModel getStopFolder(@Param("projectId") int projectId,@Param("folderType") int
            folderType,@Param("userId") int userId);



}
