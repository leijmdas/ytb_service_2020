package ytb.manager.pfUser.dao;

import ytb.manager.pfUser.model.Dict_CompanyType;
import ytb.manager.pfUser.model.Dict_Tag;

import java.util.List;

public interface DictCompanyTypeMapper {
    //获取菜单列表
    List<Dict_CompanyType> getDictCompanyTypeList();

    List<Dict_CompanyType> getDictCompanyTypeListByID(Dict_CompanyType dcType);

    void insertDictCompanyType(Dict_CompanyType dcType);

    void updateDictCompanyType(Dict_CompanyType dcType);

    void deleteDictCompanyType(int dcType);

    List<Dict_CompanyType> getDictCompanyTypeListByParentID(int parentId);
}
