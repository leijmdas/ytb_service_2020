package ytb.manager.metadata.dao;

import ytb.manager.metadata.model.Sys_MetaDataFieldModel;
import java.util.List;

/**
 * Package: ytb.manager.metadata.dao
 * Author: XZW
 * Date: Created in 2018/8/23 16:29
 */
public interface SysMetaDataFieldMapper {

    //通过主表主键查询对应的字段信息
    List<Sys_MetaDataFieldModel> getFieldListByDictId (int metaDataId);

    //通过从表主键查询
    Sys_MetaDataFieldModel getFieldById (int fieldId);

    //通过从表主键修改
    void updateFieldById (Sys_MetaDataFieldModel sysMetaDataFieldModel);

    //增加从表字段
    void addField (Sys_MetaDataFieldModel sysMetaDataFieldModel);

    //通过主键删除字段
    void deleteFieldById (int fieldId);

    //通过主表主键删除该表对应的所有字段
    void deleteFieldsByDictId (int metaDataId);
}
