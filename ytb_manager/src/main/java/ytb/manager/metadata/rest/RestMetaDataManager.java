package ytb.manager.metadata.rest;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ytb.manager.metadata.rest.impl.ConfigCenterRestProcess;
import ytb.manager.metadata.rest.impl.MetaDataProcess;
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
@RequestMapping("/rest")
@Scope("prototype")
public class RestMetaDataManager extends RestHandler {

    @RequestMapping(value = "/sysmetadata", produces = "application/json;charset=UTF-8")
    public String metaDataRest(@RequestBody String data) {
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
        return resp.toJSONString();

    }

    @RequestMapping(value = "/configCenter", produces = "application/json;charset=UTF-8")
    public String configCenter(@RequestBody String data) {
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
        return resp.toJSONString();

    }

    protected MsgResponse process() {
        if (req.cmdtype.equals("metadata")) {
            return new MetaDataProcess().process(req, this);
        } else if (req.cmdtype.equals("configCenter")) {
            return new ConfigCenterRestProcess().process(req, this);
        }
        throw new YtbError(YtbError.CODE_INVALID_REST);

    }

}
//    select a.project_id,
//        a.project_type_id                           as porject_type,
//        b.startTime                                 as plan_startTime,
//        c.user_complete_time                        as plan_endTime,
//        datediff(c.user_complete_time, b.startTime) as days_plan,
//        datediff(a.finish_date, b.startTime)        as days_real,
//        case
//        when a.pay_date > b.startTime then a.pay_date
//        else b.startTime end                      as start_date,
//        a.finish_date
//        from ytb_project.vw_project_finish a,
//        ytb_project.project_plan b,
//        ytb_project.project_plan_sum c
//        where a.project_id = b.project_id
//        and a.project_id = c.project_id
