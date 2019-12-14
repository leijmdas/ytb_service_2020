package ytb.account.wallet.service.sq.basics;

import org.apache.ibatis.annotations.Param;
import ytb.account.wallet.model.AccountUserInner;
import ytb.account.wallet.model.AccountUserInnerExample;
import ytb.account.wallet.model.AccountUserOut;
import ytb.account.wallet.model.AccountUserOutExample;

import java.util.List;

/**
 * Package: ytb.manager.metadata.service
 * Author: XZW
 * Date: Created in 2018/8/23 16:33
 */
public interface AccountUserInnerService {


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
