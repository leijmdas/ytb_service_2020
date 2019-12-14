package ytb.manager.charges.service;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ytb.manager.charges.model.DictArea;
import ytb.manager.charges.model.DictAreaExample;
import ytb.manager.charges.model.DictAreaSalary;
import ytb.manager.charges.model.DictAreaSalaryExample;

import java.util.List;


public interface DictAreaSalaryService {
    long countByExample(DictAreaSalaryExample example);

    int deleteByExample(DictAreaSalaryExample example);

    int deleteByPrimaryKey(Integer salaryId);

    int insert(DictAreaSalary record);

    int insertSelective(DictAreaSalary record);

    List<DictAreaSalary> selectByExample(DictAreaSalaryExample example);

    DictAreaSalary selectByPrimaryKey(Integer salaryId);

    int updateByExampleSelective(@Param("record") DictAreaSalary record, @Param("example") DictAreaSalaryExample example);

    int updateByExample(@Param("record") DictAreaSalary record, @Param("example") DictAreaSalaryExample example);

    int updateByPrimaryKeySelective(DictAreaSalary record);

    int updateByPrimaryKey(DictAreaSalary record);
}