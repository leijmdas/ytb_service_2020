package ytb.common.utils.CodeFactory;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ModelTemplate {
     String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    String className;
    String db;
    String pk;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public Map<String, String> getMap_field2member() {
        return map_field2member;
    }

    public void setMap_field2member(Map<String, String> map_field2member) {
        this.map_field2member = map_field2member;
    }
    private static final char UNDERLINE='_';
    public static String toUpperFirst(String fn) {
        return fn.substring(0, 1).toUpperCase() + fn.substring(1);

    }
    public static String toLowerFirst(String fn) {
        return fn.substring(0, 1).toLowerCase() + fn.substring(1);

    }
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = Character.toLowerCase(param.charAt(i));
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    Map<String, String> map_field2member = new LinkedHashMap<>();

    static Map<String, String> map_db2java = new HashMap<>();

    static {
        map_db2java.put("bit", "Boolean");
        map_db2java.put("tinyint", "Byte");
        map_db2java.put("smallint", "Short");
        map_db2java.put("int", "Integer");
        map_db2java.put("bigint", "Long");
        map_db2java.put("char", "String");
        map_db2java.put("varchar", "String");
        map_db2java.put("blob", "byte[]");
        map_db2java.put("mediumblob", "byte[]");
        map_db2java.put("longblob", "byte[]");
        map_db2java.put("decimal", "BigDecimal");
        map_db2java.put("date", "Date");
        map_db2java.put("datetime", "Timestamp");
        map_db2java.put("timestamp", "Timestamp");

    }

    static String findDb2Java(String t) {
        if (map_db2java.containsKey(t)) {
            return map_db2java.get(t);
        }
        return "String";
    }



    StringBuilder  buildGet(ModelFactory.DBFieldInfo f){
        StringBuilder s=new StringBuilder(128);
        s.append("\tpublic ").append(findDb2Java(f.fieldType)).append( " ");

        String fn=underlineToCamel(f.getFieldName());
        fn = toUpperFirst(fn);
        s.append(" get").append(fn).append("() {\n");
         s.append("\t\treturn ").append(underlineToCamel(f.getFieldName())).append(";\n");
        s.append("\t}\n\n");
        return s;

    }

    StringBuilder  buildSet(ModelFactory.DBFieldInfo f){
        StringBuilder s=new StringBuilder(128);
        s.append("\tpublic ").append(className);
        String fn=underlineToCamel(f.getFieldName());
        fn = toUpperFirst(fn);
        String fnL=toLowerFirst(fn);
        s.append(" set").append(fn).append("( ").append(findDb2Java(f.fieldType));
        s.append(" ").append(fnL).append(" ) {\n");
        s.append("\t\tthis.").append(fnL).append(" = ").append(fnL).append(";\n");
        s.append("\t\treturn this;\n");
        s.append("");
        s.append("\t}\n\n");
        return s;
    }

    String header ="public class ModelTemplate  extends Ytb_Model {\n";
    String footer ="}\n";
    StringBuilder body=new StringBuilder(1024);

    //return clsname
    String build(List<ModelFactory.DBFieldInfo> lst, String pTableName) throws IOException {
        this.tableName = pTableName.replaceAll("'", "");
        className = underlineToCamel(pTableName);
        className = toUpperFirst(className + "Model");
        for (ModelFactory.DBFieldInfo d : lst) {
            map_field2member.put(d.getFieldName(),underlineToCamel(d.getFieldName()));
            if (d.getPk().equals("PRI")) {
                pk = d.getFieldName();
            }
            db = d.getDb();
            body.append("\tprivate ").append(findDb2Java(d.fieldType)).append(" ");
            body.append(underlineToCamel(d.getFieldName())).append(";\n");
            //body.append(buildGet(d));
            body.append("\n");
        }
        for(ModelFactory.DBFieldInfo d:lst){
            //body.append("\t").append(findDb2Java(d.fieldType)).append(" ");
            //body.append(underlineToCamel(d.getFieldName())).append(";\n");
            body.append(buildGet(d));
            body.append(buildSet(d));
        }
        StringBuilder c=new StringBuilder(1024);
        c.append("import ytb.common.context.model.Ytb_Model;\n");
        c.append("import java.sql.Date;\n");
        c.append("\n");
        c.append(header.replace("ModelTemplate",className));
         c.append("\tpublic static String TABLE_NAME = \""+db+".TTT\";".replace("TTT",tableName));
        c.append("\n\n");
        c.append(body.toString());
        c.append(footer);
        //new File("D:/codefactroy").createNewFile();
        String fn=FactoryConst.mpath+className+".java";
        FileUtils.writeByteArrayToFile(new File(fn),c.toString().getBytes());
        System.out.println(c);
        System.out.println(fn);
        //System.out.println(map_field2member);
        return className ;
    }


}
