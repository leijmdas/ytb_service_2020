package ytb.manager.charges.service;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ytb.manager.charges.model.DictArea;
import ytb.manager.charges.model.DictAreaExample;

import java.util.List;


public interface DictAreaService {
    long countByExample(DictAreaExample example);

    int deleteByExample(DictAreaExample example);

    int deleteByPrimaryKey(Integer areaId);

    int insert(DictArea record);

    int insertSelective(DictArea record);

    List<DictArea> selectByExample(DictAreaExample example);

    DictArea selectByPrimaryKey(Integer areaId);

    int updateByExampleSelective(@Param("record") DictArea record, @Param("example") DictAreaExample example);

    int updateByExample(@Param("record") DictArea record, @Param("example") DictAreaExample example);

    int updateByPrimaryKeySelective(DictArea record);

    int updateByPrimaryKey(DictArea record);
}