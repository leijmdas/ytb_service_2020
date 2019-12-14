package ytb.project.service.search;

import ytb.project.model.SearchProjectModel;

import java.util.List;
import java.util.Map;

public interface PurchaseGoodsService {
    //获取供应商商品类型
    List<SearchProjectModel> getGoodsType(String parentId);
    //综合条件获取数据
    List<Map<String,Object>> getPurchaseGoodsPageInfo(Map<String,Object> map);

    //搜索总条数
    int getPurchaseGoodsCount(Map<String,Object> map);
}
