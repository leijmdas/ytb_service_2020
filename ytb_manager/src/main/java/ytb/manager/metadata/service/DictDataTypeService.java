package ytb.manager.metadata.service;

import org.apache.ibatis.annotations.Param;
import ytb.manager.metadata.model.DictDatatype;
import ytb.manager.metadata.model.DictDatatypeExample;
import ytb.manager.metadata.model.MetadataDict;
import ytb.manager.metadata.model.MetadataDictExample;

import java.util.List;

/**
 * Package: ytb.manager.metadata.service
 * Author: XZW
 * Date: Created in 2018/8/23 16:33
 */
public interface DictDataTypeService {


    long countByExample(DictDatatypeExample example);

    int deleteByExample(DictDatatypeExample example);

    int deleteByPrimaryKey(Integer datainnerid);

    int insert(DictDatatype record);

    int insertSelective(DictDatatype record);

    List<DictDatatype> selectByExample(DictDatatypeExample example);

    DictDatatype selectByPrimaryKey(Integer datainnerid);

    int updateByExampleSelective(@Param("record") DictDatatype record, @Param("example") DictDatatypeExample example);

    int updateByExample(@Param("record") DictDatatype record, @Param("example") DictDatatypeExample example);

    int updateByPrimaryKeySelective(DictDatatype record);

    int updateByPrimaryKey(DictDatatype record);


    List<DictDatatype> selectDatatype();


}
