package ytb.project.context;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.model.ProjectTemplateModel;
import ytb.manager.tagtable.service.tagtemplate.ProjectSetUpRefAll;

import java.io.UnsupportedEncodingException;
import java.util.List;
/*
 * author leijming
 * date: 2019.3.3
 * */
/*
 *
 * 建立项目所有模板的引用关联关系
 *
 * */
public class DocumentFamily extends ProjectSetUpRefAll implements IDocumentFamily {

    //models需要建立文档关系的所有 文档
    //pc建立关系的信息
    public int setUpRefAll(ProjectContext pc, List<ProjectTemplateModel> models) throws UnsupportedEncodingException {
        return setUpRef(pc, models, models);
    }
    //reqPlanModels 需求计划书， models需要建立文档关系的所有 文档

    //阶段拷贝建立关系
    public int setUpRefAllPhase(ProjectContext pc,  List<ProjectTemplateModel> reqPlanModels,
                                List<ProjectTemplateModel> models) throws UnsupportedEncodingException {

        return setUpRef(pc, reqPlanModels, models);
    }


    //isPhase  true则为阶段拷贝
    int setUpRef(ProjectContext pc, List<ProjectTemplateModel> reqPlanModels, List<ProjectTemplateModel> models) throws UnsupportedEncodingException {

        pc.check();
        //设定需求说明书和工作计划书
        pc.setUpReqWorkplan(reqPlanModels);
        //checkReqWorkplan
        pc.checkReqWorkplan();
        //project_dr.deleteRef(pc); //delete by documentId最安全
        setUpRef(pc, models);

        //增加模板关系记录
        return models.size();
    }

    void setUpRef(ProjectContext pc, List<ProjectTemplateModel> models) throws UnsupportedEncodingException {
        for (ProjectTemplateModel model : models) {
            if (model.isArchive()) {
                continue;
            }
            //可以生成SQL语句
            deleteRef(model.getDocumentId());
            pc.setDocType(model.getDocType());
            pc.setDocumentId(model.getDocumentId());
            //pc.setTemplateId(model.getTemplateId());
            if (!pc.checkTemplateReqTrue()) {
                project_dr.insertRefReq(pc);
                if (!pc.checkTemplateWorkplanTrue()) {
                    project_dr.insertRefWorkplan(pc);
                }
            }

        }
    }

    void deleteRef(int documentId) {
        StringBuilder sql = new StringBuilder(128);
        sql.append(" delete from ").append(project_dr.getTableName());
        sql.append(" where document_id=#{documentId} ");
        YtbSql.delete(sql, documentId);

    }

    //加phase
//    public void deleteRef(TemplateDocumentHeader pHeader) {
//        StringBuilder sql = new StringBuilder(256);
//        sql.append(" delete from ").append(project_dr.getTableName());
//        sql.append(" where project_id=#{projectId} ");
//        sql.append(" and user_id=  ").append(pHeader.getUserId());
//        sql.append(" and phase =  ").append(pHeader.getPhase());
//        YtbSql.delete(sql, pHeader.getProjectId());
//
//    }

}



