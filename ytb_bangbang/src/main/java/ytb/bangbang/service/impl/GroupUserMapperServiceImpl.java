package ytb.bangbang.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.mapper.GroupUserMapper;
import ytb.bangbang.model.GroupUser;
import ytb.bangbang.model.GroupUserExample;
import ytb.bangbang.service.GroupUserMapperService;
import ytb.bangbang.util.MyBatisUtil;

import java.util.List;

public class GroupUserMapperServiceImpl implements GroupUserMapperService {

    @Override
    public long countByExample(GroupUserExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(GroupUserExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer groupUserId) {
        return 0;
    }

    @Override
    public int insert(GroupUser record) {
        return 0;
    }

    @Override
    public int insertSelective(GroupUser record) {
        return 0;
    }

    @Override
    public List<GroupUser> selectByExample(GroupUserExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        List<GroupUser> sta;
        try {
            GroupUserMapper mapper = sq.getMapper(GroupUserMapper.class);
            sta = mapper.selectByExample(example);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }

    @Override
    public GroupUser selectByPrimaryKey(Integer groupUserId) {
        SqlSession sq = MyBatisUtil.getSession();
        GroupUser sta;
        try {
            GroupUserMapper mapper = sq.getMapper(GroupUserMapper.class);
            sta = mapper.selectByPrimaryKey(groupUserId);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }

    @Override
    public int updateByExampleSelective(GroupUser record, GroupUserExample example) {
        return 0;
    }

    @Override
    public int updateByExample(GroupUser record, GroupUserExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(GroupUser record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(GroupUser record) {
        return 0;
    }

}