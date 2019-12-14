package ytb.common.context.rest;

import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Package: ytb.manager.metadata.rest.impl
 * Author: leijming
 * Date: Created in 2018/10/17 18:11
 */
public interface IRestProcess {
    final static String UTF_8 = "UTF-8";

    MsgResponse process(MsgHandler handler, HttpServletRequest request, HttpServletResponse response) throws Exception;

}