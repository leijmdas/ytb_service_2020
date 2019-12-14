package ytb.account.wallet.service.sq.basics.impl;


import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.AccountUserDetailMapper;
import ytb.account.wallet.model.*;
import ytb.account.wallet.service.sq.basics.AccountUserDetailService;
import ytb.account.wallet.tool.MyBatisUtil;

import java.util.List;

/**
 * Package: ytb.manager.metadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
public class AccountUserDetailServiceImpl implements AccountUserDetailService {


    @Override
    public long countByExample(AccountUserDetailExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(AccountUserDetailExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer detailId) {
        return 0;
    }

    @Override
    public int insert(AccountUserDetail record) {
        return 0;
    }

    @Override
    public int insertSelective(AccountUserDetail record) {
        SqlSession sq = MyBatisUtil.getSession(false);
        int data = 0;
        try {

            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);

            data = accountUserDetailMapper.insertSelective(record);

            sq.commit();

        } catch (Exception e) {
            // System.out.println("出错------------");
            e.printStackTrace();
            sq.rollback();
        } finally {
            sq.close();

        }
        return data;
    }







    @Override
    public List<AccountUserDetail> selectByExample(AccountUserDetailExample example) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            AccountUserDetailMapper accountUserDetailMapper = sq.getMapper(AccountUserDetailMapper.class);
            List<AccountUserDetail> data = accountUserDetailMapper.selectByExample(example);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public AccountUserDetail selectByPrimaryKey(Integer detailId) {
        return null;
    }

    @Override
    public int updateByExampleSelective(AccountUserDetail record, AccountUserDetailExample example) {
        return 0;
    }

    @Override
    public int updateByExample(AccountUserDetail record, AccountUserDetailExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(AccountUserDetail record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(AccountUserDetail record) {
        return 0;
    }
}
