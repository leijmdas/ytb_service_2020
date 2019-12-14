package ytb.manager.metadata.service;

import ytb.manager.metadata.model.*;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.manager.metadata.service
 * Author: XZW
 * Date: Created in 2018/8/23 16:33
 */
public interface SysMetaDataService {

    //获取主表中的所有记录
   /* List<Sys_MetaDataDictModel> getDictList();

    //通过元数据类型查询出所有的记录
    List<Sys_MetaDataDictModel> getDictListByType(int metaDataType);

    //通过主表主键查询
    Sys_MetaDataDictModel getDictById(int metaDataId);

    //通过主表主键修改
    void updateDictById(Sys_MetaDataDictModel sysMetaDataDictModel);

    //增加主表字段
    void addDict(Sys_MetaDataDictModel sysMetaDataDictModel);*/

    //通过主键删除字段
    void deleteDictById(int metaDataId);

    //通过主表主键查询对应的字段信息
    /*   List<Sys_MetaDataFieldModel> getFieldListByDictId(int metaDataId);*/

    //通过从表主键查询
    /*  Sys_MetaDataFieldModel getFieldById(int fieldId);*/

    //通过从表主键修改
    /*  void updateFieldById(Sys_MetaDataFieldModel sysMetaDataFieldModel);*/

    //增加从表字段
    /*  void addField(Sys_MetaDataFieldModel sysMetaDataFieldModel);*/

    //通过主键删除字段
    void deleteFieldById(int fieldId);

    //通过输入typeid,返回dataid,和名称
    /*   List<Sys_DictDataTypeModel> getTypeAndIdByTypeId(int typeId);*/

    //获取dict_datatype表中的所有数据
    List<Sys_DictDataTypeModel> getDictDataTypeList();


    //向dict_datatype表中增加一条数据
    void addDictDataType(Sys_DictDataTypeModel sysDictDataTypeModel);

    //根据主键修改dict_datatype表中的数据
    void updateDictDataTypeById(Sys_DictDataTypeModel sysDictDataTypeModel);

    //根据主键删除dict_datatype表的数据
    void deleteDictDataTypeById(int dataInnerId);

    /*
        //根据表信息查询
        Sys_MetaDataDictModel getDictByDictInfo(Sys_MetaDataDictModel metaDataDictModel);
    */
    //得到树形数据
    List<Map<String, Object>> getTree(int typeId);

    //根据数据字典表主键查询
    /* Sys_DictDataTypeModel getDictDataTypeById(int dataInnerId);*/

    //分页查询
    //List<Sys_MetaDataDictModel> getDictByPage (int start, int currentPageCount);

    //得到主表总记录数
    int getTotalCount();

    //通过主键id创建数据表
    MsgRequest makeTableByDictId(Integer metadataId, MsgRequest req, RestHandler handler);

    //通过主表主键删除该表对应的所有字段
    void deleteFieldsByDictId(int metaDataId);

    //获取dict_datatype表中的所有数据
    List<SubsysDict> getSubSysDictList(  );


    List<MetadataDict> selectByExample(MetadataDictExample example);

    MetadataField selectByPrimaryKey(Integer fieldId);

    List<Map<String, Object>> selectByTable(SelectSql selectSql);

    List<Map<String, Object>> selectByTableLimit(SelectSql selectSql);

    List<Map<String, Object>> selectByTableLimitOrderBy(SelectSql selectSql);

}
