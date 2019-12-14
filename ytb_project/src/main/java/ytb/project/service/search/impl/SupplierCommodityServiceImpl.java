package ytb.project.service.search.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.project.dao.SupplierCommodityMapper;
import ytb.project.model.*;
import ytb.project.service.search.SupplierCommodityService;
import ytb.common.context.service.impl.YtbContext;

import java.util.List;
import java.util.Map;

/**
 * @Author hj
 * @Description //TODO
 * @Date 2018/11/20
 **/
public class SupplierCommodityServiceImpl implements SupplierCommodityService {

    /**
     * 获取供应商商品类型
     * @return
     */
    @Override
   public  List<SearchProjectModel> getGoodsType(String parentId){
        SqlSession session = null;
        try{
             session = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project
                     (true);
             SupplierCommodityMapper supplierMapper=session.getMapper(SupplierCommodityMapper.class);
             List<SearchProjectModel> searchProjectModels=supplierMapper.getGoodsType(parentId);
             return searchProjectModels;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
           if(session!=null) {
               session.close();
           }
        }
    }
    //综合条件获取数据
    @Override
   public  List<Map<String,Object>> getSellGoodsPageInfo(Map<String,Object> map){
        SqlSession session = null;
        try{
            session = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
            SupplierCommodityMapper supplierMapper = session.getMapper(SupplierCommodityMapper.class);
            List<Map<String,Object>> supplierCommodityModels = supplierMapper.getSellGoodsPageInfo(map);
            return supplierCommodityModels;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(session!=null) {
                session.close();
            }
        }
    }

    @Override
    public int getSellGoodsPageInfoCount(Map<String, Object> map) {
        SqlSession session = null;
        try{
            session = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_project(true);
            SupplierCommodityMapper supplierMapper = session.getMapper(SupplierCommodityMapper.class);
            int supplierCommodityModels = supplierMapper.getSellGoodsPageInfoCount(map);
            return supplierCommodityModels;

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }finally {
            if(session!=null) {
                session.close();
            }
        }
    }
}
