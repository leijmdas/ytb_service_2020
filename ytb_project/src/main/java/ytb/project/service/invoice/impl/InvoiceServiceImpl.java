package ytb.project.service.invoice.impl;

import org.apache.ibatis.session.SqlSession;

import ytb.common.context.service.impl.YtbContext;
import ytb.project.dao.InvoiceInfoMapper;
import ytb.project.dao.InvoiceInfoProjectMapper;
import ytb.project.dao.InvoiceMapper;
import ytb.project.model.*;
import ytb.project.service.invoice.InvoiceService;


import java.util.ArrayList;
import java.util.List;

/**
 * Copyright@ vipcchua.github.io
 * Author:Cchua
 * Date:2019/1/22
 */
public class InvoiceServiceImpl implements InvoiceService {
    @Override
    public long countByExample(InvoiceExample example) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        long data = 0;
        try {

            InvoiceMapper mapper = sq.getMapper(InvoiceMapper.class);
            data = mapper.countByExample(example);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int deleteByExample(InvoiceExample example) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceMapper mapper = sq.getMapper(InvoiceMapper.class);
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

            InvoiceMapper mapper = sq.getMapper(InvoiceMapper.class);
            data = mapper.deleteByPrimaryKey(id);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int insert(Invoice record) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceMapper mapper = sq.getMapper(InvoiceMapper.class);
            data = mapper.insert(record);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int insertSelective(Invoice record) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceMapper mapper = sq.getMapper(InvoiceMapper.class);
            data = mapper.insertSelective(record);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public Boolean insertAllSelective(Invoice record, List<InvoiceInfo> list, InvoiceInfoProject project) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(false);
        int data = 0;


        try {

            InvoiceMapper mapper = sq.getMapper(InvoiceMapper.class);
            InvoiceInfoMapper infoMapper = sq.getMapper(InvoiceInfoMapper.class);
            data = mapper.insertSelective(record);


            InvoiceInfoProjectMapper projectMapper = sq.getMapper(InvoiceInfoProjectMapper.class);

            project.setInvoiceId(record.getId());
            data = projectMapper.insertSelective(project);

            if (data > 0) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setInvoiceId(record.getId());
                    data = infoMapper.insertSelective(list.get(i));
                    if (data > 0) {
                    } else {
                        sq.rollback();
                        return false;
                    }

                }
                sq.commit();
                return true;


            } else {
                sq.rollback();
            }


            data = mapper.insertSelective(record);

        } finally {
            sq.close();
        }
        return false;
    }

    @Override
    public List<Invoice> selectByExample(InvoiceExample example) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        List<Invoice> data = new ArrayList<>();
        try {

            InvoiceMapper mapper = sq.getMapper(InvoiceMapper.class);
            data = mapper.selectByExample(example);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public InvoicePojo selectInvoiceAllByKey(Integer id) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        InvoicePojo data = new InvoicePojo();
        try {
            InvoiceInfoProjectExample projectExample = new InvoiceInfoProjectExample();
            InvoiceMapper mapper = sq.getMapper(InvoiceMapper.class);
            Invoice a =
                    mapper.selectByPrimaryKey(id);


            if (a != null) {
                data.setInvoice(a);
                InvoiceInfoMapper infoMapper = sq.getMapper(InvoiceInfoMapper.class);
                InvoiceInfoExample example = new InvoiceInfoExample();
                example.createCriteria().andInvoiceIdEqualTo(a.getId());
                List<InvoiceInfo> list = infoMapper.selectByExample(example);
                if (list.size() > 0) {
                    data.setInvoiceInfos(list);
                }

                InvoiceInfoProjectMapper projectMapper = sq.getMapper(InvoiceInfoProjectMapper.class);
                projectExample.createCriteria().andInvoiceIdEqualTo(id);
                List<InvoiceInfoProject> bb = projectMapper.selectByExample(projectExample);
                if (bb.size() > 0) {
                    data.setInvoiceInfoProject(bb.get(0));
                }


            } else {
                return null;
            }


        } finally {
            sq.close();
        }
        return data;
    }


    @Override
    public Invoice selectByPrimaryKey(Integer id) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        Invoice data = new Invoice();
        try {

            InvoiceMapper mapper = sq.getMapper(InvoiceMapper.class);
            data = mapper.selectByPrimaryKey(id);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int updateByExampleSelective(Invoice record, InvoiceExample example) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceMapper mapper = sq.getMapper(InvoiceMapper.class);
            data = mapper.updateByExampleSelective(record, example);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int updateByExample(Invoice record, InvoiceExample example) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceMapper mapper = sq.getMapper(InvoiceMapper.class);
            data = mapper.updateByExample(record, example);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int updateByPrimaryKeySelective(Invoice record) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceMapper mapper = sq.getMapper(InvoiceMapper.class);
            data = mapper.updateByPrimaryKeySelective(record);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public int updateByPrimaryKey(Invoice record) {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        int data = 0;
        try {

            InvoiceMapper mapper = sq.getMapper(InvoiceMapper.class);
            data = mapper.updateByPrimaryKey(record);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public List<Invoice> selectAll() {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        List<Invoice> data = new ArrayList<>();
        try {

            InvoiceMapper mapper = sq.getMapper(InvoiceMapper.class);
            InvoiceExample example = new InvoiceExample();
            data = mapper.selectByExample(example);

        } finally {
            sq.close();
        }
        return data;
    }

    @Override
    public List<InvoicePojo> selectAllAndInfo() {
        SqlSession sq = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
        List<InvoicePojo> data = new ArrayList<>();
        List<Invoice> asd;
        try {

            InvoiceMapper mapper = sq.getMapper(InvoiceMapper.class);
            InvoiceExample example = new InvoiceExample();
            asd = mapper.selectByExample(example);
            InvoiceInfoExample infoExample = new InvoiceInfoExample();

            InvoiceInfoProjectMapper projectMapper = sq.getMapper(InvoiceInfoProjectMapper.class);
            InvoiceInfoProjectExample projectExample = new InvoiceInfoProjectExample();


            if (asd.size() > 0) {

                for (int i = 0; i < asd.size(); i++) {
                    InvoicePojo pojo = new InvoicePojo();
                    pojo.setInvoice(asd.get(i));
                    InvoiceInfoMapper infoMapper = sq.getMapper(InvoiceInfoMapper.class);
                    infoExample.createCriteria().andInvoiceIdEqualTo(asd.get(i).getId());

                    List<InvoiceInfo> list = infoMapper.selectByExample(infoExample);
                    if (list.size() > 0) {
                        pojo.setInvoiceInfos(list);
                    }

                    data.add(pojo);


                    projectExample.createCriteria().andInvoiceIdEqualTo(asd.get(i).getId());
                    List<InvoiceInfoProject> bb = projectMapper.selectByExample(projectExample);
                    if (bb.size() > 0) {
                        pojo.setInvoiceInfoProject(bb.get(0));
                    }


                }

            }

        } finally {
            sq.close();
        }
        return data;
    }
}
