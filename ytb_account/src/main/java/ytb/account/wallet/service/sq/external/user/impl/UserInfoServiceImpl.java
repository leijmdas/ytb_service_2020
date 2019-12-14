package ytb.account.wallet.service.sq.external.user.impl;


import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.UserInfoMapper;
import ytb.account.wallet.model.*;
import ytb.account.wallet.service.sq.external.user.UserInfoService;
import ytb.account.wallet.tool.MyBatisUtil;

import java.util.List;

/**
 * Package: ytb.manager.metadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
public class UserInfoServiceImpl implements UserInfoService {

    @Override
    public long countByExample(UserInfoExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(UserInfoExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return 0;
    }

    @Override
    public int insert(UserInfo record) {
        return 0;
    }

    @Override
    public int insertSelective(UserInfo record) {
        return 0;
    }

    @Override
    public List<UserInfo> selectByExample(UserInfoExample example) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            UserInfoMapper accountTradeMapper = sq.getMapper(UserInfoMapper.class);
            List<UserInfo> data = accountTradeMapper.selectByExample(example);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public UserInfo selectByPrimaryKey(Integer userId) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            UserInfoMapper accountTradeMapper = sq.getMapper(UserInfoMapper.class);
            UserInfo data = accountTradeMapper.selectByPrimaryKey(userId);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public int updateByExampleSelective(UserInfo record, UserInfoExample example) {
        return 0;
    }

    @Override
    public int updateByExample(UserInfo record, UserInfoExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(UserInfo record) {
        return 0;
    }
}
