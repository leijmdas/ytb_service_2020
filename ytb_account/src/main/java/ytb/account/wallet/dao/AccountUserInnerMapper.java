package ytb.account.wallet.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ytb.account.wallet.model.AccountUserInner;
import ytb.account.wallet.model.AccountUserInnerExample;

public interface AccountUserInnerMapper {
    long countByExample(AccountUserInnerExample example);

    int deleteByExample(AccountUserInnerExample example);

    int deleteByPrimaryKey(Integer innerId);

    int insert(AccountUserInner record);

    int insertSelective(AccountUserInner record);

    List<AccountUserInner> selectByExample(AccountUserInnerExample example);

    AccountUserInner selectByPrimaryKey(Integer innerId);

    int updateByExampleSelective(@Param("record") AccountUserInner record, @Param("example") AccountUserInnerExample example);

    int updateByExample(@Param("record") AccountUserInner record, @Param("example") AccountUserInnerExample example);

    int updateByPrimaryKeySelective(AccountUserInner record);

    int updateByPrimaryKey(AccountUserInner record);
}