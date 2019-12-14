package ytb.project.dao;

import org.apache.ibatis.annotations.Param;
import ytb.project.model.*;

import java.util.List;
import java.util.Map;

/*供应商商品*/
public interface SupplierCommodityMapper {
    //商品类别
    List<SearchProjectModel> getGoodsType(@Param("parentId")String parentId);

    //综合条件获取数据
    List<Map<String,Object>> getSellGoodsPageInfo(Map<String,Object> map);

    //查询总条数
    int getSellGoodsPageInfoCount(Map<String,Object> map);
}
