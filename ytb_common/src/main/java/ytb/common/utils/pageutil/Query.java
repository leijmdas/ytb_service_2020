package ytb.common.utils.pageutil;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Package: ytb.manager.sysuser.rest.impl
 * Author: ZCS
 * Date: Created in 2018/8/22 14:11
 */
public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	//当前页码
    private Integer page;
    //每页条数
    private Integer limit;

    public Query(Map<String, Object> params){
        this.putAll(params);

        //分页参数
        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        this.put("offset", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);

    }
    public Query(){

    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
