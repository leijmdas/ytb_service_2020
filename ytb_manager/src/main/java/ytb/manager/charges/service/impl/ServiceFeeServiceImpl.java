package ytb.manager.charges.service.impl;


import org.apache.ibatis.session.SqlSession;
import ytb.manager.charges.dao.ServiceFeeMapper;
import ytb.manager.charges.model.ServiceFee;
import ytb.manager.charges.model.ServiceFeeExample;
import ytb.manager.charges.service.ServiceFeeService;
import ytb.manager.context.MyBatisUtil;

import java.util.List;


public class ServiceFeeServiceImpl implements ServiceFeeService {


    @Override
    public long countByExample(ServiceFeeExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(ServiceFeeExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer feeId) {
        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            ServiceFeeMapper mapper = sq.getMapper(ServiceFeeMapper.class);
            data = mapper.deleteByPrimaryKey(feeId);

        } catch (Exception e) {

        } finally {
            sq.close();

        }
        return data;
    }

    @Override
    public int insert(ServiceFee record) {
        return 0;
    }

    @Override
    public int insertSelective(ServiceFee record) {
        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            ServiceFeeMapper mapper = sq.getMapper(ServiceFeeMapper.class);
            data = mapper.insertSelective(record);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sq.close();

        }
        return data;
    }


    @Override
    public int insertSelectiveList(List<ServiceFee> record) {
        SqlSession sq = MyBatisUtil.getSession(false);
        int data = 0;
        try {

            ServiceFeeMapper mapper = sq.getMapper(ServiceFeeMapper.class);
            for (int i = 0; i < record.size(); i++) {
                data = mapper.insertSelective(record.get(i));
                if (data <= 0) {
                    sq.rollback();
                    return 0;
                }
            }
            data = 1;

        } catch (Exception e) {
            sq.rollback();
            e.printStackTrace();
        } finally {
            sq.close();

        }
        return data;
    }


    @Override
    public List<ServiceFee> selectByExample(ServiceFeeExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        try {

            ServiceFeeMapper mapper = sq.getMapper(ServiceFeeMapper.class);
            return mapper.selectByExample(example);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            sq.close();

        }

    }

    @Override
    public ServiceFee selectByPrimaryKey(Integer feeId) {
        SqlSession sq = MyBatisUtil.getSession();
        ServiceFee data = null;
        try {

            ServiceFeeMapper mapper = sq.getMapper(ServiceFeeMapper.class);
            data = mapper.selectByPrimaryKey(feeId);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sq.close();

        }
        return data;
    }

    @Override
    public int updateByExampleSelective(ServiceFee record, ServiceFeeExample example) {
        return 0;
    }

    @Override
    public int updateByExample(ServiceFee record, ServiceFeeExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(ServiceFee record) {
        SqlSession sq = MyBatisUtil.getSession();
        int data = 0;
        try {

            ServiceFeeMapper mapper = sq.getMapper(ServiceFeeMapper.class);
            data = mapper.updateByPrimaryKeySelective(record);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sq.close();

        }
        return data;
    }

    @Override
    public int updateByPrimaryKey(ServiceFee record) {
        return 0;
    }
}
