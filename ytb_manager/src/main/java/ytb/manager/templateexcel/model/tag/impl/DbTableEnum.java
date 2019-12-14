package ytb.manager.templateexcel.model.tag.impl;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * @Author lxz
 * @Date 2018/11/9 14:23
 * @Description xxx
 */
public class DbTableEnum {

    public static final class DBEnum {

        public static final String YTB_MANAGER = "ytb_manager";

    }

    public static final class TableEnum {

        //乙方工作组
        public static final String WORK_GROUP_TASK = "work_group_task";

        //工作天数一览表
        public static final String PROJECT_WORK_DAY = "project_work_day";

        //工作计划里程碑
        public static final String PROJECT_PLAN = "project_plan";

        //工作计划里程碑合计表
        public static final String PROJECT_PLAN_SUM = "project_plan_sum";

        //乙方采购和加工费用预算一览表
        public static final String PP_BUDGET = "pp_budget";

        //乙方采购和加工费用预算一览表合计表
        public static final String PP_BUDGET_SUM = "pp_budget_sum";

        //费用一览表
        public static final String COST = "cost";

        //费用一览表合计表
        public static final String COST_SUM = "cost_sum";

        //费用一览表毛利率合计表
        public static final String COST_SUM_RATE = "cost_sum_rate";

        //费用一览表发票合计表
        public static final String COST_SUM_INVOICE = "cost_sum_invoice";

        //总任务一览表
        public static final String PROJECT_TASK_TOTAL = "project_task_total";

        //需求说明书-部件名称和功能说明一览表
        public static final String DEMAND_PART_LIST = "demand_part_list";


    }
}
