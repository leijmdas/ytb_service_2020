package ytb.manager.template.service;

import ytb.manager.template.model.Dict_Req_Item;

import java.util.List;

public interface ReqItemService {

    void removeListByTemplateId(int templateId);

    void add(Dict_Req_Item reqItem);

    List<Dict_Req_Item> queryListByTemplateId(int templateId);

    void modifyReqItem(Dict_Req_Item reqItem);
}
