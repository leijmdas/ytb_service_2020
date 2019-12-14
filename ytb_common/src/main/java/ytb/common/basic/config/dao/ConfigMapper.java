package ytb.common.basic.config.dao;


import ytb.common.basic.config.model.Dict_ConfigModel;

import java.util.List;

public interface ConfigMapper {

   List<Dict_ConfigModel> getConfig();
   List<Dict_ConfigModel> selectList();

}
