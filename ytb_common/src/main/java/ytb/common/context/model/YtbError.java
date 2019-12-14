package ytb.common.context.model;

import ytb.common.basic.config.model.Dict_ErrorCode;
import ytb.common.ytblog.YtbLog;
import ytb.common.context.service.IUserContext;
import ytb.common.context.service.impl.YtbContext;

public class YtbError extends Error {
    
    //参数校验错误
    public static String CODE_PARAMETER_CHECK = "REQ_PARAMETER_CHECK";

    //用户操作错误
    public static String CODE_USER_ERROR = "USER_ERROR";
    //自定义错误
    public static String CODE_DEFINE_ERROR = "DEFINE_ERROR";
    //系统引起的错误，可能是bug,系统资源
    public static String CODE_UNKNOWN_ERROR = "UNKNOWN_ERROR";

    public static String CODE_FILE_TOO_BIG = "FILE_TOO_BIG";

    public static String CODE_NULL = "NULL_POINTER";
    public static String CODE_SUCCESS = "SUCCESS";
    public static String CODE_FAIL = "FAIL";
    public static String CODE_INVALID_USER = "INVALID_USER";
    public static String CODE_INVALID_COMPANY_EMP = "INVALID_COMPANY_EMP";
    public static String CODE_NORIGHT_REST = "NORIGHT_REST";
    public static String CODE_EXISTING_RECORDS = "EXISTING_RECORDS";
    public static String CODE_NOTEXISTS_RECORD = "NOTEXISTS_RECORD";
    public static String CODE_NOTEXISTS_ACCOUNT = "NOTEXISTS_ACCOUNT";
    public static String CODE_WORKPLAN_DOC_NO_USER = "WORKPLAN_DOC_NO_USER";

    public static String CODE_NOTEXISTS_DOCUMENT = "NOTEXISTS_DOCUMENT";

    public static String CODE_INVALID_REST = "INVALID_REST";
    public static String CODE_CONTENT_TYPE_INVALID = "CONTENT_TYPE_INVALID";
    public static String CODE_PARAMETER_IS_WRONG = "PARAMETER_IS_WRONG";
    public static String CODE_PICCODE_INVALID = "PICCODE_INVALID";
    public static String CODE_DUPLICATE_RECORD = "DUPLICATE_RECORD";
    public static String CODE_TEMPLATE_DOC_TYPE_INVALID = "TEMPLATE_DOC_TYPE_INVALID";
    public static String CODE_CACHE_TOO_MANNY = "CACHE_TOO_MANNY";
    public static String CODE_CACHE_NOT_ALLOWED = "CACHE_NOT_ALLOWED";
    public static String CODE_USER_NOT_IN_GROUP = "CODE_USER_NOT_IN_GROUP";

    public static String REQ_PARAMETER_NULL = "REQ_PARAMETER_NULL";
    public static String PHASE_PAY_FAILD = "PHASE_PAY_FAILD";
    public static String CODE_DOC_REF_NOT_FOUND = "DOC_REF_NOT_FOUND";
    public static String MODIFY_DOC_ERROR = "MODIFY_DOC_ERROR";
    public static String PAY_MONEY_NOTENOUPH = "PAY_MONEY_NOTENOUPH";

    public static String PAY_MONEY_NOTENOUGH = "PAY_MONEY_NOTENOUGH";

    public int retcode;
    public String msg;

    public String toString() {
        return "错误码:" + retcode + ", " + msg;
    }
    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static int getErrorId(String code) {
        Dict_ErrorCode json = YtbContext.getYtb_context().getError_msg(code);
        return json.getErrorId();
    }

    public YtbError(String code) {
        Dict_ErrorCode json = YtbContext.getYtb_context().getError_msg(code);
        this.retcode = json.getErrorId();
        this.msg = json.getRemarkChina();
        YtbLog.logError(retcode , msg);
    }

    public YtbError(String code, String errMsg) {
        Dict_ErrorCode json = YtbContext.getYtb_context().getError_msg(code);
        this.retcode = json.getErrorId();
        this.msg = json.getRemarkChina() + ": " + errMsg;
        YtbLog.logError(retcode,  msg);
    }
    public YtbError(IUserContext userContext,String code, String errMsg) {
        Dict_ErrorCode json = YtbContext.getYtb_context().getError_msg(code);
        this.retcode = json.getErrorId();
        this.msg = json.getRemarkChina() + ": " + errMsg;
        YtbLog.logError(userContext,retcode,  msg);
    }

    public YtbError(int retcode, String msg) {
        this.retcode = retcode;
        this.msg = msg;
        YtbLog.logError(retcode , msg);

    }

}
