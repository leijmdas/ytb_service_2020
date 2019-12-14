package ytb.manager.report.rest.impl;

import ytb.common.utils.pageutil.PageUtils;
import ytb.common.utils.pageutil.Query;
import ytb.manager.report.service.ReportService;
import ytb.manager.report.service.impl.ReportServiceImpl;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package: ytb.manager.report.rest.impl
 * Author: ZCS
 * Date: Created in 2018/12/26 16:31
 */
public class Report {

    public MsgResponse process(MsgHandler handler) {

        if (handler.req.cmd.equals("getReportList")) {

            Map<String,Object> params = new HashMap<>();
            params.put("page",handler.req.getMsgBody().getInteger("currPage"));
            params.put("limit",handler.req.getMsgBody().getInteger("pageSize"));

            Query query = new Query(params);

            ReportService reportService =  new ReportServiceImpl();
            List<Map<String,Object>> lst = reportService.getReportList(query);

            int count =  reportService.getReportCount();

            PageUtils pageUtil = new PageUtils(lst, count, query.getLimit(), query.getPage());
            handler.resp.getMsgBody().put("list", pageUtil);
            handler.resp.setRetcode(0);
            handler.resp.setRetmsg("成功");
            return handler.resp;
        }

        throw new YtbError(YtbError.CODE_INVALID_REST);

    }

}
