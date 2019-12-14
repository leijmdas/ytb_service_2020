package ytb.project.service.invoice.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.common.context.service.impl.YtbContext;
import ytb.project.dao.InvoiceInfoMapper;
import ytb.project.model.InvoiceInfo;
import ytb.project.model.InvoiceInfoExample;
import ytb.project.service.invoice.InvoiceInfoService;

import java.util.List;

/**
 * Copyright@ vipcchua.github.io
 * Author:Cchua
 * Date:2019/1/22
 */
public class InvoiceInfoServiceImpl implements InvoiceInfoService {
    @Override
    public long countByExample(InvoiceInfoExample example) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        long data = 0;
        try {

            InvoiceInfoMapper mapper = sq.getMapper(InvoiceInfoMapper.class);
            data = mapper.countByExample(example);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int deleteByExample(InvoiceInfoExample example) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceInfoMapper mapper = sq.getMapper(InvoiceInfoMapper.class);
            data = mapper.deleteByExample(example);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceInfoMapper mapper = sq.getMapper(InvoiceInfoMapper.class);
            data = mapper.deleteByPrimaryKey(id);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int insert(InvoiceInfo record) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceInfoMapper mapper = sq.getMapper(InvoiceInfoMapper.class);
            data = mapper.insert(record);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int insertSelective(InvoiceInfo record) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceInfoMapper mapper = sq.getMapper(InvoiceInfoMapper.class);
            data = mapper.insertSelective(record);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public List<InvoiceInfo> selectByExample(InvoiceInfoExample example) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        List<InvoiceInfo> data = null;
        try {

            InvoiceInfoMapper mapper = sq.getMapper(InvoiceInfoMapper.class);
            data = mapper.selectByExample(example);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public InvoiceInfo selectByPrimaryKey(Integer id) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        InvoiceInfo data = null;
        try {

            InvoiceInfoMapper mapper = sq.getMapper(InvoiceInfoMapper.class);
            data = mapper.selectByPrimaryKey(id);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int updateByExampleSelective(InvoiceInfo record, InvoiceInfoExample example) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceInfoMapper mapper = sq.getMapper(InvoiceInfoMapper.class);
            data = mapper.updateByExampleSelective(record, example);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int updateByExample(InvoiceInfo record, InvoiceInfoExample example) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceInfoMapper mapper = sq.getMapper(InvoiceInfoMapper.class);
            data = mapper.updateByExample(record, example);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int updateByPrimaryKeySelective(InvoiceInfo record) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceInfoMapper mapper = sq.getMapper(InvoiceInfoMapper.class);
            data = mapper.updateByPrimaryKeySelective(record);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int updateByPrimaryKey(InvoiceInfo record) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceInfoMapper mapper = sq.getMapper(InvoiceInfoMapper.class);
            data = mapper.updateByPrimaryKey(record);

        } finally {
            sq.close();
        }
        return data;
    }
}
