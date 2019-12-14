package ytb.project.service.search;

import ytb.project.model.SearchProjectModel;

import java.util.List;
import java.util.Map;

public interface SupplierCommodityService {
    //获取供应商商品类型
    List<SearchProjectModel> getGoodsType(String parentId);
    //综合条件获取数据
    List<Map<String,Object>> getSellGoodsPageInfo(Map<String,Object> map);

    //查询总条数
    int getSellGoodsPageInfoCount(Map<String,Object> map);
}
