package ytb.account.wallet.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ytb.account.wallet.model.AccountUserOut;
import ytb.account.wallet.model.AccountUserOutExample;
@Mapper
public interface AccountUserOutMapper {
    long countByExample(AccountUserOutExample example);

    int deleteByExample(AccountUserOutExample example);

    int deleteByPrimaryKey(Integer outId);

    int insert(AccountUserOut record);

    int insertSelective(AccountUserOut record);

    List<AccountUserOut> selectByExample(AccountUserOutExample example);

    AccountUserOut selectByPrimaryKey(Integer outId);

    int updateByExampleSelective(@Param("record") AccountUserOut record, @Param("example") AccountUserOutExample example);

    int updateByExample(@Param("record") AccountUserOut record, @Param("example") AccountUserOutExample example);

    int updateByPrimaryKeySelective(AccountUserOut record);

    int updateByPrimaryKey(AccountUserOut record);
}