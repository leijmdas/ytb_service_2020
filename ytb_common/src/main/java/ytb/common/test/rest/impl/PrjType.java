package ytb.common.test.rest.impl;

import org.springframework.stereotype.Component;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;
import ytb.common.context.model.Ytb_Model;

/**
 * Package: ytb.common.testcase.Rest.impl
 * <p>
 * Description： TODO
 * <p>
 * Author: ZCS
 * <p>
 * Date: Created in 2018/8/14 16:21
 * <p>
 * Company: 公司
 * <p>
 * Copyright: Copyright (c) 2018
 * <p>
 * Version: 0.0.1
 * <p>
 * Modified By:
 */
@Component
public final class PrjType extends Ytb_Model {
    int retcode = 0;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    String retmsg = "成功";
    String msgBody = "{}";


    public MsgResponse process(MsgRequest req, MsgHandler handler) {


        if (req.cmd.equals("selectList")) {
            int id=req.msgBody.getInteger("id");
            msgBody = getPrjTypeList(id);
        }else{
            throw new YtbError(YtbError.CODE_INVALID_REST);
        }

        return handler.buildMsg(retcode, retmsg, msgBody);

    }


    String getPrjTypeList(int id) {
        return msgBody;

    }

}
