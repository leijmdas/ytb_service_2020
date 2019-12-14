package ytb.common.utils.CodeFactory;

import org.apache.commons.io.FileUtils;
import ytb.common.utils.YtbSql;
import ytb.common.ytblog.YtbLog;
import ytb.common.context.model.Ytb_Model;

import java.io.File;
import java.io.IOException;
import java.util.List;
/*create procedure spGetDBFieldsList0(IN pTablename varchar(32))
        BEGIN
        select TABLE_SCHEMA
        from information_schema.TABLES
        where TABLE_NAME=pTablename
        into @db;
    select @db as db,COLUMN_NAME as field_name, DATA_TYPE as field_type
        from information_schema.COLUMNS
        where table_name =pTablename;

        END;

        call spGetDBFieldsList("project");*/
public class ModelFactory {

    public static class DBFieldInfo extends Ytb_Model {
        public String getDb() {
            return db;
        }

        public void setDb(String db) {
            this.db = db;
        }

        String db;
        String fieldName;
        String fieldType;

        public String getPk() {
            return pk;
        }

        public void setPk(String pk) {
            this.pk = pk;
        }

        String pk;

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldType() {
            return fieldType;
        }

        public void setFieldType(String fieldType) {
            this.fieldType = fieldType;
        }


    }

    public static List<DBFieldInfo> spGetDBFieldsList(String tableName) {
        //tableName=StringUtils.quote(tableName);
        List<DBFieldInfo> l = YtbSql.spDb(DBFieldInfo.class, "ytb_manager.spGetDBFieldsList", tableName);

        return l;
    }

    //build Rest build RestProcess
    static void buildRest(String cname) throws IOException {
        String path = ModelFactory.class.getResource("/modelTemplate/RestServiceDemo.java").getPath();
        String r = FileUtils.readFileToString(new File(path), "UTF-8");
        r = r.replaceAll("RestServiceDemo", "RestService" + cname);
        r = r.replaceAll("RestProcess", "RestProcess" + cname);
        r = r.replaceAll("demoModel", cname);

        FileUtils.writeStringToFile(new File(FactoryConst.restPath + "RestService" + cname + ".java"),
                r, "UTF-8");


        String pathrs = ModelFactory.class.getResource("/modelTemplate/RestProcess.java").getPath();
        String rs = FileUtils.readFileToString(new File(pathrs),"UTF-8");
        rs = rs.replaceAll("RestProcess", "RestProcess" + cname);
        rs = rs.replaceAll("ModelTemplate", cname );
        rs = rs.replaceAll("ModelServiceImpl", cname+"ServiceImpl");

        FileUtils.writeStringToFile(new File(FactoryConst.restPath + "impl/RestProcess" + cname+".java"), rs,"UTF-8");

    }

    public static void build(String tableName) throws IOException {
        List<DBFieldInfo> l = spGetDBFieldsList(tableName);
        ModelTemplate mt = new ModelTemplate();
        String cname = mt.build(l, tableName);
        YtbLog.logDebug(ServiceFactory.buildIService(mt));
        YtbLog.logDebug(ServiceFactory.build(mt));
        buildRest(cname);
    }
}
