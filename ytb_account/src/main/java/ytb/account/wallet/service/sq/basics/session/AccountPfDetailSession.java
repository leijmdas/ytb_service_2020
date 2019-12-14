package ytb.account.wallet.service.sq.basics.session;

import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.AccountPfDetailMapper;
import ytb.account.wallet.model.AccountPfDetail;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/21
 */
public class AccountPfDetailSession {


    public Boolean insertSelective(AccountPfDetail record, SqlSession sq) {

        try {

            AccountPfDetailMapper mapper = sq.getMapper(AccountPfDetailMapper.class);

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


    public Boolean updateByPrimaryKeySelective(AccountPfDetail record, SqlSession sq) {

        try {

            AccountPfDetailMapper mapper = sq.getMapper(AccountPfDetailMapper.class);

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
