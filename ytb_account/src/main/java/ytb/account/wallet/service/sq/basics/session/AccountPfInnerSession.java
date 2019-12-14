package ytb.account.wallet.service.sq.basics.session;

import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.AccountPfInnerMapper;
import ytb.account.wallet.model.AccountPfInner;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/21
 */
public class AccountPfInnerSession {


    public Boolean insertSelective(AccountPfInner record, SqlSession sq) {

        try {
            AccountPfInnerMapper mapper = sq.getMapper(AccountPfInnerMapper.class);

            int data = mapper.insertSelective(record);

            if (data > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public Boolean updateByPrimaryKeySelective(AccountPfInner record, SqlSession sq) {

        try {
            AccountPfInnerMapper mapper = sq.getMapper(AccountPfInnerMapper.class);

            int data = mapper.updateByPrimaryKeySelective(record);

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
