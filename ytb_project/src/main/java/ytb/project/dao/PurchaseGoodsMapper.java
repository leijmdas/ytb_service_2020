package ytb.project.dao;

import org.apache.ibatis.annotations.Param;
import ytb.project.model.*;

import java.util.List;
import java.util.Map;

public interface PurchaseGoodsMapper {
    //商品类别
    List<SearchProjectModel> getGoodsType(@Param("parentId")String parentId);

    //综合条件获取数据
    List<Map<String,Object>> getPurchaseGoodsPageInfo(Map<String,Object> map);

    //搜索总条数
    int getPurchaseGoodsCount(Map<String,Object> map);
}
