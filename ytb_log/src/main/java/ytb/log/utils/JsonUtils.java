package ytb.log.utils;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;


import javax.servlet.http.HttpServletRequest;
import java.io.CharArrayReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/1.
 */
public class JsonUtils {


    /**
     * 获取任意对象的属性对应的JSON字符串
     *
     * @param object
     * @return
     */
    public static String getJson(Object object) {
        return new Gson().toJson(object);
    }




    /**
     * 获取任意对象的属性对应的JSON字符串
     *
     * @param map
     * @return
     */
    public static String getJson(Map<String, Object> map) {
        return new Gson().toJson(map);
    }

    /**
     * 将JSON格式字符串转为map对象
     *
     * @param json
     * @return
     */



    public static Map<String, Object> getMap(String json) {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        return new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {
        }.getType());
    }


    public static Map<String, String> getStringMap(String json) {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }

        final Gson gson = new Gson();
        final JsonReader jsonReader = new JsonReader(new CharArrayReader(json.toCharArray()));
        jsonReader.setLenient(true);

        return new Gson().fromJson(jsonReader, new TypeToken<HashMap<String, String>>() {
        }.getType());
    }

    public static List<Map<String, String>> getStringListMap(String json) {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }

        return new Gson().fromJson(json, new TypeToken<List<HashMap<String, String>>>() {
        }.getType());
    }




    /**
     * 从请求对象中提取参数到map
     *
     * @param request 请求对象
     * @return 参数map
     */
    public static Map<String, String> getStringMap(HttpServletRequest request) {

        Map<String, String[]> requestMap = request.getParameterMap();
        if (requestMap.isEmpty()) return null;

        Map<String, String> paramsMap = Maps.newHashMap();
        for (String key : requestMap.keySet()) {
            String[] valueArray = requestMap.get(key);
            String value = "";

            if (null != valueArray && 0 < valueArray.length) {
                value = valueArray[0];
            }

            if (!Strings.isNullOrEmpty(value)) {
                paramsMap.put(key, value);
            }
        }

        //activiti.info("参数--【" + paramsMap.toString() + "】");
        return paramsMap;
    }


    /***
     * @param dt 需要转化的DataTable
     * @return Json字符串
     */




    /**
     * 把json字符串 转换为java对象，解决了多级的json转换为java对象失败的问题；
     * @param json
     * @param entityClass
     * @param <T>
     * @return
     */
    public static <T> T createObjectFromJson(String json, Class<T> entityClass) {
        try {

            final Gson gson = new Gson();
            final JsonReader jsonReader = new JsonReader(new CharArrayReader(json.toCharArray()));
            jsonReader.setLenient(true);
            return new Gson().fromJson(jsonReader, entityClass);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        Map<String,Object> map = new HashMap<String, Object>();
//        map.put("name","xucheng");
//        map.put("age",30);
//        map.put("sex","man");
//        map.put("birthday","1995-10-10 12:00:00");
//        map.put("home","shanxi");
//        map.put("map",map);
//
//        System.out.println(JsonUtils.getJson(map));
//        System.out.println("-----------------------");
//
//        String username = "carter";
//
//        System.out.println(JsonUtils.getJson(username));
        //String   str="{\"code\":\"0\",\"msg\":\"111\",\"data\":{\"guid\":\"s559b7c2b9c9b4\",\"username\":\"fdtms13480165874\",\"refer_type\":\"1\",\"refer_name\":\"\"}}";
        String str = "{\n" +
                "    \"code\": 0,\n" +
                "    \"msg\": \"验证成功\",\n" +
                "    \"data\": {\n" +
                "        \"guid\": \"s559b7c2b9c9b4\",\n" +
                "        \"username\": \"fdtms13480165874\",\n" +
                "        \"refer_type\": 1,\n" +
                "        \"refer_name\": \"\"\n" +
                "    }\n" +
                "}";
        Map<String, Object> value = JsonUtils.getMap(str);
        Map<String, String> data = (Map) value.get("data");
        System.out.println(data.get("guid").toString());
        System.out.println(data.get("username").toString());

    }



}
