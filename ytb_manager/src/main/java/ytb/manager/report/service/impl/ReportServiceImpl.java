package ytb.manager.report.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.manager.report.dao.ReportMapper;
import ytb.manager.report.service.ReportService;
import ytb.common.context.service.impl.YtbContext;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.manager.report.service.impl
 * Author: ZCS
 * Date: Created in 2018/12/26 16:29
 */
public class ReportServiceImpl implements ReportService{
    @Override
    public List<Map<String, Object>> getReportList(Map<String, Object> map) {
        SqlSession s = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true);
        try {
            ReportMapper m = s.getMapper(ReportMapper.class);
            return m.ReportList(map);
        } finally {
            s.close();
        }
    }

    @Override
    public int getReportCount() {
        SqlSession s = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true);
        try {
            ReportMapper m = s.getMapper(ReportMapper.class);
            return m.getReportCount();
        } finally {
            s.close();
        }
    }
}
