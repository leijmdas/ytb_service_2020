package ytb.manager.metadata.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.manager.context.MyBatisUtil;
import ytb.manager.metadata.dao.*;
import ytb.manager.metadata.model.*;
import ytb.manager.metadata.service.MetadataFieldService;

import java.util.List;

/**
 * Package: ytb.manager.metadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
public class MetadataFieldServiceImpl implements MetadataFieldService {


    @Override
    public long countByExample(MetadataFieldExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(MetadataFieldExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        int list = 0;
        try {
            MetadataFieldMapper metadataFieldMapper = sq.getMapper(MetadataFieldMapper.class);
            list = metadataFieldMapper.deleteByExample(example);
            return list;
        } finally {
            sq.close();
        }

    }

    @Override
    public int deleteByPrimaryKey(Integer fieldId) {
        SqlSession sq = MyBatisUtil.getSession();
        int list = 0;
        try {
            MetadataFieldMapper metadataFieldMapper = sq.getMapper(MetadataFieldMapper.class);
            list = metadataFieldMapper.deleteByPrimaryKey(fieldId);
            return list;
        } finally {
            sq.close();
        }

    }

    @Override
    public int insert(MetadataField record) {
        return 0;
    }

    @Override
    public int insertSelective(MetadataField record) {
        SqlSession sq = MyBatisUtil.getSession();
        int list = 0;
        try {
            MetadataFieldMapper metadataFieldMapper = sq.getMapper(MetadataFieldMapper.class);
            list = metadataFieldMapper.insertSelective(record);
            return list;
        } finally {
            sq.close();
        }

    }

    @Override
    public List<MetadataField> selectByExample(MetadataFieldExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        try {
            MetadataFieldMapper sysMetaDataFieldDao = sq.getMapper(MetadataFieldMapper.class);
            List<MetadataField> sysMetaDataFieldModel = sysMetaDataFieldDao.selectByExample(example);
            return sysMetaDataFieldModel;
        } finally {
            sq.close();
        }
    }



    @Override
    public MetadataField selectByPrimaryKey(Integer fieldId) {
        SqlSession sq = MyBatisUtil.getSession();
        MetadataField list = null;
        try {
            MetadataFieldMapper metadataFieldMapper = sq.getMapper(MetadataFieldMapper.class);
            list = metadataFieldMapper.selectByPrimaryKey(fieldId);
            return list;
        } finally {
            sq.close();
        }

    }

    @Override
    public int updateByExampleSelective(MetadataField record, MetadataFieldExample example) {
        return 0;
    }

    @Override
    public int updateByExample(MetadataField record, MetadataFieldExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        int list = 0;
        try {
            MetadataFieldMapper metadataFieldMapper = sq.getMapper(MetadataFieldMapper.class);
            list = metadataFieldMapper.updateByExample(record, example);
        } finally {
            sq.close();
        }
        return list;
    }

    @Override
    public int updateByPrimaryKeySelective(MetadataField record) {
        SqlSession sq = MyBatisUtil.getSession();
        int list = 0;
        try {
            MetadataFieldMapper metadataFieldMapper = sq.getMapper(MetadataFieldMapper.class);
            list = metadataFieldMapper.updateByPrimaryKeySelective(record);
            return list;
        } finally {
            sq.close();
        }

    }

    @Override
    public int updateByPrimaryKey(MetadataField record) {
        SqlSession sq = MyBatisUtil.getSession();
        int list = 0;
        try {
            MetadataFieldMapper metadataFieldMapper = sq.getMapper(MetadataFieldMapper.class);
            list = metadataFieldMapper.updateByPrimaryKeySelective(record);
            return list;
        } finally {
            sq.close();
        }

    }
}
