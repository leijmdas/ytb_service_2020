package ytb.manager.templateexcel.service;

import ytb.manager.template.model.DictRestRefModel;

public interface UrlTableCacheService {
    Short insertUrlReturnId(DictRestRefModel dictRestRefModel);
    DictRestRefModel getDictRestRefById(Short refId);
}
