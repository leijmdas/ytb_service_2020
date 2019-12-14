package ytb.account.wallet.service.sq.basics;

import org.apache.ibatis.annotations.Param;

import ytb.account.wallet.model.AccountUserOut;
import ytb.account.wallet.model.AccountUserOutExample;

import java.util.List;

/**
 * Package: ytb.manager.metadata.service
 * Author: XZW
 * Date: Created in 2018/8/23 16:33
 */
public interface AccountUserOutService {


    long countByExample(AccountUserOutExample example);

    int deleteByExample(AccountUserOutExample example);

    int deleteByPrimaryKey(Integer outId);

    int insert(AccountUserOut record);

    int insertSelective(AccountUserOut record);

    List<AccountUserOut> selectByExample(AccountUserOutExample example);

    AccountUserOut selectByPrimaryKey(Integer outId);

    int updateByExampleSelective(@Param("record") AccountUserOut record, @Param("example") AccountUserOutExample example);

    int updateByExample(@Param("record") AccountUserOut record, @Param("example") AccountUserOutExample example);

    int updateByPrimaryKeySelective(AccountUserOut record);

    int updateByPrimaryKey(AccountUserOut record);

}
