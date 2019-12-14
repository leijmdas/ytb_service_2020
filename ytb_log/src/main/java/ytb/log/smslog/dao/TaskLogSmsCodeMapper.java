package ytb.log.smslog.dao;






import org.apache.ibatis.annotations.Param;
import ytb.log.smslog.model.Tasklog_Sms_CodeModel;

import java.util.List;

/**
 * Package: ytb.common.basic.activititemplate.dao
 * Author: lzz
 * Date: Created in 2018/8/23 16:30
 */
public interface TaskLogSmsCodeMapper {

    //获取短信验证码列表
    List getTaskLogSmsCodeList();

    //添加短信验证码
    void addTaskLogSmsCode(Tasklog_Sms_CodeModel sms_codeModel);

    //修改短信验证码
    void modifyTaskLogSmsCode(Tasklog_Sms_CodeModel sms_codeModel);

    //删除短信验证码
    void delTaskLogSmsCode();
    //发送查询
    Tasklog_Sms_CodeModel getTemplateLogSmsCodeByPhone(String phone);

    Tasklog_Sms_CodeModel selectByPrimaryKey(String phone);


    //检验查询
    Tasklog_Sms_CodeModel getTemplateLogSmsCode(@Param("phone")String phone);

    int deleteSmsCode(@Param("phone")String phone);

    int deleteByPrimaryKey(String phone);



}


