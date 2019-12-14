package ytb.account.wallet.service.sq.basics.session;

import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.AccountUserInnerMapper;
import ytb.account.wallet.model.AccountUserInner;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/21
 */
public class AccountUserInnerSession {


    public Boolean insertSelective(AccountUserInner record, SqlSession sq) {


        try {

            AccountUserInnerMapper accountPfDetailMapper = sq.getMapper(AccountUserInnerMapper.class);

            int data = accountPfDetailMapper.insertSelective(record);
            if (data > 0) {
                return true;

            } else {
                return false;
            }

        } finally {
            sq.close();

        }
    }


    public Boolean updateByPrimaryKeySelective(AccountUserInner record, SqlSession sq) {


        try {

            AccountUserInnerMapper accountPfDetailMapper = sq.getMapper(AccountUserInnerMapper.class);

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
