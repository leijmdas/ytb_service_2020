package ytb.project.rest;
/*
 * author:leijm
 * add template document rest
 * 2010- 10- 20
 *
 * */

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ytb.manager.tagtable.rest.impl.ITagTableServiceRestProcess;
import ytb.manager.tagtable.rest.impl.TagTableServiceManagerRestProcess;
import ytb.manager.tagtable.rest.impl.TagTableServiceProjectRestProcess;
import ytb.manager.tagtable.rest.impl.TagTableServiceRestProcess;
import ytb.common.context.rest.IRestProcess;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*rest  base class*/
@RestController
@RequestMapping("/rest/tagTableService")
public class RestTagTableProjectService implements IRestProcess {

    //文档管理接口 project fot online
    @PostMapping(value = "/project", produces = "application/json;charset=UTF-8")
    public String tagTableServiceProject(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        return new MsgHandler().parseRequest(this, data, request, response);

    }

    public MsgResponse process(MsgHandler handler, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (handler.getUserContext().isUserManager()) {
           // throw new YtbError(YtbError.CODE_DEFINE_ERROR, "用户非法！");
        }

        if (handler.req.cmdtype.equals(ITagTableServiceRestProcess.TagTableServiceProject)) {
            Integer projectId = handler.req.msgBody.getInteger("projectId");
            TagTableServiceRestProcess restProcess = projectId != null && projectId == 0 ?
                    new TagTableServiceManagerRestProcess() : new TagTableServiceProjectRestProcess();

            return restProcess.process(handler);
        }
        throw new YtbError(YtbError.CODE_INVALID_REST);
    }

}
