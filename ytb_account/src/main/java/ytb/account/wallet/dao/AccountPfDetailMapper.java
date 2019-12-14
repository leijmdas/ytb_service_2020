package ytb.account.wallet.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ytb.account.wallet.model.AccountPfDetail;
import ytb.account.wallet.model.AccountPfDetailExample;

public interface AccountPfDetailMapper {
    long countByExample(AccountPfDetailExample example);

    int deleteByExample(AccountPfDetailExample example);

    int deleteByPrimaryKey(Integer pfDetailId);

    int insert(AccountPfDetail record);

    int insertSelective(AccountPfDetail record);

    List<AccountPfDetail> selectByExample(AccountPfDetailExample example);

    AccountPfDetail selectByPrimaryKey(Integer pfDetailId);

    int updateByExampleSelective(@Param("record") AccountPfDetail record, @Param("example") AccountPfDetailExample example);

    int updateByExample(@Param("record") AccountPfDetail record, @Param("example") AccountPfDetailExample example);

    int updateByPrimaryKeySelective(AccountPfDetail record);

    int updateByPrimaryKey(AccountPfDetail record);
}