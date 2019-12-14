package ytb.project.daoservice;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import ytb.project.common.ProjectConst;
import ytb.project.dao.ProjectFolderMapper;
import ytb.project.model.ProjectFolderModel;
import ytb.common.context.model.YtbError;

import java.util.List;

public class ProjectFolderDAOService extends DAOService implements ProjectFolderMapper  {
    //添加文件夹
    public Integer addFolderModel(ProjectFolderModel folder) {
        if (folder.getFolderStatus() <= ProjectConst.FOLDER_STATUS_INVALID) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR, "projectFolder.getFolderStatus() must >0");
        }
        try (SqlSession session = getSession()) {
            ProjectFolderMapper folderMapper = session.getMapper(ProjectFolderMapper.class);
            folderMapper.addFolderModel(folder);
            return folder.getFolderId();
        }
    }

    //修改文件夹
    public void modifyFolderModel(ProjectFolderModel folder) {

        try (SqlSession session = getSession()) {
            ProjectFolderMapper folderMapper = session.getMapper(ProjectFolderMapper.class);
            folderMapper.modifyFolderModel(folder);
            session.commit();
        }
    }



    //删除文件夹
    public void deleteFolderModel(int folderId) {

        try (SqlSession session = getSession()) {
            ProjectFolderMapper pfMapper = session.getMapper(ProjectFolderMapper.class);
            pfMapper.deleteFolderModel(folderId);
            session.commit();
        }
    }

    //查询文件夹
    public List<ProjectFolderModel> getFolderModels(int parentId) {
        try (SqlSession session = getSession()) {
            ProjectFolderMapper folderMapper = session.getMapper(ProjectFolderMapper.class);
            return folderMapper.getFolderModels(parentId);
        }
    }


    // select * from project_folder where parent_id =62428 and phase=601 and folder_type<7
    public ProjectFolderModel getPhaseFolderByParent(int parentFolderId, int phase, int projectId) {
        //System.out.println(parentFolderId); System.out.println(phase);
        // 60428 select * from project_folder where parent_id = #{parentId}  and phase = #{phase}
        // and project_id =#{projectId}   and folder_type &lt; 7
        try (SqlSession session = getSession()) {
            ProjectFolderMapper folderMapper = session.getMapper(ProjectFolderMapper.class);
            return folderMapper.getPhaseFolderByParent(parentFolderId, phase, projectId);

        }
    }

    public ProjectFolderModel getStopFolder( int talkFolder, int phase ) {

        try (SqlSession session = getSession()) {
            ProjectFolderMapper folderMapper = session.getMapper(ProjectFolderMapper.class);
            return folderMapper.getStopFolder(talkFolder, phase );

        }
    }

    //根据文件夹id来查询文件夹
    public ProjectFolderModel getFolderModel(int folderId) {
        try (SqlSession session = getSession()) {
            ProjectFolderMapper folderMapper = session.getMapper(ProjectFolderMapper.class);
            return folderMapper.getFolderModel(folderId);

        }
    }


    //查询乙方阶段文件夹
//    public ProjectFolderModel getTalkFolderModel(UserProjectContext context) {
//        return context.getProjectTalkModel().getTalkFolderModel();
//    }


    public List<ProjectFolderModel> getPhaseFolders(int talId, int phase) {
        try (SqlSession session = getSession()) {
            ProjectFolderMapper folderMapper = session.getMapper(ProjectFolderMapper.class);
            return folderMapper.getPhaseFolders(talId, phase);
        }
    }

    //查询一个成员本阶段的所有模板，不用递归
    public List<ProjectFolderModel> getProjectPhaseFolders(int projectId, int userId, int phase) {
        try (SqlSession session = getSession()) {
            ProjectFolderMapper folderMapper = session.getMapper(ProjectFolderMapper.class);
            return folderMapper.getProjectPhaseFolders(projectId, userId, phase);
        }
    }

    @Override
    public ProjectFolderModel getStopFolder(int projectId, int folderType, int userId) {
        return null;
    }

    public List<ProjectFolderModel> getUserPhaseFolders(int talkid, int userId, int phase) {
        try (SqlSession session = getSession()) {
            ProjectFolderMapper folderMapper = session.getMapper(ProjectFolderMapper.class);
            return folderMapper.getUserPhaseFolders(talkid, userId, phase);

        }
    }

    public List<ProjectFolderModel> getTalkFolders(@Param("projectId") int projectId, @Param("talkId") int talkid,
                                                   @Param("phase") int phase) {
        try (SqlSession session = getSession()) {
            ProjectFolderMapper folderMapper = session.getMapper(ProjectFolderMapper.class);
            return folderMapper.getTalkFolders(projectId, talkid, phase);

        }
    }

    public List<ProjectFolderModel> getTalkUserFolders(@Param("userId") int userId, @Param("projectId") int projectId,
                                                       @Param("talkId") int talkid, @Param("phase") int phase) {
        try (SqlSession session = getSession()) {
            ProjectFolderMapper folderMapper = session.getMapper(ProjectFolderMapper.class);
            return folderMapper.getTalkUserFolders(userId, projectId, talkid, phase);

        }
    }


    public List<ProjectFolderModel> getProjectFolders( @Param("projectId") int projectId  ) {
        try (SqlSession session = getSession()) {
            ProjectFolderMapper folderMapper = session.getMapper(ProjectFolderMapper.class);
            return folderMapper.getProjectFolders(  projectId );

        }
    }
    public List<ProjectFolderModel> getProjectTalkFolders( @Param("talkId") int talkId) {
        try (SqlSession session = getSession()) {
            ProjectFolderMapper folderMapper = session.getMapper(ProjectFolderMapper.class);
            return folderMapper.getProjectTalkFolders(   talkId );

        }
    }

}
