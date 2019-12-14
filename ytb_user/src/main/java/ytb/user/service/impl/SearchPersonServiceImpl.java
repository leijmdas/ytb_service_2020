package ytb.user.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.common.context.service.impl.YtbContext;
import ytb.user.dao.SearchPersonMapper;
import ytb.user.dao.VDictAreaMapper;
import ytb.user.model.*;
import ytb.user.service.SearchPersonService;
import ytb.user.context.MyBatisUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author hj
 * @Description //搜索人才
 * @Date 2018/10/29
 **/

public class SearchPersonServiceImpl implements SearchPersonService{

    /**
     * 专业能力标签
     * @return
     */
    @Override
   public  List<Map<String,Object>> getPageTag(String parentId){
       SqlSession session=null;
       List<Map<String,Object>> result= new ArrayList<>();
       if(parentId == null|| parentId.isEmpty())
               throw new RuntimeException();
        try{
           session= MyBatisUtil.getSession();
           SearchPersonMapper searchPersonMapper=session.getMapper(SearchPersonMapper.class);
           result = searchPersonMapper.getPageTag(parentId);
           return result;
       }catch(Exception e){
           e.printStackTrace();
           return result;
       }finally {
           if(session !=null)session.close();
       }
   }

    /**
     * 接单范围
     * @return
     */
    @Override
    public List<SearchProjectModel> getPageScope(String parentId){
        SqlSession session=null;
        List<SearchProjectModel> result=new ArrayList<SearchProjectModel>();
        if(parentId==null||parentId.isEmpty())
            throw new RuntimeException();
        try{
            session= MyBatisUtil.getSession();
            SearchPersonMapper searchPersonMapper=session.getMapper(SearchPersonMapper.class);
            result = searchPersonMapper.getPageScope(parentId);
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return result;
        }finally {
            if(session !=null)session.close();
        }
    }

    /**
     * 更据条件查询分页信息
     * @return
     */
    @Override
   public List<SearchPagePersonModel> getPagePerson(Map<String,Object> map){
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        List<SearchPagePersonModel> result;
        try{
            SearchPersonMapper searchPersonMapper = ss.getMapper(SearchPersonMapper.class);
            result = searchPersonMapper.getPagePerson(map);
        }finally {
            ss.close();
        }
        return result;
    }

    @Override
    public int getPagePersonCount(Map<String, Object> map) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try{
            SearchPersonMapper searchPersonMapper = ss.getMapper(SearchPersonMapper.class);
           int result = searchPersonMapper.getPagePersonCount(map);
            return result;
        }finally {
            ss.close();
        }
    }

    @Override
    public List<Map<String, Object>> getDictAreaList(int parentId) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try{
            VDictAreaMapper searchPersonMapper = ss.getMapper(VDictAreaMapper.class);
            List<Map<String,Object>> result = searchPersonMapper.getDictAreaList(parentId);
            return result;
        }finally {
            ss.close();
        }
    }

    @Override
    public List<Map<String, Object>> getSearchCompany(Map<String, Object> map) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        List<Map<String, Object>> result;
        try{
            SearchPersonMapper searchPersonMapper = ss.getMapper(SearchPersonMapper.class);
            result = searchPersonMapper.getSearchCompany(map);
        }finally {
            ss.close();
        }
        return result;
    }

    @Override
    public int getSearchCompanyCount(Map<String, Object> map) {
        SqlSession ss = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_user(true);
        try{
            SearchPersonMapper searchPersonMapper = ss.getMapper(SearchPersonMapper.class);
            int result = searchPersonMapper.getSearchCompanyCount(map);
            return result;
        }finally {
            ss.close();
        }
    }

    /**
     * 更具用户id获取最高学历基本信息
     * @param userId
     * @return
     */
    @Override
    public Map<String,Object> getEducationByUserId(String userId){
        SqlSession session=null;
        try{
            session= MyBatisUtil.getSession();
            SearchPersonMapper searchPersonMapper = session.getMapper(SearchPersonMapper.class);
            return searchPersonMapper.getEducationByUserId(userId);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            if(session !=null)session.close();
        }
    }
}
