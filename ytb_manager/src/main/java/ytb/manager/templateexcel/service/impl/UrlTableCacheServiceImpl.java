package ytb.manager.templateexcel.service.impl;

import ytb.common.utils.YtbSql;
import ytb.manager.template.model.DictRestRefModel;
import ytb.manager.templateexcel.service.UrlTableCacheService;
import ytb.common.context.model.YtbError;

/**
 * @ClassName UrlTableCacheServiceImpl
 * @Description TODO
 * @Author qsy
 * @Date 2019/4/23 16:19
 **/
public class UrlTableCacheServiceImpl implements UrlTableCacheService {

    /**
     * @Author qsy
     * @Description //insertUrlReturnId  插入URL 返回 refId
     * @Date 17:56 2019/4/24
     * @Param [dictRestRefModel]
     * @return java.lang.Short
     **/
    @Override
    public  Short insertUrlReturnId(DictRestRefModel dictRestRefModel) {
        insertUrl(dictRestRefModel);
        DictRestRefModel dictRestRef = getDictRestRefByUrl(dictRestRefModel.getRefPath());
        if(dictRestRef == null){
            throw new YtbError(YtbError.CODE_NULL);
        }
        return dictRestRef.getRefId();
    }

    /**
     * @Author qsy
     * @Description 插入url
     * @Date 18:26 2019/4/24
     * @Param [dictRestRefModel]
     * @return int
     **/
    public  int insertUrl(DictRestRefModel dictRestRefModel){
        StringBuilder sql = new StringBuilder();
        sql.append("insert into dict_rest_ref(ref_path,ref_param) values('"+dictRestRefModel.getRefPath()+"','"+dictRestRefModel.getRefParam()+"')");
        return YtbSql.insert(sql,null);
    }
    /**
     * @Author qsy
     * @Description getDictRestRefById  获取DictRestRef 对象
     * @Date 17:22 2019/4/24
     * @Param [refId]
     * @return ytb.manager.template.model.DictRestRefModel
     **/
    @Override
    public DictRestRefModel getDictRestRefById(Short refId){
        StringBuilder sql = new StringBuilder();
        sql.append("select  *  from  ytb_manager.dict_rest_ref  where  ref_id=" + refId);
        return YtbSql.selectOne(sql,DictRestRefModel.class);
    }

    /**
     * @Author qsy
     * @Description getDictRestRefByUrl  获取DictRestRef 对象
     * @Date 17:22 2019/4/24
     * @Param [url]
     * @return ytb.manager.template.model.DictRestRefModel
     **/
    public  DictRestRefModel getDictRestRefByUrl(String url){
        StringBuilder sql = new StringBuilder();
        sql.append("select  *  from  ytb_manager.dict_rest_ref  where  ref_path='" + url + "'");
        return YtbSql.selectOne(sql,DictRestRefModel.class);
    }
}
