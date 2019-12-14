package ytb.bangbang.service.impl;


import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.mapper.UserFriendsMapper;
import ytb.bangbang.model.UserFriends;
import ytb.bangbang.model.UserFriendsExample;
import ytb.bangbang.service.GetFriendService;
import ytb.bangbang.util.MyBatisUtil;


import java.util.List;


public class getFriendServiceImpl implements GetFriendService {

    @Override
    public long countByExample(UserFriendsExample example) {

        try (SqlSession sq = MyBatisUtil.getSession()) {
            UserFriendsMapper mapper = sq.getMapper(UserFriendsMapper.class);
            return mapper.countByExample(example);
        }

    }

    @Override
    public int deleteByExample(UserFriendsExample example) {

        try (SqlSession sq = MyBatisUtil.getSession()){
            UserFriendsMapper mapper = sq.getMapper(UserFriendsMapper.class);
            return  mapper.deleteByExample(example);

        }
    }

    @Override
    public int deleteByPrimaryKey(Integer userRltnId) {

        try(SqlSession sq = MyBatisUtil.getSession()) {
            UserFriendsMapper mapper = sq.getMapper(UserFriendsMapper.class);
            return mapper.deleteByPrimaryKey(userRltnId);

        }
    }

    @Override
    public int insert(UserFriends record) {
        SqlSession sq = MyBatisUtil.getSession();

        try {
            UserFriendsMapper mapper = sq.getMapper(UserFriendsMapper.class);
            return mapper.insert(record);

        } finally {
            sq.close();

        }
    }

    @Override
    public int insertSelective(UserFriends record) {


        try( SqlSession sq = MyBatisUtil.getSession()) {
            UserFriendsMapper mapper = sq.getMapper(UserFriendsMapper.class);
            return mapper.insertSelective(record);

        }
    }

    @Override
    public List<UserFriends> selectByExample(UserFriendsExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        List<UserFriends> sta;
        try {
            UserFriendsMapper mapper = sq.getMapper(UserFriendsMapper.class);
            sta = mapper.selectByExample(example);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }

    @Override
    public UserFriends selectByPrimaryKey(Integer userRltnId) {
        SqlSession sq = MyBatisUtil.getSession();
        UserFriends sta;
        try {
            UserFriendsMapper mapper = sq.getMapper(UserFriendsMapper.class);
            sta = mapper.selectByPrimaryKey(userRltnId);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }

    @Override
    public int updateByExampleSelective(UserFriends record, UserFriendsExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta;
        try {
            UserFriendsMapper mapper = sq.getMapper(UserFriendsMapper.class);
            sta = mapper.updateByExampleSelective(record, example);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }

    @Override
    public int updateByExample(UserFriends record, UserFriendsExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta;
        try {
            UserFriendsMapper mapper = sq.getMapper(UserFriendsMapper.class);
            sta = mapper.updateByExample(record, example);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }

    @Override
    public int updateByPrimaryKeySelective(UserFriends record) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta;
        try {
            UserFriendsMapper mapper = sq.getMapper(UserFriendsMapper.class);
            sta = mapper.updateByPrimaryKeySelective(record);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }

    @Override
    public int updateByPrimaryKey(UserFriends record) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta;
        try {
            UserFriendsMapper mapper = sq.getMapper(UserFriendsMapper.class);
            sta = mapper.updateByPrimaryKey(record);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }
}
