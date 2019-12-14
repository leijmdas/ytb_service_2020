package ytb.account.wallet.service.sq.basics;

import org.apache.ibatis.annotations.Param;
import ytb.account.wallet.model.AccountTrade;
import ytb.account.wallet.model.AccountTradeExample;
import ytb.account.wallet.model.AccountUserDetail;

import ytb.account.wallet.pojo.ProjectBalanceAgent;
import ytb.account.wallet.pojo.ProjectBalanceOutAc;
import ytb.account.wallet.pojo.ProjectBalanceToAc;

import java.math.BigDecimal;
import java.util.List;

public interface AccountTradeService {
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


}