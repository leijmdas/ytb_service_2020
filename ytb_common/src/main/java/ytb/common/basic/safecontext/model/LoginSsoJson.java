package ytb.common.basic.safecontext.model;

import ytb.common.RestMessage.MsgHandler;
import ytb.common.context.model.Ytb_ModelSkipNull;

import java.io.Serializable;

/**
 * @author
 */

//    //用户中心测试用户
//    public static int USER_TYPE_PERSON_TEST = 11;
//    //公司员工测试用户
//    public static int USER_TYPE_COMPANY_EMPLOYEE_TEST = 12;
//    //公司管理员测试用户
//    public static int USER_TYPE_COMPANY_MANAGER_TEST = 13;
//    //管理中心测试用户
//    public static int USER_TYPE_MANAGER_TEST = 111;
public class LoginSsoJson extends Ytb_ModelSkipNull implements Serializable {

    public static Byte TEST_FLAG_TEST_USER_NONE = 0;
    public static Byte TEST_FLAG_TEST_USER = 1;

    //个人用户
    public static int USER_TYPE_PERSON = 1;
    //公司员工
    public static int USER_TYPE_COMPANY_EMPLOYEE = 2;
    //公司管理员
    public static int USER_TYPE_COMPANY_MANAGER = 3;

    //管理中心用户
    public static int USER_TYPE_MANAGER = 100;

    private String token;
    private String refresh_token;
    private Long expires_in;

    private String customerType;
    private Integer userType;
    private Integer userId;
    private Integer companyUserId;
    private Integer companyId;

    private Byte testFlag = TEST_FLAG_TEST_USER_NONE;

    private String loginMode;
    private Boolean isAdmin;
    private String bangbang_no;
    private String nick_name;
    private String login_mobile;
    private String creditGrade;

    Api_KeyModel api_keyModel ;

    public boolean isUserManager(){
        return  this.getUserType().equals(LoginSsoJson.USER_TYPE_MANAGER);
    }

    public Api_KeyModel getApi_keyModel() {
        return api_keyModel;
    }

    public void setApi_keyModel(Api_KeyModel api_keyModel) {
        this.api_keyModel = api_keyModel;
    }


    public Byte getTestFlag() {
        return testFlag;
    }

    public void setTestFlag(Byte testFlag) {
        this.testFlag = testFlag;
    }
    public String getCreditGrade() {
        return creditGrade;
    }

    public void setCreditGrade(String creditGrade) {
        this.creditGrade = creditGrade;
    }


    public String getNick_name() {
        return nick_name;
    }


    public String toString(){
        return MsgHandler.toJSONStringPretty(this);
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getLogin_mobile() {
        return login_mobile;
    }

    public void setLogin_mobile(String login_mobile) {
        this.login_mobile = login_mobile;
    }


    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public String getBangbang_no() {
        return bangbang_no;
    }

    public void setBangbang_no(String bangbang_no) {
        this.bangbang_no = bangbang_no;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCompanyUserId() {
        return companyUserId;
    }

    public void setCompanyUserId(Integer companyUserId) {
        this.companyUserId = companyUserId;
    }

    public String getLoginMode() {
        return loginMode;
    }

    public void setLoginMode(String loginMode) {
        this.loginMode = loginMode;
    }
}