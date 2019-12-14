package ytb.account.wallet.service.sq.basics;

import org.apache.ibatis.annotations.Param;
import ytb.account.wallet.model.AccountPfDetail;
import ytb.account.wallet.model.AccountPfDetailExample;
import ytb.account.wallet.model.AccountPfInner;


import java.util.List;

/**
 * Package: ytb.manager.metadata.service
 * Author: XZW
 * Date: Created in 2018/8/23 16:33
 */
public interface AccountPfDetailService {
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




    int newRecord(AccountPfDetail record, AccountPfInner accountPfInner);
}
