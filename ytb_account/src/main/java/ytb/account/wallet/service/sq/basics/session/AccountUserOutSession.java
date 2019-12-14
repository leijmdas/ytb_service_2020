package ytb.account.wallet.service.sq.basics.session;

import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.AccountUserOutMapper;
import ytb.account.wallet.model.AccountUserOut;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/21
 */
public class AccountUserOutSession {


    public Boolean insertSelective(AccountUserOut record, SqlSession sq) {


        try {

            AccountUserOutMapper accountPfDetailMapper = sq.getMapper(AccountUserOutMapper.class);

            int data = accountPfDetailMapper.insertSelective(record);

            if (data > 0) {
                return true;

            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }


    public Boolean updateByPrimaryKeySelective(AccountUserOut record, SqlSession sq) {


        try {

            AccountUserOutMapper accountPfDetailMapper = sq.getMapper(AccountUserOutMapper.class);

            int data = accountPfDetailMapper.updateByPrimaryKeySelective(record);

            if (data > 0) {
                return true;

            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
