package ytb.manager.metadata.dao;

import org.apache.ibatis.annotations.Param;
import ytb.manager.metadata.model.Sub_SysDictModel;
import ytb.manager.metadata.model.SubsysDict;
import ytb.manager.metadata.model.SubsysDictExample;

import java.util.List;

/**
 * Created by ZYB on 2018/9/13 14:41
 */
public interface SubsysDictMapper {

    //获取dict_datatype表中的所有数据
    List<SubsysDict> getSubSysDictList();

    Sub_SysDictModel getSubSysDictById(int subsysId);


    long countByExample(SubsysDictExample example);

    int deleteByExample(SubsysDictExample example);

    int deleteByPrimaryKey(Integer subsysId);

    int insert(SubsysDict record);

    int insertSelective(SubsysDict record);

    List<SubsysDict> selectByExample(SubsysDictExample example);

    SubsysDict selectByPrimaryKey(Integer subsysId);

    int updateByExampleSelective(@Param("record") SubsysDict record, @Param("example") SubsysDictExample example);

    int updateByExample(@Param("record") SubsysDict record, @Param("example") SubsysDictExample example);

    int updateByPrimaryKeySelective(SubsysDict record);

    int updateByPrimaryKey(SubsysDict record);


}
