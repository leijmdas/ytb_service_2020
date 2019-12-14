package ytb.manager.tagtable.service.tagfun;

import ytb.common.ehcache.SysCacheService;
import ytb.common.ytblog.YtbLog;
import ytb.manager.tagtable.model.tagtemplate.Tag_FunctionModel;
import ytb.common.context.model.YtbError;

import java.util.List;

public class TagFunService {
    static String dbName = "ytb_manager";
    static String KEY_TABLENAME = "tag_function";

    static String sql = "select * from  " + dbName + "." + KEY_TABLENAME + " limit 6000";

    public static Tag_FunctionModel findTag_FunctionModel(int functionId) {
        List<Tag_FunctionModel> lst = findTag_FunctionModels();
        for (Tag_FunctionModel m : lst) {
            if (m.getFunctionId()==functionId)   {
                return m;
            }
        }
        throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD,"findTag_FunctionModel");
    }

    public static Tag_FunctionModel findTag_FunctionModel(TagFunRefTagTableParam ref) {
        List<Tag_FunctionModel> lst = findTag_FunctionModels( );
        for (Tag_FunctionModel m : lst) {
            if (m.getTagName().trim().equals(ref.getTagName().trim())
                    && m.getTableName().trim().equals(ref.getTableName().trim())
                    && m.getFieldDisplayName().trim().equals(ref.getFieldDisplayName().trim())) {
                return m;
            }
        }
        return null;
    }

    public static Tag_FunctionModel findTagTableRef_FunctionModel(TagFunRefTagTableParam ref) {
        List<Tag_FunctionModel> lst = findTag_FunctionModels( );
        for (Tag_FunctionModel functionModel : lst) {
            if (functionModel.getTagName().trim().equals(ref.getTagName().trim())
                    && functionModel.getTableName().trim().equals(ref.getTableName().trim()) ) {
                return functionModel;
            }
        }
        YtbLog.logError("findTagTableRef_FunctionModel",ref);
        throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD,"Tag_FunctionModel");

    }

    static List<Tag_FunctionModel> findTag_FunctionModels() {

        return SysCacheService.table2Cache(  KEY_TABLENAME, Tag_FunctionModel.class );

    }

//    static List<Tag_FunctionModel> findTag_FunctionModels(   ) {
//        List<Tag_FunctionModel> vw = YtbContext.getSysCache(KEY_TABLENAME, List.class);
//        if (vw == null) {
//            List<Tag_FunctionModel> lst = findTag_FunctionModels_noCache( );
//            YtbContext.putSysCache(KEY_TABLENAME, lst);
//        }
//        return YtbContext.getSysCache(KEY_TABLENAME, List.class);
//
//    }
//
//    static List<Tag_FunctionModel> findTag_FunctionModels_noCache() {
//        StringBuilder sql = new StringBuilder(128);
//        sql.append("select * from  ").append(dbName);
//        sql.append(".").append(KEY_TABLENAME).append(" limit 6000");
//
//        YtbUtils.testLog(sql);
//        return YtbSql.selectList(sql, Tag_FunctionModel.class);
//    }


}
