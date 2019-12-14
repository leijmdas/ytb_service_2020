package ytb.account.wallet.service.sq.basics.impl;


import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.AccountUserInnerMapper;
import ytb.account.wallet.model.AccountUserInner;
import ytb.account.wallet.model.AccountUserInnerExample;
import ytb.account.wallet.service.sq.basics.AccountUserInnerService;
import ytb.account.wallet.tool.MyBatisUtil;

import java.util.List;

/**
 * Package: ytb.manager.metadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
public class AccountUserInnerServiceImpl implements AccountUserInnerService {


    @Override
    public long countByExample(AccountUserInnerExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(AccountUserInnerExample example) {
        SqlSession sq = MyBatisUtil.getSession();

        try {
            int data = 0;
            AccountUserInnerMapper accountUserOut = sq.getMapper(AccountUserInnerMapper.class);
            data = accountUserOut.deleteByExample(example);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public int deleteByPrimaryKey(Integer innerId) {
        return 0;
    }

    @Override
    public int insert(AccountUserInner record) {
        return 0;
    }

    @Override
    public int insertSelective(AccountUserInner record) {

        SqlSession sq = MyBatisUtil.getSession(false);

        int data = 0;
        try {

            AccountUserInnerMapper accountUserOut = sq.getMapper(AccountUserInnerMapper.class);
            data = accountUserOut.insertSelective(record);
            if (data > 0) {
                record.getInnerId();
            } else {
                return 0;
            }
            sq.commit();

        } catch (Exception e) {
            sq.rollback();
        } finally {
            sq.close();

        }
        return data;
    }

    @Override
    public AccountUserInner selectByPrimaryKey(Integer innerId) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            AccountUserInnerMapper accountUserOut = sq.getMapper(AccountUserInnerMapper.class);
            AccountUserInner data = accountUserOut.selectByPrimaryKey(innerId);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public List<AccountUserInner> selectByExample(AccountUserInnerExample example) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            AccountUserInnerMapper accountUserOut = sq.getMapper(AccountUserInnerMapper.class);
            List<AccountUserInner> data = accountUserOut.selectByExample(example);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public int updateByExampleSelective(AccountUserInner record, AccountUserInnerExample example) {
        return 0;
    }

    @Override
    public int updateByExample(AccountUserInner record, AccountUserInnerExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(AccountUserInner record) {

        int data = 0;
        SqlSession sq = MyBatisUtil.getSession(false);

        try {

            AccountUserInnerMapper accountUserOut = sq.getMapper(AccountUserInnerMapper.class);
            data = accountUserOut.updateByPrimaryKeySelective(record);
            sq.commit();

        } catch (Exception e) {
            sq.rollback();
        } finally {
            sq.close();

        }
        return data;


    }

    @Override
    public int updateByPrimaryKey(AccountUserInner record) {
        return 0;
    }
}
