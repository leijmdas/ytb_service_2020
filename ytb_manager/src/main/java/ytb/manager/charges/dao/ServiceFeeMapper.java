package ytb.manager.charges.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ytb.manager.charges.model.ServiceFee;
import ytb.manager.charges.model.ServiceFeeExample;

public interface ServiceFeeMapper {
    long countByExample(ServiceFeeExample example);

    int deleteByExample(ServiceFeeExample example);

    int deleteByPrimaryKey(Integer feeId);

    int insert(ServiceFee record);

    int insertSelective(ServiceFee record);

    List<ServiceFee> selectByExample(ServiceFeeExample example);

    ServiceFee selectByPrimaryKey(Integer feeId);

    int updateByExampleSelective(@Param("record") ServiceFee record, @Param("example") ServiceFeeExample example);

    int updateByExample(@Param("record") ServiceFee record, @Param("example") ServiceFeeExample example);

    int updateByPrimaryKeySelective(ServiceFee record);

    int updateByPrimaryKey(ServiceFee record);
}