package ytb.manager.template.dao;

import ytb.manager.template.model.Dict_Req_Item;

import java.util.List;

public interface ReqItemMapper {

    void insert(Dict_Req_Item reqItem);

    void deleteListByTemplateId(int templateId);

    //根据需求书模板ID查询需求因子列表
    List<Dict_Req_Item> selectListByTemplateId(int templateId);

    void updateReqItem(Dict_Req_Item reqItem);

}
