package ytb.bangbang.service.impl;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.mapper.GroupInfoMapper;
import ytb.bangbang.mapper.GroupUserMapper;
import ytb.bangbang.model.GroupInfo;
import ytb.bangbang.model.GroupInfoExample;
import ytb.bangbang.model.GroupUser;
import ytb.bangbang.service.GroupInfoMapperService;
import ytb.bangbang.util.MyBatisUtil;

import java.util.List;

public class GroupInfoMapperServiceImpl implements GroupInfoMapperService {

    @Override
    public long countByExample(GroupInfoExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(GroupInfoExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer groupId) {
        return 0;
    }

    @Override
    public int insert(GroupInfo record) {
        return 0;
    }

    @Override
    public int insertSelective(GroupInfo record) {
        return 0;
    }

    @Override
    public List<GroupInfo> selectByExample(GroupInfoExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        List<GroupInfo> sta;
        try {
            GroupInfoMapper mapper = sq.getMapper(GroupInfoMapper.class);
            sta = mapper.selectByExample(example);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }

    @Override
    public GroupInfo selectByPrimaryKey(Integer groupId) {
        SqlSession sq = MyBatisUtil.getSession();
        GroupInfo sta;
        try {
            GroupInfoMapper mapper = sq.getMapper(GroupInfoMapper.class);
            sta = mapper.selectByPrimaryKey(groupId);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }

    @Override
    public int updateByExampleSelective(GroupInfo record, GroupInfoExample example) {
        return 0;
    }

    @Override
    public int updateByExample(GroupInfo record, GroupInfoExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(GroupInfo record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(GroupInfo record) {
        return 0;
    }
}