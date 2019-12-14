package ytb.common.basic.safecontext.model;

import com.alibaba.fastjson.JSONObject;
import ytb.common.context.model.Ytb_Model;

import java.sql.Date;

public final class Api_KeyModel extends Ytb_Model {

    int userId;
    String apiKey;
    String securityKey;
    Date genTime;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    public Date getGenTime() {
        return genTime;
    }

    public void setGenTime(Date genTime) {
        this.genTime = genTime;
    }

    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
