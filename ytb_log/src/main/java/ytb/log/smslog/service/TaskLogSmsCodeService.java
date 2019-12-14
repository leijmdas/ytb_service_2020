package ytb.log.smslog.service;


import org.apache.ibatis.annotations.Param;
import ytb.log.smslog.model.Tasklog_SmsModel;
import ytb.log.smslog.model.Tasklog_Sms_CodeModel;

/**
 * Package: ytb.activiti.service
 * Author: ZCS
 * Date: Created in 2018/8/23 16:35
 */
public interface TaskLogSmsCodeService {

   //添加短信通知
   void addTaskLogSmsCode(Tasklog_Sms_CodeModel sms_codeModel);

   int sendSms(String phone, String templateCode,String template_param);

   int sendSmsCode(String phone, String templateCode);

   int checkCode(String phone, String code);

   int deleteSmsCode (String phone);

   Tasklog_Sms_CodeModel getTemplateLogSmsCodeByPhone(String phone);


   Tasklog_Sms_CodeModel getTemplateLogSmsCode(String phone);

   Tasklog_Sms_CodeModel selectByPrimaryKey(String phone);


   int  deleteByPrimaryKey(String phone);


}
