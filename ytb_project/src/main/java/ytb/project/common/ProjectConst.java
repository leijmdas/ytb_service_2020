package ytb.project.common;

import ytb.manager.template.model.TemplateModel;

public class ProjectConst extends TemplateModel {
    public final static String TEMPORARY_PASSWORD = "e10adc3949ba59abbe56e057f20f883e";

    //项目文件夹类型folder_type

    // 文件夹非法
    public static Byte FOLDER_TYPE_INVALID  = 0;
    //项目根文件夹

    public static Byte FOLDER_TYPE_PROJECT  = 1;
    //洽谈根文件夹
    public static Byte FOLDER_TYPE_TALK = 2;
    //阶段根文件夹
    public static Byte FOLDER_TYPE_PHASE  = 3;
    //项目岗位文件夹
    public static Byte FOLDER_TYPE_DUTY_PROJECT = 4;
    //组员岗位文件夹
    public static Byte FOLDER_TYPE_DUTY_MEMBER = 5;
    //岗位任务文件夹
    public static Byte FOLDER_TYPE_TASK = 6;
    //自定义任务文件夹
    public static Byte FOLDER_TYPE_TASK_DEFINE = 7;
    //终止文件夹
    public static Byte FOLDER_TYPE_STOP = 8;

    //协助文件夹
    public static Byte FOLDER_TYPE_ASSIST = 100;


    // 草稿状态
    public  static int TalkDraft = 0;
    // 邀请申请中
    public static int RequestIn = 100;
    // 洽谈阶段
    public static int TalkPhase = 200;
    // 洽谈阶段子文件夹挂靠阶段
    public static int TalkPhase_SUBFOLDER = 201;
        //洽谈中
        public static int TalkPhase_STATUS = 0;
        //洽谈审核中
        public static int TalkPhase_STATUS_PA_AUDIT = 200;
        //支付中,启动支付，阶段支付
        public static int TalkPhase_STATUS_PAY = 300;
        //项目结束支付中
        public static int TalkPhase_STATUS_FINISH_PAY = 400;


    //开始阶段
    public static int Phase_START = 600;
    //第0阶段
    public static int Phase0 = Phase_START;
    //第一阶段
    public static int Phase1 = 601;
    //第二阶段
    public static int Phase2 = 602;
    //第三阶段
    public static int Phase3 = 603;
    //第四阶段
    public static int Phase4 = 604;
    //第五阶段
    public static int Phase5 = 605;
    //第六阶段
    public static int Phase6 = 606;
    //第7阶段
    public static int Phase7 = 607;
    //第8阶段
    public static int Phase8 = 608;
    //第9阶段
    public static int Phase9 = 609;
    //结束阶段
    public static int Phase_END = 609;


    //终止
    public static int Phase_Stop = 800;

    //项目已完成
    //public static int Phase_Finish = 999;


    //---- 文件夹状态 (project_folder：folder_status)---start
        //文件夹状态非法
        public static int FOLDER_STATUS_INVALID = 0;
        //文件夹编写状态
        public static int FOLDER_STATUS_WRITE_PM = 1;
        //文件夹组员提交组长审核 提交
        public static int FOLDER_STATUS_SUBMIT_PM = 2;
        //文件夹审核 组长审核通过
        public static int FOLDER_STATUS_PASS_PM = 3;
        //文件夹审核 组长审核不过
        public static int FOLDER_STATUS_NOTPASS_PM = 4;

        //5 组长提交甲方审核
        public static int FOLDER_STATUS_SUBMIT_PB = 5;
        //6 甲方审核通过
        public static int FOLDER_STATUS_PASS_PB = 6;
        //7 甲方审核不过
        public static int FOLDER_STATUS_NOTPASS_PB = 7;
    //---- 文件夹状态 (project_folder：folder_status)---end



    //------------- -activeStatus
    //去查阅
    public static int ACTIVE_STATUS_GoRead = 1;
    //去编制
    public static int ACTIVE_STATUS_ToWrite = 2;
    //去修改
    public static int ACTIVE_STATUS_ToModify = 3;
    //去审核绿色
    public  static int ACTIVE_STATUS_ToAudit = 4;
    //上传文件
    public  static int ACTIVE_STATUS_UploadFile = 5;
    //重新上传
    public  static int ACTIVE_STATUS_ReUpload = 6;
    //去变更
    public  static int ACTIVE_STATUS_CHANGE = 7;



    //--------------------- --文件status
    //无权限
    public static  int TASK_STATUS_NO_RIGHT = -1;
    //发布中
    public static  int TASK_STATUS_Release = 0;
    //未开始
    public  static int TASK_STATUS_NotStart = 1;
    //已查阅
    public  static int TASK_STATUS_Viewed = 2;
    //编制中
    public  static int TASK_STATUS_Writing = 3;
    //未递交
    public  static int TASK_STATUS_NotSubmitted = 4;
    //已递交
    public  static int TASK_STATUS_Submitted = 5;
    //通过
    public  static int TASK_STATUS_Passing = 6;
    //未通过
    public  static int TASK_STATUS_NotPassing = 7;
    //15
    public  static int TASK_STATUS_Passing_PA = 15;
    //16
    public  static int TASK_STATUS_NotPassing_PA = 16;

    //project_doc_list sttaus
    //模板状态 project_doc_list
    //100项目模板作者编写状态
    public final static int P_TEMPLATE_STAT_WRITE_PM = 100;
    //200组员提交审核
    public final static int P_TEMPLATE_STAT_SUBMIT_PM  = 200;
    //300组长审核通过
    public final static int P_TEMPLATE_STAT_PASS_PM  = 300;
    //400组长审核不过
    public final static int P_TEMPLATE_STAT_NOPASS_PM  = 400;

    //405 组长提交甲方审核
    public final static int P_TEMPLATE_STAT_SUBMIT_PB = 405;
    //406 甲方审核通过
    public final static int P_TEMPLATE_STAT_PASS_PB = 406;
    //407 甲方审核不过
    public final static int P_TEMPLATE_STAT_NOPASS_PB = 407;

    //501 递交协助（组内协助）
    public final static int P_TEMPLATE_STAT_SUBMIT_ASSIST = 501;
    //502 递交协助（组外协助）
    public final static int P_TEMPLATE_STAT_SUBMIT_ASSIST_OUT = 502;
    //600确认协助
    public final static int P_TEMPLATE_STAT_ASSIST_PASS= 600;
    //700协助打回
    public final static int P_TEMPLATE_STAT_ASSIST_NOPASS= 700;
    //800 协助完成返还责任人
    public final static int P_TEMPLATE_STAT_ASSIST_RETURN = 800;

    //------------ --项目过程记录类型



    //加工检验报告
    public final static int   Processing_inspection_report = 305;
    //加工检查清单
    public  final static int   Processing_checklist = 306;
    //采购检验报告
    public  final static int   Procurement_inspection_report = 307;

    //协助
    public final static int ASSIST_TYPE_PA = 1;
    public final static int ASSIST_TYPE_PB = 2;
    ///区别协助、洽谈(1协助)
    public final static int ASSIST_FLAG = 1;

    public final static int ASSIST_PHASESTATUS_START = 60;
    public final static int ASSIST_PHASESTATUS_SUBMIT = 60;

}
