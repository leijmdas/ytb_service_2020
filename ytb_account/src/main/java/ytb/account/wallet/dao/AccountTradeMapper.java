package ytb.account.wallet.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ytb.account.wallet.model.AccountTrade;
import ytb.account.wallet.model.AccountTradeExample;
import ytb.account.wallet.model.Condition;

public interface AccountTradeMapper {
    long countByExample(AccountTradeExample example);

    int deleteByExample(AccountTradeExample example);

    int deleteByPrimaryKey(Integer tradeId);

    int insert(AccountTrade record);

    int insertSelective(AccountTrade record);

    List<AccountTrade> selectByExample(AccountTradeExample example);

    AccountTrade selectByPrimaryKey(Integer tradeId);

    int updateByExampleSelective(@Param("record") AccountTrade record, @Param("example") AccountTradeExample example);

    int updateByExample(@Param("record") AccountTrade record, @Param("example") AccountTradeExample example);

    int updateByPrimaryKeySelective(AccountTrade record);

    int updateByPrimaryKey(AccountTrade record);

    /**
     * zc
     * 以condition中不为空的字段 为条件查询
     * 结果为 List<AccountTrade>
     * @param condition
     * @return
     */
    List<AccountTrade>  queryAccountTradeList(Condition condition);
}