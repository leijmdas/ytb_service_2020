package ytb.manager.metadata.dao;

import ytb.manager.metadata.model.Sys_DictDataTypeModel;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.manager.metadata.dao
 * Author: XZW
 */
public interface SysDictDataTypeMapper {

    //通过输入typeid,返回dataid,和名称
    List<Sys_DictDataTypeModel> getTypeAndIdByTypeId (int typeId);

    //获取dict_datatype表中的所有数据
    List<Sys_DictDataTypeModel> getDictDataTypeList ();

    //向dict_datatype表中增加一条数据
    void addDictDataType (Sys_DictDataTypeModel sysDictDataTypeModel);

    //根据主键修改dict_datatype表中的数据
    void updateDictDataTypeById (Sys_DictDataTypeModel sysDictDataTypeModel);

    //根据主键删除dict_datatype表的数据
    void deleteDictDataTypeById (int dataInnerId);

    //得到树形数据
    List<Map<String, Object>> getTree (int typeId);

    //根据数据字典表主键查询
    Sys_DictDataTypeModel getDictDataTypeById (int dataInnerId);

}
