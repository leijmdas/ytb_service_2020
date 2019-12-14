package ytb.common.basic.safecontext.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONObject;
import ytb.common.context.model.Ytb_Model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class LoginSso extends Ytb_Model implements Serializable {


    //LoginSsoJson
    private LoginSsoJson loginSsoJson;

    @ExcelProperty(value = "ssoid", index = 0)
    private Integer ssoid;

    /**
     * 用户主键
     */
    @ExcelProperty(value = "userId", index = 1)
    private Long userId;

    /**
     * SessionID
     */
    private String token;

    /**
     * 登录时间
     */

    @ExcelProperty(value = "登录时间", index = 2)
    private Date loginTime;

    /**
     * 用户登录的IP
     */
    private String loginIp;

    /**
     * 短信验证码code
     */
    private String code;

    /**
     * 登陆信息
     */
    transient  private String json;

    private static final long serialVersionUID = 1L;

    public Integer getSsoid() {
        return ssoid;
    }

    public void setSsoid(Integer ssoid) {
        this.ssoid = ssoid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserid(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public  String getJson() {
        return json;
    }

    public void setJson( String json) {
        this.json = json;
    }

    public LoginSsoJson getLoginSsoJson() {
        return loginSsoJson;
    }

    public void setLoginSsoJson(LoginSsoJson loginSsoJson) {
        this.loginSsoJson = loginSsoJson;
    }


    public LoginSsoJson  parseLoginSsoJson(){

        loginSsoJson =  JSONObject.parseObject(json,LoginSsoJson.class);
        return loginSsoJson;
    }


    public boolean isUserManager(){
        if(loginSsoJson==null){
            return false;
        }
        if(loginSsoJson.getTestFlag()==null){
            return false;
        }
        return  this.loginSsoJson.getUserType().equals(LoginSsoJson.USER_TYPE_MANAGER);
    }

    public boolean isTest(){
        if(loginSsoJson==null){
            return false;
        }
        if(loginSsoJson.getTestFlag()==null){
            return false;
        }
        return  loginSsoJson.getTestFlag().equals(LoginSsoJson.TEST_FLAG_TEST_USER);
    }


}