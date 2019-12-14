package ytb.manager.pfUser.dao;

import ytb.manager.pfUser.model.Dict_Tag;
import ytb.manager.sysuser.model.Sys_MenuModel;

import java.util.List;

public interface DictTagMapper {
    //获取菜单列表
    List<Dict_Tag> getDictTagList(int tagType);

    List<Dict_Tag> getDictTagListByID(Dict_Tag dtag);

    void insertDictTag(Dict_Tag dtag);

    void updateDictTag(Dict_Tag dtag);

    void deleteDictTag(int tagId);

}
