package ytb.manager.tagtable.rest;
/*
 * author:leijm
 * add template document rest
 * 2010 10 20
 *
 * */

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import ytb.manager.sysuser.service.impl.SysUserContext;
import ytb.manager.tagtable.rest.impl.ITagTableServiceRestProcess;
import ytb.manager.tagtable.rest.impl.TagTableServiceManagerRestProcess;
import ytb.manager.tagtable.rest.impl.TagTableServiceProjectRestProcess;
import ytb.common.context.rest.IRestProcess;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/*rest  base class*/
@RestController
@RequestMapping("/rest/tagTableService")
public class RestTagTableService implements IRestProcess {

    //文档管理接口manager for test
    @ApiOperation(value="标签表服务", notes="根据消息体处理标签表的导出，查询")
    @ApiImplicitParam(name = "data", value = "请求消息体", required = true, dataType =
            "String", paramType = "body")
    @PostMapping(value = "/manager", produces = "application/json;charset=UTF-8")
    public String tagTableServiceManager(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        return new MsgHandler(new SysUserContext()).parseRequest(this, data, request, response);

    }


    public MsgResponse process(MsgHandler handler, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (handler.req.cmdtype.equals(ITagTableServiceRestProcess.TagTableServiceManager)) {
            Integer projectId = handler.req.msgBody.getInteger("projectId");
            if (projectId != null && projectId == 0) {
                return new TagTableServiceManagerRestProcess().process(handler);
            }
            return new TagTableServiceProjectRestProcess().process(handler);
        }
        throw new YtbError(YtbError.CODE_INVALID_REST);
    }



}
