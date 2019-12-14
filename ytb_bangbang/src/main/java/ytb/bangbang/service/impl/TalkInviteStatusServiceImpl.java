package ytb.bangbang.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.mapper.TalkInviteStatusMapper;
import ytb.bangbang.model.TalkInviteStatus;
import ytb.bangbang.model.TalkInviteStatusExample;
import ytb.bangbang.service.TalkInviteStatusService;
import ytb.bangbang.util.MyBatisUtil;

import java.util.List;


public class TalkInviteStatusServiceImpl implements TalkInviteStatusService {

    @Override
    public long countByExample(TalkInviteStatusExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        Long sta;
        try {
            TalkInviteStatusMapper mapper = sq.getMapper(TalkInviteStatusMapper.class);
            sta = mapper.countByExample(example);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }

    @Override
    public int deleteByExample(TalkInviteStatusExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta;
        try {
            TalkInviteStatusMapper mapper = sq.getMapper(TalkInviteStatusMapper.class);
            sta = mapper.deleteByExample(example);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }

    @Override
    public int deleteByPrimaryKey(Integer inviteId) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta;
        try {
            TalkInviteStatusMapper mapper = sq.getMapper(TalkInviteStatusMapper.class);
            sta = mapper.deleteByPrimaryKey(inviteId);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }

    @Override
    public int insert(TalkInviteStatus record) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta;
        try {
            TalkInviteStatusMapper mapper = sq.getMapper(TalkInviteStatusMapper.class);
            sta = mapper.insert(record);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }

    @Override
    public int insertSelective(TalkInviteStatus record) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta;
        try {
            TalkInviteStatusMapper mapper = sq.getMapper(TalkInviteStatusMapper.class);
            sta = mapper.insertSelective(record);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }

    @Override
    public List<TalkInviteStatus> selectByExample(TalkInviteStatusExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        List<TalkInviteStatus> sta = null;
        try {
            TalkInviteStatusMapper mapper = sq.getMapper(TalkInviteStatusMapper.class);
            sta = mapper.selectByExample(example);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }

    @Override
    public TalkInviteStatus selectByPrimaryKey(Integer inviteId) {
        SqlSession sq = MyBatisUtil.getSession();
        TalkInviteStatus sta;
        try {
            TalkInviteStatusMapper mapper = sq.getMapper(TalkInviteStatusMapper.class);
            sta = mapper.selectByPrimaryKey(inviteId);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }

    @Override
    public int updateByExampleSelective(TalkInviteStatus record, TalkInviteStatusExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta;
        try {
            TalkInviteStatusMapper mapper = sq.getMapper(TalkInviteStatusMapper.class);
            sta = mapper.updateByExampleSelective(record, example);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }

    @Override
    public int updateByExample(TalkInviteStatus record, TalkInviteStatusExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta;
        try {
            TalkInviteStatusMapper mapper = sq.getMapper(TalkInviteStatusMapper.class);
            sta = mapper.updateByExample(record, example);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }

    @Override
    public int updateByPrimaryKeySelective(TalkInviteStatus record) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta;
        try {
            TalkInviteStatusMapper mapper = sq.getMapper(TalkInviteStatusMapper.class);
            sta = mapper.updateByPrimaryKeySelective(record);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }

    @Override
    public int updateByPrimaryKey(TalkInviteStatus record) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta;
        try {
            TalkInviteStatusMapper mapper = sq.getMapper(TalkInviteStatusMapper.class);
            sta = mapper.updateByPrimaryKey(record);
            return sta;
        } finally {
            sq.rollback();
            sq.close();

        }
    }
}