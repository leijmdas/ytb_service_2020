package ytb.account.wallet.service.sq.basics.impl;


import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.AccountPfDetailMapper;
import ytb.account.wallet.model.AccountPfDetail;
import ytb.account.wallet.model.AccountPfDetailExample;
import ytb.account.wallet.model.AccountPfInner;
import ytb.account.wallet.service.sq.basics.AccountPfDetailService;
import ytb.account.wallet.tool.MyBatisUtil;

import java.util.List;

/**
 * Package: ytb.manager.metadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
public class AccountPfDetailServiceImpl implements AccountPfDetailService {

    @Override
    public long countByExample(AccountPfDetailExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(AccountPfDetailExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer pfDetailId) {
        return 0;
    }

    @Override
    public int insert(AccountPfDetail record) {
        return 0;
    }

    @Override
    public int insertSelective(AccountPfDetail record) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            AccountPfDetailMapper accountPfDetailMapper = sq.getMapper(AccountPfDetailMapper.class);

            int data = accountPfDetailMapper.insertSelective(record);


            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public List<AccountPfDetail> selectByExample(AccountPfDetailExample example) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            AccountPfDetailMapper accountPfDetailMapper = sq.getMapper(AccountPfDetailMapper.class);
            List<AccountPfDetail> data = accountPfDetailMapper.selectByExample(example);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public AccountPfDetail selectByPrimaryKey(Integer pfDetailId) {
        return null;
    }

    @Override
    public int updateByExampleSelective(AccountPfDetail record, AccountPfDetailExample example) {
        return 0;
    }

    @Override
    public int updateByExample(AccountPfDetail record, AccountPfDetailExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(AccountPfDetail record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(AccountPfDetail record) {
        return 0;
    }


    @Override
    public int newRecord(AccountPfDetail record, AccountPfInner accountPfInner) {
        SqlSession sq = MyBatisUtil.getSession(false);
        int data = 0;
        try {
            return data;
        } finally {
            sq.close();

        }
    }


}
