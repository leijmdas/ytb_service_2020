package ytb.account.wallet.service.sq.basics.impl;


import org.apache.ibatis.session.SqlSession;

import ytb.account.wallet.dao.AccountUserOutMapper;
import ytb.account.wallet.model.AccountUserOut;
import ytb.account.wallet.model.AccountUserOutExample;
import ytb.account.wallet.service.sq.basics.AccountUserOutService;


import ytb.account.wallet.tool.MyBatisUtil;


import java.util.List;

/**
 * Package: ytb.manager.metadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
public class AccountUserOutServiceImpl implements AccountUserOutService {


    @Override
    public long countByExample(AccountUserOutExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(AccountUserOutExample example) {
        SqlSession sq = MyBatisUtil.getSession();

        try {
            int data = 0;
            AccountUserOutMapper accountUserOut = sq.getMapper(AccountUserOutMapper.class);
            data = accountUserOut.deleteByExample(example);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public int deleteByPrimaryKey(Integer outId) {
        return 0;
    }

    @Override
    public int insert(AccountUserOut record) {
        return 0;
    }

    @Override
    public int insertSelective(AccountUserOut record) {
        SqlSession sq = MyBatisUtil.getSession();

        try {
            int data = 0;
            AccountUserOutMapper accountUserOut = sq.getMapper(AccountUserOutMapper.class);
            data = accountUserOut.insertSelective(record);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public List<AccountUserOut> selectByExample(AccountUserOutExample example) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            AccountUserOutMapper accountUserOut = sq.getMapper(AccountUserOutMapper.class);
            List<AccountUserOut> data = accountUserOut.selectByExample(example);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public AccountUserOut selectByPrimaryKey(Integer outId) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            AccountUserOutMapper accountUserOut = sq.getMapper(AccountUserOutMapper.class);
            AccountUserOut data = accountUserOut.selectByPrimaryKey(outId);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public int updateByExampleSelective(AccountUserOut record, AccountUserOutExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            AccountUserOutMapper accountUserOut = sq.getMapper(AccountUserOutMapper.class);
            data = accountUserOut.updateByExampleSelective(record, example);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public int updateByExample(AccountUserOut record, AccountUserOutExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(AccountUserOut record) {
        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            AccountUserOutMapper accountUserOut = sq.getMapper(AccountUserOutMapper.class);
            data = accountUserOut.updateByPrimaryKeySelective(record);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public int updateByPrimaryKey(AccountUserOut record) {
        return 0;
    }
}
