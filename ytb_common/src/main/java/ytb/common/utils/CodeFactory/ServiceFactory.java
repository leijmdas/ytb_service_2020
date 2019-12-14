package ytb.common.utils.CodeFactory;

import org.apache.commons.io.FileUtils;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
public interface IModelService {
    int insert(Model m);
    void update(Model m);
    void delete(int id);
    Model selectOne(int id);

    List<Model> selectList(int m);
    List<Model> selectList(Model m);
}
* */
public final class ServiceFactory {


    //public StringBuilder isb=new StringBuilder(1024);
    public static String buildIService(ModelTemplate m) throws IOException {
        StringBuilder isb=new StringBuilder(1024);
        isb.append("import java.util.List;");
        isb.append("\n");


        isb.append("public interface IModelService {\n");
        isb.append("\n");
        isb.append("\tint insert(Model m);\n");
        isb.append("\tvoid update(Model m);\n");
        isb.append("\tvoid delete(int id);\n");
        isb.append("\tModel selectOne(int id);\n");
        isb.append("\n");
        isb.append("\tList<Model> selectList(int id);\n");
        isb.append("\tList<Model> selectList(Model m);\n");
        isb.append("\n");
        isb.append("}\n");
        String s = isb.toString().replaceAll("Model",m.getClassName());
        String f=FactoryConst.ipath+"IModelService.java".replaceAll("Model",m.getClassName());
        FileUtils.writeByteArrayToFile(new File(f),s.getBytes());
        return f;
    }

    public static StringBuilder buildInsert(ModelTemplate m) {
        Map<String, String> mm = new LinkedHashMap<>(m.getMap_field2member());
        for(String key:mm.keySet()){
            mm.put(key,"#{"+mm.get(key)+"}");
        }
        mm.remove(m.getPk());
        StringBuilder sb = new StringBuilder(256);
        sb.append("\tStringBuilder sql=new StringBuilder(256);\n");
        sb.append("\t\tsql.append(\"insert into ").append(m.getDb()).append(".").append(m.getTableName());
        sb.append("\");\n\t\tsql.append(\"(");
        sb.append(mm.keySet().stream().collect(Collectors.joining(",")));
        sb.append(")\");\n");
        sb.append("\t\tsql.append(\"values\");\t\t\n");
        sb.append("\t\tsql.append(\"(");
        sb.append(mm.values().stream().collect(Collectors.joining(",")));
        sb.append(")\");\n");
        sb.append("\t\treturn YtbSql.insert(sql,m);");
        sb.append("\n\n");
        return sb;
    }
    public static StringBuilder buildUpdate(ModelTemplate m) {
        Map<String, String> mm = new LinkedHashMap<>(m.getMap_field2member());
        for(String key:mm.keySet()){
            mm.put(key,key+"=#{"+mm.get(key)+"}");
        }
        String w=mm.get(m.getPk());
        mm.remove(m.getPk());
        StringBuilder sb = new StringBuilder(256);
        sb.append("\tStringBuilder sql=new StringBuilder(256);\n");
        sb.append("\t\tsql.append(\"update ").append(m.getDb()).append(".").append(m.getTableName());
        sb.append("\");\n\t\tsql.append(\"set ");
        sb.append(mm.values().stream().collect(Collectors.joining(",")));
        sb.append("\");\n");
        sb.append("\t\tsql.append(\" where " );
        sb.append(w).append("\");\n");

        sb.append("\t\tYtbSql.update(sql,m);");
        sb.append("\n\n");
        return sb;
    }

    public static StringBuilder buildDelete(ModelTemplate m) {
        Map<String, String> mm = new LinkedHashMap<>(m.getMap_field2member());
        for(String key:mm.keySet()){
            mm.put(key,"#{"+mm.get(key)+"}");
        }
        StringBuilder sb = new StringBuilder(256);
        sb.append("\tStringBuilder sql=new StringBuilder(256);\n");
        sb.append("\t\tsql.append(\"delete from ").append(m.getDb()).append(".").append(m.getTableName());
        sb.append("\");\n");
        sb.append("\t\tsql.append(\" where ").append(m.getPk());
        sb.append("=").append(mm.get(m.getPk())).append("\");\n");
        sb.append("\t\tYtbSql.delete(sql,"+ModelTemplate.underlineToCamel(m.getPk())+");");
        sb.append("\n\n");
        return sb;
    }

    public static StringBuilder buildSelectOne(ModelTemplate m) {
        Map<String, String> mm = new LinkedHashMap<>(m.getMap_field2member());
        for (String key : mm.keySet()) {
            mm.put(key, "#{" + mm.get(key) + "}");
        }
        StringBuilder sb = new StringBuilder(256);
        sb.append("\tStringBuilder sql=new StringBuilder(256);\n");
        sb.append("\t\tsql.append(\"select *  from ").append(m.getDb()).append(".").append(m.getTableName());
        sb.append("\");\n");
        sb.append("\t\tsql.append(\" where ").append(m.getPk());
        sb.append("=").append(mm.get(m.getPk())).append("\");\n");
        sb.append("\t\treturn YtbSql.selectOne(sql,id,Model.class);");
        sb.append("\n\n");
        return sb;
    }

    public static StringBuilder buildSelectList(ModelTemplate m) {
        Map<String, String> mm = new LinkedHashMap<>(m.getMap_field2member());
        for (String key : mm.keySet()) {
            mm.put(key, "#{" + mm.get(key) + "}");
        }
        StringBuilder sb = new StringBuilder(256);
        sb.append("\tStringBuilder sql=new StringBuilder(256);\n");
        sb.append("\t\tsql.append(\"select *  from ").append(m.getDb()).append(".").append(m.getTableName());
        sb.append("\");\n");
        sb.append("\t\tsql.append(\" where ").append(m.getPk());
        sb.append("=").append(mm.get(m.getPk())).append("\");\n");
        sb.append("\t\treturn YtbSql.selectList(sql,id,Model.class);");
        sb.append("\n\n");
        return sb;
    }
    public static StringBuilder buildSelectListM(ModelTemplate m) {
        Map<String, String> mm = new LinkedHashMap<>(m.getMap_field2member());
        for (String key : mm.keySet()) {
            mm.put(key, "#{" + mm.get(key) + "}");
        }
        StringBuilder sb = new StringBuilder(256);
        sb.append("\tStringBuilder sql=new StringBuilder(256);\n");
        sb.append("\t\tsql.append(\"select *  from ").append(m.getDb()).append(".").append(m.getTableName());
        sb.append("\");\n");
        sb.append("\t\tsql.append(\" where ").append(m.getPk());
        sb.append("=").append(mm.get(m.getPk())).append("\");\n");
        sb.append("\t\treturn YtbSql.selectList(sql,m,Model.class);");
        sb.append("\n\n");
        return sb;
    }
    public static String build(ModelTemplate m) throws IOException {
        StringBuilder isb = new StringBuilder(1024);
        isb.append("import java.util.List;\n");
        isb.append("\n");
        isb.append("import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;\n");

        isb.append("public class ModelServiceImpl implements IModelService {\n");
        isb.append("\n");
        isb.append("\tpublic int insert(Model m) {\n");
        isb.append("\t").append(buildInsert(m).toString());
        isb.append("\t}\n");

        isb.append("\tpublic void update(Model m){\n");
        isb.append("\t").append(buildUpdate(m).toString());
        isb.append("\t}\n\n");
        isb.append("\tpublic void delete(int id){\n".replace(" id", " "+ ModelTemplate.underlineToCamel(  m.getPk())));
        isb.append("\t").append(buildDelete( m).toString());

        isb.append("\t}\n\n");

        isb.append("\tpublic Model selectOne(int id) {\n");
        isb.append("\t").append(buildSelectOne(m));
        isb.append("\t}\n\n");

        isb.append("\n");
        isb.append("\tpublic List<Model> selectList(int id) {\n");
        isb.append("\t").append(buildSelectList(m));
        isb.append("\t}");
        isb.append("\n\n");
        isb.append("\tpublic List<Model>  selectList(Model m){\n");
        isb.append("\t").append(buildSelectListM(m));
        isb.append("\t}\n\n");

        isb.append("}\n");
        String s = isb.toString().replaceAll("Model", m.getClassName());
        String f = FactoryConst.spath+"ModelServiceImpl.java".replaceAll("Model", m.getClassName());
        System.out.println(s);
        FileUtils.writeByteArrayToFile(new File(f), s.getBytes());
        return f;
    }

}
