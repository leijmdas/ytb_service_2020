package ytb.manager.template.dao;

import ytb.manager.template.model.Dict_Prj_Config;;

import java.util.List;

public interface ProjConfigMapper {
    List<Dict_Prj_Config> selectProjConfig(int projectType);

    void addProjConfig(Dict_Prj_Config c);

    void modifyProjConfig(Dict_Prj_Config c);

    void delProjConfig(int configId);
}
