package ytb.manager.metadata.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ytb.manager.metadata.model.DictDatatype;
import ytb.manager.metadata.model.DictDatatypeExample;

public interface DictDatatypeMapper {
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