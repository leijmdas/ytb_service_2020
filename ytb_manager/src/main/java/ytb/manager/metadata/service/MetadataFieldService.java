package ytb.manager.metadata.service;

import org.apache.ibatis.annotations.Param;
import ytb.manager.metadata.model.*;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.manager.metadata.service
 * Author: XZW
 * Date: Created in 2018/8/23 16:33
 */
public interface MetadataFieldService {



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
