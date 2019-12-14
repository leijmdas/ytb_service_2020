package ytb.manager.report.service;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.manager.report.service
 * Author: ZCS
 * Date: Created in 2018/12/26 16:28
 */
public interface ReportService {
    //获取举报列表
    List<Map<String,Object>> getReportList(Map<String,Object> map);

    int getReportCount();
}
