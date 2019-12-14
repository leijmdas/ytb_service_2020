package ytb.log.notify.dao;



import ytb.log.notify.model.Template_notifyModel;

import java.util.List;
import java.util.Map;


/**
 * Package: ytb.common.basic.activititemplate.dao
 * Author: lzz
 * Date: Created in 2018/8/23 16:30
 */
public interface TemplateNotifyMapper {

     //获取所有通知模板列表
    List<Template_notifyModel> getTaskTemplateList(Map map);

    //添加通知模板
    void addTaskTemplate(Template_notifyModel tnm);

    //修改通知模板
    void modifyTaskTemplate(Template_notifyModel tnm);

    //删除通知模板
    void delTaskTemplate(int templateId);

    //获取通知
    List<Template_notifyModel> getTaskTemplate(int templateId);

    Template_notifyModel getTemplateNotify(String templateName);


}


