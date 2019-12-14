package ytb.manager.report.dao;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.manager.report.dao
 * Author: ZCS
 * Date: Created in 2018/12/26 16:20
 * Company: 公司
 */
public interface ReportMapper {

    //获取举报列表
    List<Map<String,Object>> ReportList(Map<String,Object> map);


    int getReportCount();
}
