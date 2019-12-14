package ytb.manager.projectStat.rest;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ytb.manager.projectStat.rest.impl.ProjectStatMoneyRestProcess;
import ytb.manager.projectStat.rest.impl.ProjectStatPageRestProcess;
import ytb.manager.projectStat.rest.impl.ProjectStatProjectRestProcess;
import ytb.manager.projectStat.rest.impl.ProjectStatUserRestProcess;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

/**
 * 后台元数据字典模块的Rest类
 * Package: ytb.manager.metadata.rest
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
@RestController
@RequestMapping("/rest/project")
@Scope("prototype")
public class RestProjectStat extends RestHandler {

    @PostMapping(value = "stat", produces = {"application/json;charset=UTF-8"})
    public String restProjectStat(@RequestBody String data) {
        try {
            parseRequest(data);
            resp = process();
        } catch (YtbError e) {
            e.printStackTrace();
            resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
        } catch (Exception e) {
            e.printStackTrace();
            YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
            resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
        }
        return resp.toJSONStringPretty();

    }

    protected MsgResponse process() {

        if (req.cmdtype.equals("projectStatPage")) {
            return new ProjectStatPageRestProcess().process(req, this);
        } else if (req.cmdtype.equals("projectStatUser")) {
            return new ProjectStatUserRestProcess().process(req, this);
        } else if (req.cmdtype.equals("projectStatProject")) {
            return new ProjectStatProjectRestProcess().process(req, this);
        } else if (req.cmdtype.equals("projectStatMoney")) {
            return new ProjectStatMoneyRestProcess().process(req, this);
        }
        throw new YtbError(YtbError.CODE_INVALID_REST);

    }

}
