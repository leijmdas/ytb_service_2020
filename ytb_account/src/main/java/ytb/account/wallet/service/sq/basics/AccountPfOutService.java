package ytb.account.wallet.service.sq.basics;

import org.apache.ibatis.annotations.Param;
import ytb.account.wallet.model.AccountPfOut;
import ytb.account.wallet.model.AccountPfOutExample;
import ytb.account.wallet.model.AccountUserOut;
import ytb.account.wallet.model.AccountUserOutExample;

import java.util.List;

/**
 * Package: ytb.manager.metadata.service
 * Author: XZW
 * Date: Created in 2018/8/23 16:33
 */
public interface AccountPfOutService {

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
