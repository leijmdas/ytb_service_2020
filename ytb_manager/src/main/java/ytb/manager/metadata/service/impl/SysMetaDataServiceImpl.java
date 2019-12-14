package ytb.manager.metadata.service.impl;

import com.github.abel533.sql.SqlMapper;
import org.apache.ibatis.session.SqlSession;
import ytb.common.utils.YtbSql;
import ytb.manager.context.MyBatisUtil;
import ytb.manager.metadata.dao.*;
import ytb.manager.metadata.model.*;
import ytb.manager.metadata.service.SysMetaDataService;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static ytb.manager.metadata.service.impl.MetadataDictServiceImpl.checkTableExists;

/**
 * Package: ytb.manager.metadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
public class SysMetaDataServiceImpl implements SysMetaDataService {


    /*
            @Override
            public List<Sys_MetaDataDictModel> getDictList() {
                SqlSession sq = MyBatisUtil.getSession();
                List<Sys_MetaDataDictModel> list;
                try {
                    SysMetaDataDictMapper sysMetaDataDictDao = sq.getMapper(SysMetaDataDictMapper.class);
                    list = sysMetaDataDictDao.getDictList();
                } finally {
                    sq.close();
                }
                return list;
            }*/
/*
    @Override
    public List<Sys_MetaDataDictModel> getDictListByType(int metaDataType) {
        SqlSession sq = MyBatisUtil.getSession();
        List<Sys_MetaDataDictModel> list;
        try {
            SysMetaDataDictMapper sysMetaDataDictDao = sq.getMapper(SysMetaDataDictMapper.class);
            list = sysMetaDataDictDao.getDictListByType(metaDataType);
        } finally {
            sq.close();
        }
        return list;
    }*/
/*
    @Override
    public Sys_MetaDataDictModel getDictById(int metaDataId) {
        SqlSession sq = MyBatisUtil.getSession();
        SysMetaDataDictMapper sysMetaDataDictDao = sq.getMapper(SysMetaDataDictMapper.class);
        Sys_MetaDataDictModel sysMetaDataDictModel = sysMetaDataDictDao.getDictById(metaDataId);
        sq.close();
        return sysMetaDataDictModel;
    }*/
/*
    @Override
    public void updateDictById(Sys_MetaDataDictModel sysMetaDataDictModel) {
        SqlSession sq = MyBatisUtil.getSession();
        SysMetaDataDictMapper sysMetaDataDictDao = sq.getMapper(SysMetaDataDictMapper.class);

        try {
            sysMetaDataDictDao.updateDictById(sysMetaDataDictModel);
            sq.commit();
        } finally {
            sq.close();
        }
    }*/
/*
    @Override
    public void addDict(Sys_MetaDataDictModel sysMetaDataDictModel) {
        SqlSession sq = MyBatisUtil.getSession();
        SysMetaDataDictMapper sysMetaDataDictDao = sq.getMapper(SysMetaDataDictMapper.class);

        try {
            sysMetaDataDictDao.addDict(sysMetaDataDictModel);
            sq.commit();
        } finally {
            sq.close();
        }
    }
*/
    @Override
    public void deleteDictById(int metaDataId) {
        SqlSession sq = MyBatisUtil.getSession();
        SysMetaDataDictMapper sysMetaDataDictDao = sq.getMapper(SysMetaDataDictMapper.class);

        try {
            sysMetaDataDictDao.deleteDictById(metaDataId);
            sq.commit();
        } finally {
            sq.close();
        }
    }

    public MetadataField selectByPrimaryKey(Integer metaDataId) {
        SqlSession sq = MyBatisUtil.getSession();

        MetadataFieldMapper sysMetaDataFieldDao = sq.getMapper(MetadataFieldMapper.class);
        MetadataField field = sysMetaDataFieldDao.selectByPrimaryKey(metaDataId);
        sq.close();
        return field;
    }


    public List<MetadataDict> selectByExample(MetadataDictExample getDictMetadataName) {
        SqlSession sq = MyBatisUtil.getSession();
        MetadataDictMapper sysMetaDataFieldDao = sq.getMapper(MetadataDictMapper.class);
        List<MetadataDict> metadataDicts = sysMetaDataFieldDao.selectByExample(getDictMetadataName);
        sq.close();
        return metadataDicts;
    }


    @Override
    public void deleteFieldById(int fieldId) {
        SqlSession sq = MyBatisUtil.getSession();
        SysMetaDataFieldMapper sysMetaDataFieldDao = sq.getMapper(SysMetaDataFieldMapper.class);

        try {
            sysMetaDataFieldDao.deleteFieldById(fieldId);
            sq.commit();
        } finally {
            sq.close();
        }
    }


    @Override
    public List<Sys_DictDataTypeModel> getDictDataTypeList() {
        SqlSession sq = MyBatisUtil.getSession();
        List list;
        try {
            SysDictDataTypeMapper sysDictDataTypeDao = sq.getMapper(SysDictDataTypeMapper.class);
            list = sysDictDataTypeDao.getDictDataTypeList();
        } finally {
            sq.close();
        }
        return list;
    }

    @Override
    public void addDictDataType(Sys_DictDataTypeModel sysDictDataTypeModel) {
        SqlSession sq = MyBatisUtil.getSession();
        SysDictDataTypeMapper sysDictDataTypeDao = sq.getMapper(SysDictDataTypeMapper.class);
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            String createTime = df.format(now);
            sysDictDataTypeModel.setCreateTime(createTime);
            sysDictDataTypeDao.addDictDataType(sysDictDataTypeModel);
            sq.commit();
        } finally {
            sq.close();
        }
    }

    @Override
    public void updateDictDataTypeById(Sys_DictDataTypeModel sysDictDataTypeModel) {
        SqlSession sq = MyBatisUtil.getSession();
        SysDictDataTypeMapper sysDictDataTypeDao = sq.getMapper(SysDictDataTypeMapper.class);

        try {
            sysDictDataTypeDao.updateDictDataTypeById(sysDictDataTypeModel);
            sq.commit();
        } finally {
            sq.close();
        }
    }

    @Override
    public void deleteDictDataTypeById(int dataInnerId) {
        SqlSession sq = MyBatisUtil.getSession();
        SysDictDataTypeMapper sysDictDataTypeDao = sq.getMapper(SysDictDataTypeMapper.class);

        try {
            sysDictDataTypeDao.deleteDictDataTypeById(dataInnerId);
            sq.commit();
        } finally {
            sq.close();
        }
    }

    @Override
    public List<Map<String, Object>> getTree(int typeId) {
        SqlSession sq = MyBatisUtil.getSession();
        List<Map<String, Object>> list;
        try {
            SysDictDataTypeMapper sysDictDataTypeDao = sq.getMapper(SysDictDataTypeMapper.class);
            list = sysDictDataTypeDao.getTree(typeId);
        } finally {
            sq.close();
        }
        return list;
    }


    @Override
    public int getTotalCount() {
        SqlSession sq = MyBatisUtil.getSession();
        int i = 0;
        try {
            SysMetaDataDictMapper sysMetaDataDictDao = sq.getMapper(SysMetaDataDictMapper.class);
            i = sysMetaDataDictDao.getTotalCount();
        } finally {
            sq.close();
        }
        return i;
    }

    boolean isDate(String dtype) {
        return dtype.equals("DATE")
                || dtype.equals("TIMESTAMP") || dtype.equalsIgnoreCase("DATETIME");
    }

    boolean isString(String dtype) {
        return dtype.equals("CHAR")
                || dtype.equalsIgnoreCase("VARCHAR");
    }

    boolean isDecimal(String dtype) {
        return dtype.equals("DECIMAL");

    }

    boolean isTAGIMAGE(String dtype) {
        return dtype.equals("TAGIMAGE");

    }


    boolean isPCT(String dtype) {
        return dtype.equals("PCT");

    }

    boolean isMoney (String dtype) {
        return dtype.equals("MONEY");

    }
    boolean isBlobText(String dt) {
        return dt.equals("BLOB")
                || dt.equals("MEDIUMBLOB")
                || dt.equals("TEXT");

    }

    @Override
    public MsgResponse makeTableByDictId(Integer metadataId, MsgRequest req, RestHandler handler) {
        int retcode = 0;
        String retmsg = "成功";
        String msgBody = "";

        if (metadataId == null) {
            throw new YtbError(YtbError.CODE_PARAMETER_IS_WRONG);
        }
        MetadataDictServiceImpl metadataDictService = new MetadataDictServiceImpl();
        MetadataDict metadataDict = metadataDictService.selectByPrimaryKey(metadataId);
        if (metadataDict == null) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR,"字典未定义");
        }

        if(checkTableExists(metadataDict.getMetadataDb(),metadataDict.getMetadataName())){
            throw new YtbError(YtbError.CODE_DEFINE_ERROR," 表已经存在！");
        }

        MetadataFieldServiceImpl metadataFieldService = new MetadataFieldServiceImpl();
        MetadataFieldExample metadataFieldExampleKey = new MetadataFieldExample();
        MetadataFieldExample.Criteria fieldExampleCriteriaKey = metadataFieldExampleKey.createCriteria();
        fieldExampleCriteriaKey.andFieldPkEqualTo(true);
        fieldExampleCriteriaKey.andMetadataIdEqualTo(metadataId);
        List<MetadataField> fieldListKey = metadataFieldService.selectByExample(metadataFieldExampleKey);
        MetadataFieldExample metadataFieldExample = new MetadataFieldExample();
        MetadataFieldExample.Criteria fieldExampleCriteria = metadataFieldExample.createCriteria();
        fieldExampleCriteria.andMetadataIdEqualTo(metadataId);
        metadataFieldExample.setOrderByClause("field_pk desc,field_order asc");
        List<MetadataField> fieldList = metadataFieldService.selectByExample(metadataFieldExample);

        //构造创建表的sql语句
        StringBuilder sql = new StringBuilder();
        sql.append("create table if not exists ");
        sql.append(metadataDict.getMetadataDb()).append(".").append(metadataDict.getMetadataName());
        sql.append("(");
        for (int i = 0; i < fieldList.size(); i++) {
            sql.append(fieldList.get(i).getFieldName()).append(" ");
            if (isPCT(fieldList.get(i).getFieldType())
             || isMoney(fieldList.get(i).getFieldType())) {
                sql.append(" DECIMAL ");
            }
            if (isTAGIMAGE(fieldList.get(i).getFieldType())
                    || isMoney(fieldList.get(i).getFieldType())) {
                sql.append(" INT ");
            }

            else {
                sql.append(fieldList.get(i).getFieldType());
            }

            if (isString(fieldList.get(i).getFieldType())) {
                sql.append("(").append(fieldList.get(i).getFieldSize()).append(") ");
            } else if (isDecimal(fieldList.get(i).getFieldType())) {
                sql.append("(").append(fieldList.get(i).getFieldSize()).append(",2) ");
            }
            else if (isPCT(fieldList.get(i).getFieldType())) {
                sql.append("(8,4) ");
            }
            else if (isMoney(fieldList.get(i).getFieldType())) {
                sql.append("(12,2) ");
            }


            if (fieldList.get(i).getFieldPk() != null && fieldList.get(i).getFieldPk()) {
                if (fieldList.get(i).getFieldPk() == true) {
                    sql.append(" primary key NOT NULL ");
                }
                if (fieldList.get(i).getFieldAuto() == true) {
                    sql.append("AUTO_INCREMENT ");
                }
            } else {
                if (fieldList.get(i).getFieldIsnull()) {
                    sql.append("  NULL ");
                } else {
                    sql.append("  NOT NULL ");
                }

                if (!isBlobText(fieldList.get(i).getFieldType()) && fieldList.get(i).getFieldDefault() != null && !fieldList.get(i).getFieldDefault().isEmpty()) {
                    sql.append(" DEFAULT   ");
                    if (isString(fieldList.get(i).getFieldType())) {
                        sql.append("'").append(fieldList.get(i).getFieldDefault()).append("'");
                    } else if (isDate(fieldList.get(i).getFieldType())) {
                        if (fieldList.get(i).getFieldDefault() != null
                                && fieldList.get(i).getFieldDefault().length() >= 10) {
                            sql.append("'").append(fieldList.get(i).getFieldDefault()).append("'");
                        }else{
                            sql.append(fieldList.get(i).getFieldDefault());

                        }
                    } else {
                        sql.append(fieldList.get(i).getFieldDefault());
                    }
                }
            }
            sql.append(" comment '").append(fieldList.get(i).getFieldMemo()).append("' ,");
        }

        sql.deleteCharAt(sql.length() - 1);
        sql.append(")" + "ENGINE=Innodb DEFAULT CHARSET=UTF8 COLLATE UTF8_BIN;");

        YtbSql.update(sql);
        msgBody = "{'sql': 'make Table OK'}";
        return handler.buildMsg(retcode, retmsg, msgBody);
    }

    @Override
    public void deleteFieldsByDictId(int metaDataId) {
        SqlSession sq = MyBatisUtil.getSession();
        try {
            SqlMapper m = new SqlMapper(sq);
            m.delete("delete from metadata_field where metadata_id =" + metaDataId);
        } finally {
            sq.close();
        }
    }

    @Override
    public List<Map<String, Object>> selectByTable(SelectSql selectSql) {

        StringBuilder sql = new StringBuilder(152);
        sql.append(" select * from  ").append(selectSql.getTable());
        if (selectSql.getsWhere() != null && !selectSql.getsWhere().isEmpty()) {
            sql.append(" where  ").append(selectSql.getsWhere());
        }
        if (selectSql.getOrderBy() != null && !selectSql.getOrderBy().isEmpty()) {
            sql.append(" order by ").append(selectSql.getOrderBy()).append(" asc ");
        }
        return YtbSql.selectList(sql);

    }

    @Override
    public List<Map<String, Object>> selectByTableLimit(SelectSql selectSql) {
        SqlSession ss = MyBatisUtil.getSession();
        try {
            SqlMapper m = new SqlMapper(ss);
            StringBuilder sql=new StringBuilder(256);
            sql.append(" select * from " ).append( selectSql.getTable() );
            if( selectSql.getsWhere()!=null ){
                sql.append(" where ").append(selectSql.getsWhere());
            }
            sql.append( "  limit " ).append(selectSql.getLimitFirstIndex() );
            sql.append( ",").append( selectSql.getLimitpageSize() );
            List<Map<String, Object>> data = m.selectList(sql.toString());
            return data;
        } finally {
            ss.close();
        }

    }

    @Override
    public List<Map<String, Object>> selectByTableLimitOrderBy(SelectSql selectSql) {
        SqlSession sq = MyBatisUtil.getSession();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select * from ").append(selectSql.getTable());
            if( selectSql.getsWhere()!=null ){
                sql.append(" where ").append(selectSql.getsWhere());
            }
            if(!selectSql.buildOrderby().isEmpty()) {
                sql.append(" order by ").append(selectSql.buildOrderby());
            }
            sql.append("  limit ").append(selectSql.getLimitFirstIndex());
            sql.append(",").append(selectSql.getLimitpageSize());
            //System.out.println(sql);
            SqlMapper m = new SqlMapper(sq);
            return m.selectList(sql.toString());
        } finally {
            sq.close();
        }

    }


    public List<SubsysDict> getSubSysDictList() {

        SqlSession sq = MyBatisUtil.getSession();
        try {
            SubsysDictMapper sqMapper = sq.getMapper(SubsysDictMapper.class);
            return sqMapper.getSubSysDictList();
        } finally {
            sq.close();
        }

    }



}
