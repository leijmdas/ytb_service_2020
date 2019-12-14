package ytb.manager.metadata.dao;


import java.util.List;
import org.apache.ibatis.annotations.Param;
import ytb.manager.metadata.model.MetadataDict;
import ytb.manager.metadata.model.MetadataDictExample;


public interface MetadataDictMapper {
    long countByExample(MetadataDictExample example);

    int deleteByExample(MetadataDictExample example);

    int deleteByPrimaryKey(Integer metadataId);

    int insert(MetadataDict record);

    int insertSelective(MetadataDict record);

    List<MetadataDict> selectByExample(MetadataDictExample example);

    MetadataDict selectByPrimaryKey(Integer metadataId);

    int updateByExampleSelective(@Param("record") MetadataDict record, @Param("example") MetadataDictExample example);

    int updateByExample(@Param("record") MetadataDict record, @Param("example") MetadataDictExample example);

    int updateByPrimaryKeySelective(MetadataDict record);

    int updateByPrimaryKey(MetadataDict record);
}