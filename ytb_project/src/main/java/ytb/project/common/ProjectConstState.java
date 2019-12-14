package ytb.project.common;

import ytb.project.common.ProjectConst;

public class ProjectConstState extends ProjectConst {
    //大变更与终止与其它完成状态
    public static int CHNAGE_TYPE_NONE =  0;
    //洽谈终止
    public static int CHNAGE_TYPE_TALK_TERM = 400;
    //普通变更
    public static int CHNAGE_TYPE_SMALL = 701;
    //重大变更
    public static int CHNAGE_TYPE_BIG = 702;
    //项目终止
    public static int CHNAGE_TYPE_STOP = 800;
    //项目完成
    public static int CHNAGE_TYPE_FINISH = 900;


    //0publish
    public final static int TEMPLATE_STAGE_PUBLISH = 0;
    //1talk
    public final static int TEMPLATE_STAGE_TALK = 1;
    //2design
    public final static int TEMPLATE_STAGE_DESIGN = 2;
    //3compositedesign
    public final static int TEMPLATE_STAGE_COMPOSITE_DESIGN = 3;
    //4deliver交付
    public final static int TEMPLATE_STAGE_DELIVER = 4;

    //--------- -项目变更终止表project_change:changeStatus的状态
    //10 提出 changeType=801甲方 802乙方是提出方
    public static int STOP_CHANGESTATUS_PA = 10;
    //20 对方同意
    public static int STOP_CHANGESTATUS_PB_OK = 20;
    //30 对方不同意
    public static int STOP_CHANGESTATUS_PB_NO_OK = 30;
    //40 三天后正式生效，支付中 20,30->40
    public static int STOP_CHANGESTATUS_FINISH = 40;
    //50 支付完，项目正式终止，修改洽谈表changSatus=800
    public static int STOP_CHANGESTATUS_PAYED = 50;
    //60 <40可取消终止
    public static int STOP_CHANGESTATUS_CANCEL = 60;

    //--------- -项目变更表变更changeStatus的状态
    // -1 隐藏  0 默认  10乙方编制 20审核 30项目结项  40支付  50确定变更协作  60取消变更
    //隐藏草稿
    public static int PRO_CHANGESTATUS_HIDE = -1;
    //0 默认
    public static int PRO_CHANGESTATUS_NORMAL = 0;
    //乙方编制中
    public static int PRO_CHANGESTATUS_PB_MAKE = 10;
    //甲方审核中
    public static int PRO_CHANGESTATUS_PA_AUDIT = 20;
    //项目结项中
    public static int PRO_CHANGESTATUS_CLOSE = 30;
    //支付中
    public static int PRO_CHANGESTATUS_PAYMENT = 40;

    //确定变更
    public static int PRO_CHANGESTATUS_CONFIRM_CHANGE = 50;
    //取消变更
    public static int PRO_CHANGESTATUS_CANCEL_CHANGE = 60;


    //所有项目
    public final static int PROJECT_ALL = 0;

    //1:甲方邀请
    public final static int PROJECT_INVITE = 1;
    //2:乙方申请
    public final static int PROJECT_APPLY = 2;

    //项目状态
    //-1: 隐藏
    public final static int PUBLISH_HIDE = -1;

    //1：未发布
    public final static int PUBLISH_DRAFT = 1;

    //发布审核中
    public final static int PUBLISH_AUDIT = 2;

    //审核未通过
    public final static int PUBLISH_NOPASS = 3;

    //发布中
    public final static int PUBLISH_PASS = 4;

    //发布停止
    public final static int PUBLISH_STOP = 5;
    //重新发布
    public final static int PUBLISH_RE_PUBLISH = 6;

    //邀请状态：0 未邀请
    public final static int INVITE_STATUS_NO = 0;
    // 1邀请中
    public final static int INVITE_STATUS_YES = 1;
    // 2已邀请
    public final static int INVITE_STATUS_PASS = 2;
    // 3已拒绝
    public final static int INVITE_STATUS_REJECT = 3;


    //项目发布审核通知
    public  final  static String PROJECT_CheckNotify = "项目发布审核通知";
    //项目申请通知
    public final static String PROJECT_InviteNotify = "项目申请通知";
    //洽谈终止通知
    public static String PROJECT_TalkEndNotify = "洽谈终止通知";
    //甲方阶段预付款通知
    public static String PROJECT_PREPAY_PA_Notify ="甲方阶段预付款通知";
    //乙方阶段预收款通知
    public static String PROJECT_PREPAY_PB_Notify ="乙方阶段预收款通知";
    //协助预收款通知
    public static String PROJECT_PREPAY_ASSIST_Notify = "甲方预付款通知";//"协助预收款通知";

    //洽谈感谢金通知
    public static String PROJECT_Talk_Notice = "洽谈感谢金通知";
    //协助通知
    public static String PROJECT_Assist_Notice = "协助通知";
    //违约金通知
    public static String PROJECT_Penalty_Notice = "违约金通知";

}
