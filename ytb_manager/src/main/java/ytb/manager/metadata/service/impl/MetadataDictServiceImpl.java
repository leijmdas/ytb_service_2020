package ytb.manager.metadata.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.common.utils.YtbSql;
import ytb.manager.context.MyBatisUtil;
import ytb.common.context.service.impl.YtbContext;
import ytb.manager.metadata.dao.MetadataDictMapper;
import ytb.manager.metadata.model.MetadataDict;
import ytb.manager.metadata.model.MetadataDictExample;
import ytb.manager.metadata.model.MetadataField;
import ytb.manager.metadata.service.MetadataDictService;
import ytb.common.context.model.YtbError;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.manager.metadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
public class MetadataDictServiceImpl implements MetadataDictService {


    @Override
    public long countByExample(MetadataDictExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(MetadataDictExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer metadataId) {
        SqlSession sq = MyBatisUtil.getSession();

        try {
            MetadataDictMapper metadataDictMapper = sq.getMapper(MetadataDictMapper.class);
            int list = metadataDictMapper.deleteByPrimaryKey(metadataId);
            return list;
        } finally {
            sq.close();

        }

    }

    @Override
    public int insert(MetadataDict record) {
        return 0;
    }

    @Override
    public int insertSelective(MetadataDict record) {
        SqlSession sq = MyBatisUtil.getSession();
        try {
            MetadataDictMapper metadataDictMapper = sq.getMapper(MetadataDictMapper.class);
            metadataDictMapper.insertSelective(record);
            int id=YtbContext.getSqlSessionBuilder().selectAutoID(sq);
            return id;
        } finally {
            sq.close();
        }

    }

    @Override
    public List<MetadataDict> selectByExample(MetadataDictExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        try {
            MetadataDictMapper sysMetaDataFieldDao = sq.getMapper(MetadataDictMapper.class);
            return sysMetaDataFieldDao.selectByExample(example);
        } finally {
            sq.close();
        }
    }

    @Override
    public MetadataDict selectByPrimaryKey(Integer metadataId) {
        SqlSession sq = MyBatisUtil.getSession();
        MetadataDict list = null;
        try {
            MetadataDictMapper metadataDictMapper = sq.getMapper(MetadataDictMapper.class);
            list = metadataDictMapper.selectByPrimaryKey(metadataId);
        } finally {
            sq.close();
        }
        return list;
    }

    @Override
    public int updateByExampleSelective(MetadataDict record, MetadataDictExample example) {
        return 0;
    }

    @Override
    public int updateByExample(MetadataDict record, MetadataDictExample example) {
        SqlSession sq = MyBatisUtil.getSession();

        try {
            MetadataDictMapper metadataDictMapper = sq.getMapper(MetadataDictMapper.class);
            int list = metadataDictMapper.updateByExample(record, example);
            return list;
        } finally {
            sq.close();
        }

    }

    @Override
    public int updateByPrimaryKeySelective(MetadataDict record) {
        SqlSession sq = MyBatisUtil.getSession();
        try {
            MetadataDictMapper metadataDictMapper = sq.getMapper(MetadataDictMapper.class);
            metadataDictMapper.updateByPrimaryKeySelective(record);
            return record.getMetadataId();
        } finally {
            sq.close();
        }

    }

    @Override
    public int updateByPrimaryKey(MetadataDict record) {

        SqlSession sq = MyBatisUtil.getSession();
        int list = 0;
        try {
            MetadataDictMapper metadataDictMapper = sq.getMapper(MetadataDictMapper.class);
            list = metadataDictMapper.updateByPrimaryKey(record);
            return list;
        } finally {
            sq.close();
        }

    }

    public Integer copyMaster(Integer metadataId){
        StringBuilder sql1 = new StringBuilder();
        sql1.append(" select * from ytb_manager.metadata_dict ");
        sql1.append(" where metadata_id= ").append(metadataId);
        MetadataDict md = YtbSql.selectOne(sql1, MetadataDict.class);

        md.setMetadataAlias(md.getMetadataAlias()+System.currentTimeMillis());
        md.setMetadataName(md.getMetadataName()+System.currentTimeMillis());
        md.setMetadataId(null);
        Integer id = this.insertSelective(md);
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from ytb_manager.metadata_field ");
        sql.append(" where metadata_id= ").append(metadataId);
        MetadataFieldServiceImpl mfs = new MetadataFieldServiceImpl();
        md.setMetadataId(metadataId);

        List<MetadataField> lst = YtbSql.selectList(sql, MetadataField.class);
        for (MetadataField mf : lst) {
            mf.setFieldId(null);
            mf.setMetadataId(id);
            mfs.insertSelective(mf);
        }
        return id;
    }

    //SELECT  *  FROM information_schema.TABLES WHERE  TABLE_SCHEMA='ytb_manager'
    public static  boolean checkTableExists(String db, String tbl) {
        StringBuilder sql = new StringBuilder(128);
        sql.append("select 1 from information_schema.TABLES");
        sql.append(" where  TABLE_SCHEMA='").append(db).append("'");
        sql.append(" and table_name='").append(tbl).append("'");
        List lst = YtbSql.selectList(sql);
        return lst.size() > 0;
    }

    public Integer dpMaster(Integer metadataId){
        StringBuilder sql1 = new StringBuilder();
        sql1.append(" select * from ytb_manager.metadata_dict ");
        sql1.append(" where metadata_id= ").append(metadataId);
        MetadataDict md = YtbSql.selectOne(sql1, MetadataDict.class);
        if(!checkTableExists(md.getMetadataDb(),md.getMetadataName())){
            throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD," 表不存在！");
        }

        sql1.delete(0,sql1.length());
        sql1.append(" select 1 from ").append(md.getMetadataDb());
        sql1.append(".").append(md.getMetadataName());
        sql1.append(" limit 1");
        List<Map<String, Object>> lst=YtbSql.selectList(sql1);
        //有记录不能删除的
        if(lst.size()>0) {
            return 0;
        }
        sql1.delete(0,sql1.length());
        sql1.append(" drop table ").append(md.getMetadataDb());
        sql1.append(".").append(md.getMetadataName());
        YtbSql.update(sql1);
        return metadataId;

    }
}
