package ytb.log.notify.dao;




import org.apache.ibatis.annotations.Param;
import ytb.log.notify.model.Template_policyModel;

import java.util.List;


/**
 * Package: ytb.common.basic.activititemplate.dao
 * Author: lzz
 * Date: Created in 2018/8/23 16:30
 */
public interface TemplatePolicyMapper {

     //获取所有通知模板列表
    List getTemplatePolicyList();

    //添加通知模板
    void addTemplatePolicy(Template_policyModel tpm);

    //修改通知模板
    void modifyTemplatePolicy(Template_policyModel tpm);

    //删除通知模板
    void delTemplatePolicy(int template_id);

    //获取通知
    List getTemplatePolicy(int template_id);

    //获取任务模板
    Template_policyModel getTemplatePolicyByType(@Param("templateType")int templateType, @Param("objectType")int objectType);


}


