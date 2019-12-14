package ytb.account.wallet.service.sq.basics.session;

import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.AccountPfOutMapper;
import ytb.account.wallet.model.AccountPfOut;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/21
 */
public class AccountPfOutSession {


    public Boolean insertSelective(AccountPfOut record, SqlSession sq) {


        try {

            AccountPfOutMapper accountPfDetailMapper = sq.getMapper(AccountPfOutMapper.class);

            int data = accountPfDetailMapper.insertSelective(record);

            if (data > 0) {
                return
                        true;

            } else {
                return
                        false;
            }


        } catch (Exception e) {
            return false;
        }
    }

    public Boolean updateByPrimaryKeySelective(AccountPfOut record, SqlSession sq) {


        try {

            AccountPfOutMapper accountPfDetailMapper = sq.getMapper(AccountPfOutMapper.class);

            int data = accountPfDetailMapper.updateByPrimaryKeySelective(record);

            if (data > 0) {
                return
                        true;

            } else {
                return
                        false;
            }


        } catch (Exception e) {
            return false;
        }
    }

}
