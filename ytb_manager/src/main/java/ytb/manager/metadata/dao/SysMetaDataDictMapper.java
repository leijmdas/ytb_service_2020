package ytb.manager.metadata.dao;

import ytb.manager.metadata.model.Sys_MetaDataDictModel;
import ytb.manager.metadata.model.Sys_MetaDataFieldModel;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.manager.metadata.dao
 * Author: XZW
 * Date: Created in 2018/8/23 16:16
 */
public interface SysMetaDataDictMapper {

    //获取主表中的所有记录
    List<Sys_MetaDataDictModel> getDictList ();

    //通过元数据类型查询出所有的记录
    List<Sys_MetaDataDictModel> getDictListByType (int metaDataType);

    //通过主表主键查询
    Sys_MetaDataDictModel getDictById (int metaDataId);

    //通过主表主键修改
    void updateDictById (Sys_MetaDataDictModel sysMetaDataDictModel);

    //增加主表字段
    void addDict (Sys_MetaDataDictModel sysMetaDataDictModel);

    //通过主键删除字段
    void deleteDictById (int metaDataId);

    //根据表名称查询
    Sys_MetaDataDictModel getDictByDictInfo (Sys_MetaDataDictModel metaDataDictModel);

    //分页查询
    List<Sys_MetaDataDictModel> getDictByPage (int start, int currentPageCount);

    //得到主表总记录数
    int getTotalCount ();

    //通过元数据类型查询出所有的记录
    List<Sys_MetaDataDictModel> getTreeByTypeAndSubSys (Map<String, Object> params);

}
