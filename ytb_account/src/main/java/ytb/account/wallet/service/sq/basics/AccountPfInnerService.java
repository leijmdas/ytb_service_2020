package ytb.account.wallet.service.sq.basics;

import org.apache.ibatis.annotations.Param;
import ytb.account.wallet.model.*;

import java.util.List;

/**
 * Package: ytb.manager.metadata.service
 * Author: XZW
 * Date: Created in 2018/8/23 16:33
 */
public interface AccountPfInnerService {
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


//增加
    int newRecordIn(AccountPfDetail data);

//减少
    int newRecordOut(AccountPfDetail data);

}
