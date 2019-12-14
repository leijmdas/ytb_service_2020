package ytb.account.wallet.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ytb.account.wallet.model.AccountPfOut;
import ytb.account.wallet.model.AccountPfOutExample;

public interface AccountPfOutMapper {
    long countByExample(AccountPfOutExample example);

    int deleteByExample(AccountPfOutExample example);

    int deleteByPrimaryKey(Integer pfOutId);

    int insert(AccountPfOut record);

    int insertSelective(AccountPfOut record);

    List<AccountPfOut> selectByExample(AccountPfOutExample example);

    AccountPfOut selectByPrimaryKey(Integer pfOutId);

    int updateByExampleSelective(@Param("record") AccountPfOut record, @Param("example") AccountPfOutExample example);

    int updateByExample(@Param("record") AccountPfOut record, @Param("example") AccountPfOutExample example);

    int updateByPrimaryKeySelective(AccountPfOut record);

    int updateByPrimaryKey(AccountPfOut record);
}