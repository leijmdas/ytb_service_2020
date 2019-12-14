package ytb.log.smslog.service.impl;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import ytb.log.notify.dao.TemplateNotifyMapper;
import ytb.log.notify.model.Template_notifyModel;
import ytb.log.smslog.dao.TaskLogSmsCodeMapper;
import ytb.log.smslog.dao.TaskLogSmsMapper;
import ytb.log.smslog.model.Tasklog_SmsModel;
import ytb.log.smslog.model.Tasklog_Sms_CodeModel;
import ytb.log.smslog.service.TaskLogSmsCodeService;

import ytb.log.utils.Aliyun;
import ytb.log.utils.MyBatisUtils;
import ytb.log.utils.RndUtils;

import java.util.Date;
import java.util.List;

@Service
public class TaskLogSmsCodeServiceImpl implements TaskLogSmsCodeService {


    public  void addTaskLogSmsCode(Tasklog_Sms_CodeModel sms_codeModel) {

        try (SqlSession session = MyBatisUtils.getSession()) {
            TaskLogSmsCodeMapper sessionMapper = session.getMapper(TaskLogSmsCodeMapper.class);
            sessionMapper.addTaskLogSmsCode(sms_codeModel);
        }
    }

    //发送短信
    @Override
    public int sendSms(String phone, String templateCode, String template_param) {

        int msg = 0;
        Aliyun aliyun = new Aliyun();
        if (aliyun.sendSms(phone, template_param, templateCode)) {

            try (SqlSession session = MyBatisUtils.getSession()) {
                TaskLogSmsMapper taskLogSmsMapper = session.getMapper(TaskLogSmsMapper.class);
                Tasklog_SmsModel tasklog_smsModel = new Tasklog_SmsModel();
                tasklog_smsModel.setMobile(phone);
                tasklog_smsModel.setRetCode(0);
                tasklog_smsModel.setSendTime(new Date());
                taskLogSmsMapper.addTaskLogSms(tasklog_smsModel);
                session.commit();
            }
        } else {
            msg = 1;
        }
        return msg;
    }

    @Override
    public int checkCode(String phone, String smsCode) {
        int statue = 0;
        try (SqlSession session = MyBatisUtils.getSession()) {

            TaskLogSmsCodeMapper sessionMapper = session.getMapper(TaskLogSmsCodeMapper.class);
            Tasklog_Sms_CodeModel sms_codeModel = sessionMapper.getTemplateLogSmsCodeByPhone(phone);
            if (sms_codeModel == null) {
                statue = 1;
            } else {
                if (!sms_codeModel.getSmsCode().equals(smsCode)) {
                    statue = 2;
                } else if (sms_codeModel.getEndTime().compareTo(new Date()) < 0) {
                    statue = 3;
                }
            }
        }
        return statue;
    }

    //发送验证码
    @Override
    public int sendSmsCode(String phone, String smsType) {
        int msg =0;
        Aliyun aliyun =  new Aliyun();
        String code = RndUtils.getValidateCode();
        Template_notifyModel template_notifyModel = getTemplateNotifyByName(smsType);
        if(aliyun.sendSmsCode(phone,code,template_notifyModel.getAliSmsTemplate())){

            Tasklog_Sms_CodeModel sms_codeModel = new Tasklog_Sms_CodeModel();

            try (SqlSession session = MyBatisUtils.getSession()) {
                TaskLogSmsCodeMapper tlscMapper = session.getMapper(TaskLogSmsCodeMapper.class);
                TaskLogSmsMapper taskLogSmsMapper = session.getMapper(TaskLogSmsMapper.class);
                Tasklog_Sms_CodeModel codeModel = tlscMapper.getTemplateLogSmsCodeByPhone(phone);
                //添加短信记录
                Tasklog_SmsModel tasklog_smsModel =new Tasklog_SmsModel();
                //tasklog_smsModel.setUser_id(userId);
                tasklog_smsModel.setMobile(phone);
                tasklog_smsModel.setRetCode(0);
                tasklog_smsModel.setSendTime(new Date());
                taskLogSmsMapper.addTaskLogSms(tasklog_smsModel);

                List<Tasklog_SmsModel> list = taskLogSmsMapper.getTaskLogSmsList(phone);
                if(codeModel ==null){//判断短信验证码表中用户是否存在
                    sms_codeModel.setSmsCode(code);
                    sms_codeModel.setSendTime(new Date());
                    sms_codeModel.setEndTime(getEndTiem());
                    //sms_codeModel.setUser_id(userId);
                    sms_codeModel.setPhone(phone);
                    sms_codeModel.setSmsId(list.get(0).getSmsId());
                    tlscMapper.addTaskLogSmsCode(sms_codeModel);

                }else{
                    sms_codeModel.setSmsId(list.get(0).getSmsId());
                    sms_codeModel.setSmsCode(code);
                    sms_codeModel.setSendTime(new Date());
                    sms_codeModel.setEndTime(getEndTiem());
                    //sms_codeModel.setUser_id(userId);
                    sms_codeModel.setPhone(phone);
                    tlscMapper.modifyTaskLogSmsCode(sms_codeModel);
                }

                session.commit();
            }
        }
        else{
             try (SqlSession session = MyBatisUtils.getSession()) {
                TaskLogSmsMapper taskLogSmsMapper = session.getMapper(TaskLogSmsMapper.class);
                Tasklog_SmsModel tasklog_smsModel = new Tasklog_SmsModel();
                tasklog_smsModel.setMobile(phone);
                tasklog_smsModel.setRetCode(1);
                tasklog_smsModel.setSendTime(new Date());
                taskLogSmsMapper.addTaskLogSms(tasklog_smsModel);
                session.commit();
                msg = 1;
            }
        }
        return msg;
    }


    @Override
    public Tasklog_Sms_CodeModel getTemplateLogSmsCodeByPhone(String phone) {


        try (SqlSession session = MyBatisUtils.getSession()) {
            TaskLogSmsCodeMapper tnMapper = session.getMapper(TaskLogSmsCodeMapper.class);
            return tnMapper.getTemplateLogSmsCodeByPhone(phone);
        }


    }
    @Override
    public Tasklog_Sms_CodeModel getTemplateLogSmsCode(String phone) {

        try (SqlSession session = MyBatisUtils.getSession()) {

            TaskLogSmsCodeMapper tnMapper = session.getMapper(TaskLogSmsCodeMapper.class);
            return tnMapper.getTemplateLogSmsCode(phone);
        }
    }


    public Template_notifyModel getTemplateNotifyByName(String templateName) {
        try (SqlSession session = MyBatisUtils.getSession()) {

            TemplateNotifyMapper tnMapper = session.getMapper(TemplateNotifyMapper.class);
            return tnMapper.getTemplateNotify(templateName);

        }
    }

    @Override
    public Tasklog_Sms_CodeModel selectByPrimaryKey(String phone) {

        try (SqlSession session = MyBatisUtils.getSession()) {
            TaskLogSmsCodeMapper tnMapper = session.getMapper(TaskLogSmsCodeMapper.class);
            return tnMapper.selectByPrimaryKey(phone);
        }
    }


    @Override
    public int deleteSmsCode(String phone) {
        try ( SqlSession session= MyBatisUtils.getSession()){
            TaskLogSmsCodeMapper tnMapper = session.getMapper(TaskLogSmsCodeMapper.class);
            return tnMapper.deleteSmsCode(phone);
        }

    }


    @Override
    public int deleteByPrimaryKey(String phone) {

        try (SqlSession session = MyBatisUtils.getSession()) {

            TaskLogSmsCodeMapper tnMapper = session.getMapper(TaskLogSmsCodeMapper.class);
            return tnMapper.deleteByPrimaryKey(phone);
        }

    }


    private Date getEndTiem(){
        long endtime=System.currentTimeMillis()+2*60*1000;//设置过期时间为2分钟后
        Date date=new Date();
        date.setTime(endtime);
        return date;
    }
}
