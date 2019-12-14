package ytb.account.wallet.service.sq.external.user.impl;


import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.CompanyInfoMapper;
import ytb.account.wallet.model.*;
import ytb.account.wallet.service.sq.external.user.CompanyInfoService;
import ytb.account.wallet.tool.MyBatisUtil;

import java.util.List;

/**
 * Package: ytb.manager.metadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
public class CompanyInfoServiceImpl implements CompanyInfoService {


    @Override
    public long countByExample(CompanyInfoExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(CompanyInfoExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer companyId) {
        return 0;
    }

    @Override
    public int insert(CompanyInfo record) {
        return 0;
    }

    @Override
    public int insertSelective(CompanyInfo record) {
        return 0;
    }

    @Override
    public List<CompanyInfo> selectByExample(CompanyInfoExample example) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            CompanyInfoMapper accountTradeMapper = sq.getMapper(CompanyInfoMapper.class);
            List<CompanyInfo> data = accountTradeMapper.selectByExample(example);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public CompanyInfo selectByPrimaryKey(Integer companyId) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            CompanyInfoMapper accountTradeMapper = sq.getMapper(CompanyInfoMapper.class);
            CompanyInfo data = accountTradeMapper.selectByPrimaryKey(companyId);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public int updateByExampleSelective(CompanyInfo record, CompanyInfoExample example) {
        return 0;
    }

    @Override
    public int updateByExample(CompanyInfo record, CompanyInfoExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(CompanyInfo record) {
        SqlSession sq = MyBatisUtil.getSession();


        try {

            CompanyInfoMapper accountTradeMapper = sq.getMapper(CompanyInfoMapper.class);
            int data = accountTradeMapper.updateByPrimaryKeySelective(record);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public int updateByPrimaryKey(CompanyInfo record) {
        return 0;
    }
}
