package ytb.project.service.invoice.impl;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import ytb.common.context.service.impl.YtbContext;
import ytb.project.dao.InvoiceInfoProjectMapper;
import ytb.project.model.InvoiceInfoProjectExample;
import ytb.project.model.InvoiceInfoProject;
import ytb.project.service.invoice.InvoiceInfoProjectService;

import java.util.List;

@Service
public class InvoiceInfoProjectServiceimpl implements InvoiceInfoProjectService {
    @Override
    public long countByExample(InvoiceInfoProjectExample example) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        long data = 0;
        try {

            InvoiceInfoProjectMapper mapper = sq.getMapper(InvoiceInfoProjectMapper.class);
            data = mapper.countByExample(example);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int deleteByExample(InvoiceInfoProjectExample example) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceInfoProjectMapper mapper = sq.getMapper(InvoiceInfoProjectMapper.class);
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

            InvoiceInfoProjectMapper mapper = sq.getMapper(InvoiceInfoProjectMapper.class);
            data = mapper.deleteByPrimaryKey(id);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int insert(InvoiceInfoProject record) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceInfoProjectMapper mapper = sq.getMapper(InvoiceInfoProjectMapper.class);
            data = mapper.insert(record);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int insertSelective(InvoiceInfoProject record) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceInfoProjectMapper mapper = sq.getMapper(InvoiceInfoProjectMapper.class);
            data = mapper.insertSelective(record);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public List<InvoiceInfoProject> selectByExample(InvoiceInfoProjectExample example) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        List<InvoiceInfoProject> data = null;
        try {

            InvoiceInfoProjectMapper mapper = sq.getMapper(InvoiceInfoProjectMapper.class);
            data = mapper.selectByExample(example);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public InvoiceInfoProject selectByPrimaryKey(Integer id) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        InvoiceInfoProject data = null;
        try {

            InvoiceInfoProjectMapper mapper = sq.getMapper(InvoiceInfoProjectMapper.class);
            data = mapper.selectByPrimaryKey(id);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int updateByExampleSelective(InvoiceInfoProject record, InvoiceInfoProjectExample example) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceInfoProjectMapper mapper = sq.getMapper(InvoiceInfoProjectMapper.class);
            data = mapper.updateByExampleSelective(record, example);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int updateByExample(InvoiceInfoProject record, InvoiceInfoProjectExample example) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceInfoProjectMapper mapper = sq.getMapper(InvoiceInfoProjectMapper.class);
            data = mapper.updateByExample(record, example);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int updateByPrimaryKeySelective(InvoiceInfoProject record) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceInfoProjectMapper mapper = sq.getMapper(InvoiceInfoProjectMapper.class);
            data = mapper.updateByPrimaryKeySelective(record);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int updateByPrimaryKey(InvoiceInfoProject record) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceInfoProjectMapper mapper = sq.getMapper(InvoiceInfoProjectMapper.class);
            data = mapper.updateByPrimaryKey(record);

        } finally {
            sq.close();
        }
        return data;
    }

}