package ytb.project.service.project.change.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.context.UserProjectContext;
import ytb.project.dao.ProjectTemplateUserMapper;
import ytb.project.model.ProjectTemplateUserModel;
import ytb.project.model.ProjectFolderModel;
import ytb.project.model.projectFolderView.projectTemplate.ProjectChange.CopyTemplateModelChange;

import ytb.project.service.impl.flow.ProjectFileServiceImpl;

import java.util.List;
import java.util.Map;

public class FolderChangeService extends ProjectFileServiceImpl {


    public void copyTemplate(UserProjectContext context, ProjectFolderModel folder, int projectId) {
        Map<Integer, Integer> doc_map = copyPhaseFolderList(context, folder, folder.getFolderId());
        copyProjectTemplateUserModel(projectId, doc_map);
    }

    Integer getPA_user_id(int projectId) {//查询甲方，返回userId
        StringBuilder sql = new StringBuilder(256);
        sql.append(" select user_id1 from ytb_project.project ");
        sql.append(" where project_id = ").append(projectId);
        return YtbSql.selectOne(sql, Integer.class);
    }

    void copyProjectTemplateUserModel(int projectId, Map<Integer, Integer> doc_map  ) {

        StringBuilder sql = new StringBuilder(256);//查询不属于甲方的所有控制文档
        sql.append(" select * from ytb_project.document_user_status ");
        sql.append(" where project_id =").append(projectId);
        sql.append(" and doc_user_id <> ").append(getPA_user_id(projectId));
        
        List<ProjectTemplateUserModel> lst = YtbSql.selectList(sql, ProjectTemplateUserModel.class);

        SqlSession session = getSession (false);
        ProjectTemplateUserMapper templateUserMapper = session.getMapper(ProjectTemplateUserMapper.class);
        try {
            for (ProjectTemplateUserModel templateUserModel : lst) {
                templateUserModel.setTemplateId(doc_map.get(templateUserModel.getTemplateId()));//map Get老的ID替换成新ID（documernt_Id）
                templateUserMapper.addProjectTemplateUserModel(templateUserModel);
            }
            session.commit();
        } catch (Exception ex) {
            session.rollback();
            throw ex;
        } finally {
            session.close();
        }
    }


    //阶段拷贝文件夹
    private Map<Integer, Integer> copyPhaseFolderList(UserProjectContext context,ProjectFolderModel folder, int folderId) {
        //获取该文件夹下的文档
        List<ProjectFolderModel> list = getFolderModels(folder.getFolderId());//查询子文件夹
        if (folder.getParentId() == 0) {
            folder.setParentId(0);//设置新文件夹的父id
        } else {
            folder.setParentId(folderId);//设置新文件夹的父id
        }

        addFolderModel(folder);//copy文件夹

        Map<Integer, Integer> template_map = new CopyTemplateModelChange().copyPBTalkTemplate(context,
                folder.getFolderId());
        for (ProjectFolderModel pf : list) {
            Map<Integer, Integer> map = copyPhaseFolderList(context,pf, folder.getFolderId());
            template_map.putAll(map);
        }
        return template_map;

    }


    public void delFolderList(int folderId) {

        List<ProjectFolderModel> list = getFolderModels(folderId);//查询子文件夹
        for(ProjectFolderModel pf : list){
            deleteFolderModel(pf.getFolderId());
        }
        deleteFolderModel(folderId);
    }



}
