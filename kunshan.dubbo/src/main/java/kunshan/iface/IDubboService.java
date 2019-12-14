package kunshan.iface;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface IDubboService {
    List<String> getPermissions(Long id);
    JSONObject exec(JSONObject req);
    JSONObject runAgent(JSONObject req);

}