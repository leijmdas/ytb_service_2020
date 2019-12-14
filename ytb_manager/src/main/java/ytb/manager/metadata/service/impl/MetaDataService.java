package ytb.manager.metadata.service.impl;

import ytb.common.utils.YtbSql;
import ytb.manager.metadata.model.*;
import ytb.common.context.model.YtbError;

import java.util.List;
import java.util.Map;

public class MetaDataService {
    public static MetaDataService getMetaDataService() {
        return metaDataService;
    }
    static MetaDataService metaDataService=new MetaDataService();

    MetadataDictServiceImpl metadataDictService = new MetadataDictServiceImpl();
    MetadataFieldServiceImpl metadataFieldService = new MetadataFieldServiceImpl();


    public List<MetadataDict> getDictTableAndField(String dbName,String metadataName){

        MetadataDictExample metadataDictExample = new MetadataDictExample();
        metadataDictExample.createCriteria().andMetadataNameEqualTo(metadataName).andMetadataDbEqualTo(dbName);
        List<MetadataDict> metadataDicts = metadataDictService.selectByExample(metadataDictExample);
        if (metadataDicts.size() > 0) {
            for (MetadataDict metadataDict : metadataDicts) {
                MetadataFieldExample metadataFieldExample = new MetadataFieldExample();
                metadataFieldExample.createCriteria().andMetadataIdEqualTo(metadataDicts.get(0).getMetadataId());
                metadataFieldExample.setOrderByClause("field_order asc");
                List<MetadataField> sysMetaDataFieldModelList = metadataFieldService.selectByExample(metadataFieldExample);
                metadataDict.setField(sysMetaDataFieldModelList);
            }
        }
        return metadataDicts;
    }

    public MetadataDict getMetadataDictTable(String metadataName) {
        StringBuilder sql = new StringBuilder(128);
        sql.append("select * from ytb_manager.metadata_dict ");
        sql.append(" where metadata_name='").append(metadataName).append("'");
        return YtbSql.selectOne(sql, MetadataDict.class);
    }

    public List<MetadataField> getMetadataFields(int metadataId) {
        StringBuilder sql = new StringBuilder(128);
        sql.append("select * from ytb_manager.metadata_field ");
        sql.append(" where metadata_id='").append(metadataId).append("'");
        sql.append(" order by field_order ");
        return YtbSql.selectList(sql, MetadataField.class);
    }

    public List<MetadataDict> getDictTableAndField(String metadataName) {

        MetadataDictExample metadataDictExample = new MetadataDictExample();
        metadataDictExample.createCriteria().andMetadataNameEqualTo(metadataName);
        List<MetadataDict> metadataDicts = metadataDictService.selectByExample(metadataDictExample);
        if (metadataDicts.size() > 0) {
            MetadataFieldExample metadataFieldExample = new MetadataFieldExample();
            metadataFieldExample.createCriteria().andMetadataIdEqualTo(metadataDicts.get(0).getMetadataId());
            metadataFieldExample.setOrderByClause("field_order asc");
            List<MetadataField> lst = metadataFieldService.selectByExample(metadataFieldExample);
            metadataDicts.get(0).setField(lst);
            return metadataDicts;
        }
        throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);

    }

    public List<MetadataDict> getDictTableAndField(int metadataId) {

        MetadataDictExample metadataDictExample = new MetadataDictExample();
        metadataDictExample.createCriteria().andMetadataIdEqualTo(metadataId);
        List<MetadataDict> metadataDicts = metadataDictService.selectByExample(metadataDictExample);
        if (metadataDicts.size() > 0) {
            for (MetadataDict metadataDict : metadataDicts) {
                MetadataFieldExample metadataFieldExample = new MetadataFieldExample();
                metadataFieldExample.createCriteria().andMetadataIdEqualTo(metadataDicts.get(0).getMetadataId());
                metadataFieldExample.setOrderByClause("field_order asc");
                List<MetadataField> sysMetaDataFieldModelList = metadataFieldService.selectByExample(metadataFieldExample);
                metadataDict.setField(sysMetaDataFieldModelList);
            }
        }
        return metadataDicts;
    }

    public List<Map<String, Object>> selectByTable(SelectSql selectSql) {

        return selectByTable(selectSql,"*");

    }


    public List<Map<String, Object>> selectByTable(SelectSql selectSql,String fields) {

        StringBuilder sql = new StringBuilder(152);
        sql.append(" select ").append(fields);
        sql.append(" from  ").append(selectSql.getTable());
        if (selectSql.getsWhere() != null && !selectSql.getsWhere().isEmpty()) {
            sql.append(" where  ").append(selectSql.getsWhere());
        }
        if (selectSql.getOrderBy() != null && !selectSql.getOrderBy().isEmpty()) {
            sql.append(" order by ").append(selectSql.getOrderBy()).append(" asc ");
        }
        return YtbSql.selectList(sql);

    }
}
