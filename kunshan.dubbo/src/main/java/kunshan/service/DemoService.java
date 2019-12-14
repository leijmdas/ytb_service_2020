package kunshan.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface DemoService {
    List<String> getPermissions(Long id);
    JSONObject exec(JSONObject req);
    JSONObject runAgent(JSONObject req);

}