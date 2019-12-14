package ytb.manager.charges.service.impl;


import org.apache.ibatis.session.SqlSession;
import ytb.manager.charges.dao.DictAreaSalaryMapper;
import ytb.manager.charges.model.DictAreaSalary;
import ytb.manager.charges.model.DictAreaSalaryExample;
import ytb.manager.charges.service.DictAreaSalaryService;
import ytb.manager.context.MyBatisUtil;

import java.util.List;

/**
 * Package: ytb.manager.metadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
public class DictAreaSalaryServiceImpl implements DictAreaSalaryService {

    @Override
    public long countByExample(DictAreaSalaryExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(DictAreaSalaryExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer salaryId) {
        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            DictAreaSalaryMapper mapper = sq.getMapper(DictAreaSalaryMapper.class);
            data = mapper.deleteByPrimaryKey(salaryId);

        } catch (Exception e) {

        } finally {
            sq.close();

        }
        return data;
    }

    @Override
    public int insert(DictAreaSalary record) {
        return 0;
    }

    @Override
    public int insertSelective(DictAreaSalary record) {
        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            DictAreaSalaryMapper mapper = sq.getMapper(DictAreaSalaryMapper.class);
            data = mapper.insertSelective(record);

        } catch (Exception e) {

        } finally {
            sq.close();

        }
        return data;
    }

    @Override
    public List<DictAreaSalary> selectByExample(DictAreaSalaryExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        List<DictAreaSalary> data = null;
        try {

            DictAreaSalaryMapper mapper = sq.getMapper(DictAreaSalaryMapper.class);
            data = mapper.selectByExample(example);

        } catch (Exception e) {

        } finally {
            sq.close();

        }
        return data;
    }

    @Override
    public DictAreaSalary selectByPrimaryKey(Integer salaryId) {
        SqlSession sq = MyBatisUtil.getSession();
        DictAreaSalary data = null;
        try {

            DictAreaSalaryMapper mapper = sq.getMapper(DictAreaSalaryMapper.class);
            data = mapper.selectByPrimaryKey(salaryId);

        } catch (Exception e) {

        } finally {
            sq.close();

        }
        return data;
    }

    @Override
    public int updateByExampleSelective(DictAreaSalary record, DictAreaSalaryExample example) {
        return 0;
    }

    @Override
    public int updateByExample(DictAreaSalary record, DictAreaSalaryExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(DictAreaSalary record) {
        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            DictAreaSalaryMapper mapper = sq.getMapper(DictAreaSalaryMapper.class);
            data = mapper.updateByPrimaryKeySelective(record);

        } catch (Exception e) {

        } finally {
            sq.close();

        }
        return data;
    }

    @Override
    public int updateByPrimaryKey(DictAreaSalary record) {
        return 0;
    }
}
