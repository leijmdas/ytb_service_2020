package ytb.account.wallet.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ytb.account.wallet.model.AccountUserDetail;
import ytb.account.wallet.model.AccountUserDetailExample;

public interface AccountUserDetailMapper {
    long countByExample(AccountUserDetailExample example);

    int deleteByExample(AccountUserDetailExample example);

    int deleteByPrimaryKey(Integer detailId);

    int insert(AccountUserDetail record);

    int insertSelective(AccountUserDetail record);

    List<AccountUserDetail> selectByExample(AccountUserDetailExample example);

    AccountUserDetail selectByPrimaryKey(Integer detailId);

    int updateByExampleSelective(@Param("record") AccountUserDetail record, @Param("example") AccountUserDetailExample example);

    int updateByExample(@Param("record") AccountUserDetail record, @Param("example") AccountUserDetailExample example);

    int updateByPrimaryKeySelective(AccountUserDetail record);

    int updateByPrimaryKey(AccountUserDetail record);
}