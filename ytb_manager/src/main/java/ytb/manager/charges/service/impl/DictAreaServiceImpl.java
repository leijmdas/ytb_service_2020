package ytb.manager.charges.service.impl;


import org.apache.ibatis.session.SqlSession;
import ytb.manager.charges.dao.DictAreaMapper;
import ytb.manager.charges.model.DictArea;
import ytb.manager.charges.model.DictAreaExample;
import ytb.manager.charges.service.DictAreaService;
import ytb.manager.context.MyBatisUtil;


import java.util.List;

/**
 * Package: ytb.manager.metadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
public class DictAreaServiceImpl implements DictAreaService {

    @Override
    public long countByExample(DictAreaExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(DictAreaExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer areaId) {
        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            DictAreaMapper mapper = sq.getMapper(DictAreaMapper.class);
            data = mapper.deleteByPrimaryKey(areaId);

        } catch (Exception e) {

        } finally {
            sq.close();

        }
        return data;

    }

    @Override
    public int insert(DictArea record) {
        return 0;
    }

    @Override
    public int insertSelective(DictArea record) {
        SqlSession sq = MyBatisUtil.getSession();
        int sta = 0;
        try {

            DictAreaMapper mapper = sq.getMapper(DictAreaMapper.class);
            sta = mapper.insertSelective(record);

        } catch (Exception e) {

        } finally {
            sq.close();

        }
        return sta;
    }

    @Override
    public List<DictArea> selectByExample(DictAreaExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        List<DictArea> data = null;
        try {

            DictAreaMapper mapper = sq.getMapper(DictAreaMapper.class);
            data = mapper.selectByExample(example);

        } catch (Exception e) {

        } finally {
            sq.close();

        }
        return data;
    }

    @Override
    public DictArea selectByPrimaryKey(Integer areaId) {
        SqlSession sq = MyBatisUtil.getSession();
        DictArea data = null;
        try {

            DictAreaMapper mapper = sq.getMapper(DictAreaMapper.class);
            data = mapper.selectByPrimaryKey(areaId);

        } catch (Exception e) {

        } finally {
            sq.close();

        }
        return data;
    }

    @Override
    public int updateByExampleSelective(DictArea record, DictAreaExample example) {


        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            DictAreaMapper mapper = sq.getMapper(DictAreaMapper.class);
            data = mapper.updateByExampleSelective(record, example);

        } catch (Exception e) {

        } finally {
            sq.close();

        }
        return data;

    }

    @Override
    public int updateByExample(DictArea record, DictAreaExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(DictArea record) {

        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            DictAreaMapper mapper = sq.getMapper(DictAreaMapper.class);
            data = mapper.updateByPrimaryKeySelective(record);

        } catch (Exception e) {

        } finally {
            sq.close();

        }
        return data;


    }

    @Override
    public int updateByPrimaryKey(DictArea record) {
        return 0;
    }
}
