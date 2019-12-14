package ytb.account.wallet.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ytb.account.wallet.model.AccountTradeOut;
import ytb.account.wallet.model.AccountTradeOutExample;

public interface AccountTradeOutMapper {
    long countByExample(AccountTradeOutExample example);

    int deleteByExample(AccountTradeOutExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountTradeOut record);

    int insertSelective(AccountTradeOut record);

    List<AccountTradeOut> selectByExample(AccountTradeOutExample example);

    AccountTradeOut selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountTradeOut record, @Param("example") AccountTradeOutExample example);

    int updateByExample(@Param("record") AccountTradeOut record, @Param("example") AccountTradeOutExample example);

    int updateByPrimaryKeySelective(AccountTradeOut record);

    int updateByPrimaryKey(AccountTradeOut record);
}