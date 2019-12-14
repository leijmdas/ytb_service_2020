package ytb.project.service.search.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.common.context.service.impl.YtbContext;
import ytb.project.dao.PurchaseGoodsMapper;
import ytb.project.model.*;
import ytb.project.service.search.PurchaseGoodsService;

import java.util.List;
import java.util.Map;

/**
 * @Author hj
 * @Description //TODO
 * @Date 2018/11/20
 **/
public class PurchaseGoodsServiceImpl implements PurchaseGoodsService {

    /**
     * 获取供应商商品类型
     *
     * @return
     */
    @Override
    public List<SearchProjectModel> getGoodsType(String parentId) {
        SqlSession session = null;
        try {
            session = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
            PurchaseGoodsMapper purchaseGoodsMapper = session.getMapper(PurchaseGoodsMapper.class);
            List<SearchProjectModel> searchProjectModels = purchaseGoodsMapper.getGoodsType(parentId);
            return searchProjectModels;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public int getPurchaseGoodsCount(Map<String, Object> map) {
        SqlSession session = null;
        try {
            session = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
            PurchaseGoodsMapper purchaseGoodsMapper = session.getMapper(PurchaseGoodsMapper.class);
            int purchaseGoodsModels = purchaseGoodsMapper.getPurchaseGoodsCount(map);
            return purchaseGoodsModels;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    //综合条件获取数据
    @Override
    public List<Map<String, Object>> getPurchaseGoodsPageInfo(Map<String, Object> map) {
        SqlSession session = null;
        try {
            session = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
            PurchaseGoodsMapper purchaseGoodsMapper = session.getMapper(PurchaseGoodsMapper.class);
            List<Map<String, Object>> purchaseGoodsModels = purchaseGoodsMapper.getPurchaseGoodsPageInfo(map);
            return purchaseGoodsModels;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
