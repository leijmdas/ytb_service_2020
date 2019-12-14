package ytb.manager.webpagemng.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import ytb.manager.context.MyBatisUtil;
import ytb.manager.webpagemng.dao.CurServiceHotSearchMapper;
import ytb.manager.webpagemng.model.CurServiceHotSearch;
import ytb.manager.webpagemng.model.CurServiceHotSearchExample;
import ytb.manager.webpagemng.service.CusHotService;

import java.util.List;

/**
 * @author lxz 2018/12/22 15:59
 */
@Service
public class CusHotImpl implements CusHotService {


    @Override
    public long countByExample(CurServiceHotSearchExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        long sta = 0;
        try {

            CurServiceHotSearchMapper accountPfDetailMapper = sq.getMapper(CurServiceHotSearchMapper.class);

            sta = accountPfDetailMapper.countByExample(example);


        } finally {
            sq.close();

        }

        return sta;
    }

    @Override
    public int deleteByExample(CurServiceHotSearchExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta = 0;
        try {

            CurServiceHotSearchMapper accountPfDetailMapper = sq.getMapper(CurServiceHotSearchMapper.class);

            sta = accountPfDetailMapper.deleteByExample(example);


        } finally {
            sq.close();

        }

        return sta;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta = 0;
        try {

            CurServiceHotSearchMapper accountPfDetailMapper = sq.getMapper(CurServiceHotSearchMapper.class);

            sta = accountPfDetailMapper.deleteByPrimaryKey(id);


        } finally {
            sq.close();

        }

        return sta;
    }

    @Override
    public int insert(CurServiceHotSearch record) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta = 0;
        try {

            CurServiceHotSearchMapper accountPfDetailMapper = sq.getMapper(CurServiceHotSearchMapper.class);

            sta = accountPfDetailMapper.insert(record);

            if (sta > 0) {
                sta = record.getId();
            }


        } finally {
            sq.close();

        }

        return sta;
    }

    @Override
    public int insertSelective(CurServiceHotSearch record) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta = 0;
        try {

            CurServiceHotSearchMapper accountPfDetailMapper = sq.getMapper(CurServiceHotSearchMapper.class);

            sta = accountPfDetailMapper.insertSelective(record);

            if (sta > 0) {
                sta = record.getId();
            }


        } finally {
            sq.close();

        }

        return sta;
    }

    @Override
    public List<CurServiceHotSearch> selectByExample(CurServiceHotSearchExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        List<CurServiceHotSearch> sta = null;
        try {

            CurServiceHotSearchMapper accountPfDetailMapper = sq.getMapper(CurServiceHotSearchMapper.class);

            sta = accountPfDetailMapper.selectByExample(example);


        } finally {
            sq.close();

        }

        return sta;
    }

    @Override
    public CurServiceHotSearch selectByPrimaryKey(Integer id) {
        SqlSession sq = MyBatisUtil.getSession();
        CurServiceHotSearch sta = null;
        try {

            CurServiceHotSearchMapper accountPfDetailMapper = sq.getMapper(CurServiceHotSearchMapper.class);

            sta = accountPfDetailMapper.selectByPrimaryKey(id);


        } finally {
            sq.close();

        }

        return sta;
    }

    @Override
    public int updateByExampleSelective(CurServiceHotSearch record, CurServiceHotSearchExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta = 0;
        try {

            CurServiceHotSearchMapper accountPfDetailMapper = sq.getMapper(CurServiceHotSearchMapper.class);

            sta = accountPfDetailMapper.updateByExampleSelective(record, example);


        } finally {
            sq.close();

        }

        return sta;
    }

    @Override
    public int updateByKeyWordSelective(CurServiceHotSearch record) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta = 0;
        try {

            CurServiceHotSearchMapper accountPfDetailMapper = sq.getMapper(CurServiceHotSearchMapper.class);

            sta = accountPfDetailMapper.updateByKeyWordSelective(record);


        } finally {
            sq.close();

        }

        return sta;
    }



    @Override
    public int updateByExample(CurServiceHotSearch record, CurServiceHotSearchExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta = 0;
        try {

            CurServiceHotSearchMapper accountPfDetailMapper = sq.getMapper(CurServiceHotSearchMapper.class);

            sta = accountPfDetailMapper.updateByExample(record, example);


        } finally {
            sq.close();

        }

        return sta;
    }

    @Override
    public int updateByPrimaryKeySelective(CurServiceHotSearch record) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta = 0;
        try {

            CurServiceHotSearchMapper accountPfDetailMapper = sq.getMapper(CurServiceHotSearchMapper.class);

            sta = accountPfDetailMapper.updateByPrimaryKeySelective(record);


        } finally {
            sq.close();

        }

        return sta;
    }

    @Override
    public int updateByPrimaryKey(CurServiceHotSearch record) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta = 0;
        try {

            CurServiceHotSearchMapper accountPfDetailMapper = sq.getMapper(CurServiceHotSearchMapper.class);

            sta = accountPfDetailMapper.updateByPrimaryKey(record);


        } finally {
            sq.close();

        }

        return sta;
    }
}
