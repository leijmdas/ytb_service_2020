package ytb.log.smslog.rest.impl;


import ytb.log.smslog.service.impl.TaskLogSmsCodeServiceImpl;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;


public class SmsLog {


    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";

    private static final String REGISTMODELCODE="SMS_135210196";//用户注册验证码模板Code
    private static final String NOTIFY="SMS_143860203";//用户注册验证码模板Code
    public MsgResponse process(MsgRequest req, RestHandler handler) {

        TaskLogSmsCodeServiceImpl taskLogSmsCodeService = new TaskLogSmsCodeServiceImpl();

        if(req.cmd.equals("sendSmsCode")){

            String phone = req.msgBody.getString("phone");
            String smsType = req.msgBody.getString("smsType");
            int statue =taskLogSmsCodeService.sendSmsCode(phone,smsType);
            if(statue==1){

                retcode =1;
                retmsg ="验证码发送失败";
            }
        }
        else if(req.cmd.equals("checkSmsCode")){
            String smsCode =  req.msgBody.getString("smsCode");
            String phone =  req.msgBody.getString("phone");

            int statue = taskLogSmsCodeService.checkCode(phone,smsCode);
            if(statue==1){
                retcode =10006;
                retmsg="验证码不存在";
            }else if(statue ==2){
                retcode =10007;
                retmsg="验证码输入错误";
            }else if(statue ==3){
                retcode =10008;
                retmsg="验证码已过期";
            }

        }else if(req.cmd.equals("sendSms")){

            String phone = req.msgBody.getString("phone");
            int user_id = Integer.parseInt( req.msgBody.getString("user_id"));
            String template_param = req.msgBody.getString("template_param");
            int statue =taskLogSmsCodeService.sendSms(phone,NOTIFY,template_param);

        } else{
            retcode=1;
            retmsg ="cmd输入错误";

        }
        return handler.buildMsg(retcode, retmsg, msgBody);
    }


}
