package ytb.account.wallet.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.model.AccountTradeProjectExample;

public interface AccountTradeProjectMapper {
    long countByExample(AccountTradeProjectExample example);

    int deleteByExample(AccountTradeProjectExample example);

    int deleteByPrimaryKey(Integer tradeId);

    int insert(AccountTradeProject record);

    int insertSelective(AccountTradeProject record);

    List<AccountTradeProject> selectByExample(AccountTradeProjectExample example);

    AccountTradeProject selectByPrimaryKey(Integer tradeId);

    int updateByExampleSelective(@Param("record") AccountTradeProject record, @Param("example") AccountTradeProjectExample example);

    int updateByExample(@Param("record") AccountTradeProject record, @Param("example") AccountTradeProjectExample example);

    int updateByPrimaryKeySelective(AccountTradeProject record);

    int updateByPrimaryKey(AccountTradeProject record);
}