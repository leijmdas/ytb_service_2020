package ytb.account.wallet.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ytb.account.wallet.model.AccountPfInner;
import ytb.account.wallet.model.AccountPfInnerExample;

public interface AccountPfInnerMapper {
    long countByExample(AccountPfInnerExample example);

    int deleteByExample(AccountPfInnerExample example);

    int deleteByPrimaryKey(Integer pfInnerId);

    int insert(AccountPfInner record);

    int insertSelective(AccountPfInner record);

    List<AccountPfInner> selectByExample(AccountPfInnerExample example);

    AccountPfInner selectByPrimaryKey(Integer pfInnerId);

    int updateByExampleSelective(@Param("record") AccountPfInner record, @Param("example") AccountPfInnerExample example);

    int updateByExample(@Param("record") AccountPfInner record, @Param("example") AccountPfInnerExample example);

    int updateByPrimaryKeySelective(AccountPfInner record);

    int updateByPrimaryKey(AccountPfInner record);
}