package ytb.manager.tagtable.model.tagtemplate;

import java.util.HashMap;
import java.util.Map;

public class TagTableDict {
    static Map<String, String> map_tables = new HashMap<String, String>();
    //引用字段
    static Map<String, String> map_refFields = new HashMap<String, String>();
    //条件
    static Map<String, String> map_pFields = new HashMap<String, String>();

    // String tag=tagTagleParamRef@岗位任务.任务名称(电路)$A1.11
    // tagTableParamRef@数据字典.字典名称(项目变更类别)
    static {
//        map_tables.put("数据字典", "dict_datatype");
//        map_refFields.put("字典名称", "data_name");
//        map_pFields.put("数据字典", "type_name");
//        map_tables.put("项目", "project");
//        map_refFields.put("项目名称", "project_name");

        map_tables.put("岗位任务", "work_group_task");
        map_refFields.put("岗位名称", "work_job");
        map_refFields.put("任务名称", "work_task");
        map_pFields.put("岗位任务", "work_job");
        //value:string list:[]    visible:true/false
    }

    public static String findTable(String name) {
        if (map_tables.containsKey(name)) {
            return map_tables.get(name);
        }
        return name;
    }

    public static String findRefField(String name) {
        if (map_refFields.containsKey(name)) {
            return map_refFields.get(name);
        }
        return name;
    }
    public static String findPField(String name) {
        if (map_pFields.containsKey(name)) {
            return map_pFields.get(name);
        }
        return name;
    }

}
