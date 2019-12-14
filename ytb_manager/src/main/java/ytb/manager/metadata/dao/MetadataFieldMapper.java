package ytb.manager.metadata.dao;


import java.util.List;
import org.apache.ibatis.annotations.Param;
import ytb.manager.metadata.model.MetadataField;
import ytb.manager.metadata.model.MetadataFieldExample;

public interface MetadataFieldMapper {
    long countByExample(MetadataFieldExample example);

    int deleteByExample(MetadataFieldExample example);

    int deleteByPrimaryKey(Integer fieldId);

    int insert(MetadataField record);

    int insertSelective(MetadataField record);

    List<MetadataField> selectByExample(MetadataFieldExample example);

    MetadataField selectByPrimaryKey(Integer fieldId);

    int updateByExampleSelective(@Param("record") MetadataField record, @Param("example") MetadataFieldExample example);

    int updateByExample(@Param("record") MetadataField record, @Param("example") MetadataFieldExample example);

    int updateByPrimaryKeySelective(MetadataField record);

    int updateByPrimaryKey(MetadataField record);
}