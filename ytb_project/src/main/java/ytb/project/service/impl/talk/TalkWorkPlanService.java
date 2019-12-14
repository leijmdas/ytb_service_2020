package ytb.project.service.impl.talk;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.project.common.ProjectConst;
import ytb.project.context.UserProjectContext;
import ytb.project.dao.tagtable.IBuyPriceListPModelService;
import ytb.project.dao.tagtable.IProcessListPModelService;
import ytb.project.daoservice.BuyPriceListPModelServiceImpl;
import ytb.project.daoservice.ProcessListPModelServiceImpl;
import ytb.project.daoservice.ProjectWorkPlanDAOService;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTalkModel;
import ytb.project.model.tagtable.BuyPriceListPModel;
import ytb.project.model.tagtable.ProcessListPModel;
import ytb.project.model.tagtable.ProjectPlanModel;

import java.util.Date;

//工作计划表信息：
//WorkGroupModel workGroup;
//  ①工作组-- ②工作组成员--  ③组员岗位表--   ④组员岗位任务表
//  ⑤项目天数一览表
//  ⑥工作计划里程碑
//  ⑦费用一览表
//  ⑧乙方采购加工费预算一览表

//  ⑨总任务一览表
//  ⑩总工作计划表
//  ⑪人员任务一览表

//项目工作组计划
public final class TalkWorkPlanService extends ProjectWorkPlanDAOService {
    IBuyPriceListPModelService buyPriceListPModelService = new BuyPriceListPModelServiceImpl();
    IProcessListPModelService processListPModelService = new ProcessListPModelServiceImpl();
    //获取 结束时间
    public Date getEndTime(UserProjectContext context) {
        if (context.isPurchase()) {
            return getEndTimePurchase(context);
        } else if (context.isProcessing()) {
            return getEndTimeProcess(context);
        }
        return getEndTimeNonPp(context);
    }

    //工作计划书文档
    //获取阶段结束时间
    public Date getPhaseEndTime(UserProjectContext context) {
        if (context.isPurchase()) {
            return getEndTimePurchase(context);
        } else if (context.isProcessing()) {
            return getEndTimeProcess(context);
        }
        return getPhaseEndTimeNonPp(context);
    }

    Date getEndTimeProcess(UserProjectContext context) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        ProcessListPModel listPModel = processListPModelService.selectOne(pm.getProjectId(), ptm.getWorkplanId());

        return  YtbUtils.dateAddDays(ptm.getPayDate(),listPModel.getDay());
    }

    Date getEndTimePurchase(UserProjectContext context) {
        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();
        BuyPriceListPModel listPModel = buyPriceListPModelService.selectOne(pm.getProjectId(), ptm.getWorkplanId());

        return  YtbUtils.dateAddDays(ptm.getPayDate(),listPModel.getDay1());
    }

    //获取项目结束时间
    Date getEndTimeNonPp(UserProjectContext context) {

        ProjectModel pm = context.getProjectModel();
        return getPhaseEndTimeNonPp(context,pm.getPhaseEnd());

    }

    //获取阶段结束时间
    Date getPhaseEndTimeNonPp(UserProjectContext context) {

        ProjectTalkModel ptm = context.getProjectTalkModel();
        return getPhaseEndTimeNonPp(context,ptm.getPhase());

    }

    Date getPhaseEndTimeNonPp(UserProjectContext context, int phase) {

        ProjectModel pm = context.getProjectModel();
        ProjectTalkModel ptm = context.getProjectTalkModel();

        ProjectPlanModel projectPlan = getProjectPlan(pm.getProjectId(), ptm.getWorkplanId());
        if (projectPlan == null) {
            return new Date();
        }

        Date endTime = new Date() ;
        if (phase == ProjectConst.Phase0) {
            endTime = projectPlan.getPhase0();
        } else if (phase == ProjectConst.Phase1) {
            endTime = projectPlan.getPhase1();
        } else if (phase == ProjectConst.Phase2) {
            endTime = projectPlan.getPhase2();
        } else if (phase == ProjectConst.Phase3) {
            endTime = projectPlan.getPhase3();
        } else if (phase == ProjectConst.Phase4) {
            endTime = projectPlan.getPhase4();
        } else if (phase == ProjectConst.Phase5) {
            endTime = projectPlan.getPhase5();
        } else if (phase == ProjectConst.Phase6) {
            endTime = projectPlan.getPhase6();
        } else if (phase == ProjectConst.Phase7) {
            endTime = projectPlan.getPhase7();
        } else if (phase == ProjectConst.Phase8) {
            endTime = projectPlan.getPhase7();
        } else if (phase == ProjectConst.Phase9) {
            endTime = projectPlan.getPhase7();
        }
        return endTime;
    }

}
