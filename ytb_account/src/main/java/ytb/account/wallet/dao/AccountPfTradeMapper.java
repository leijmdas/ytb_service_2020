package ytb.account.wallet.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ytb.account.wallet.model.AccountPfTrade;
import ytb.account.wallet.model.AccountPfTradeExample;

public interface AccountPfTradeMapper {
    long countByExample(AccountPfTradeExample example);

    int deleteByExample(AccountPfTradeExample example);

    int deleteByPrimaryKey(Integer tradeId);

    int insert(AccountPfTrade record);

    int insertSelective(AccountPfTrade record);

    List<AccountPfTrade> selectByExample(AccountPfTradeExample example);

    AccountPfTrade selectByPrimaryKey(Integer tradeId);

    int updateByExampleSelective(@Param("record") AccountPfTrade record, @Param("example") AccountPfTradeExample example);

    int updateByExample(@Param("record") AccountPfTrade record, @Param("example") AccountPfTradeExample example);

    int updateByPrimaryKeySelective(AccountPfTrade record);

    int updateByPrimaryKey(AccountPfTrade record);
}