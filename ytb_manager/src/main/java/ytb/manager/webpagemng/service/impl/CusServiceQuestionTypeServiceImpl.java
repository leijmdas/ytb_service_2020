package ytb.manager.webpagemng.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import ytb.manager.webpagemng.dao.CusServiceQuestionTypeMapper;
import ytb.manager.webpagemng.model.CusServiceQuestionType;
import ytb.manager.webpagemng.service.CusServiceQuestionTypeService;
import ytb.common.context.service.impl.YtbContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lxz 2018/12/22 15:53
 */
public class CusServiceQuestionTypeServiceImpl implements CusServiceQuestionTypeService {

    private SqlSession getSession(boolean isAuto) {
        return YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(isAuto);
    }

    @Override
    public CusServiceQuestionType add(CusServiceQuestionType cusServiceQuestionType) {
        SqlSession session = getSession(false);
        try {
            CusServiceQuestionTypeMapper mapper = session.getMapper(CusServiceQuestionTypeMapper.class);
            mapper.insert(cusServiceQuestionType);
            CusServiceQuestionType questionType = mapper.selectOne(cusServiceQuestionType);
            session.commit();
            return questionType;
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }

    }

    @Override
    public int removeById(int id) {
        try (SqlSession session = getSession(true)) {
            CusServiceQuestionTypeMapper mapper = session.getMapper(CusServiceQuestionTypeMapper.class);
            mapper.deleteById(id);
            return id;
        }
    }

    @Override
    public CusServiceQuestionType modify(CusServiceQuestionType cusServiceQuestionType) {
        SqlSession session = getSession(false);
        try {
            CusServiceQuestionTypeMapper mapper = session.getMapper(CusServiceQuestionTypeMapper.class);
            mapper.update(cusServiceQuestionType);
            CusServiceQuestionType questionType = mapper.selectOne(cusServiceQuestionType);
            session.commit();
            return questionType;
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public CusServiceQuestionType selectOne(CusServiceQuestionType cusServiceQuestionType) {
        try (SqlSession session = getSession(true)) {
            CusServiceQuestionTypeMapper mapper = session.getMapper(CusServiceQuestionTypeMapper.class);
            return mapper.selectOne(cusServiceQuestionType);
        }
    }

    @Override
    public JSONArray selectList(CusServiceQuestionType cusServiceQuestionType) {
        try (SqlSession session = getSession(true)) {
            CusServiceQuestionTypeMapper mapper = session.getMapper(CusServiceQuestionTypeMapper.class);
            List<CusServiceQuestionType> questionTypeList = mapper.selectList(cusServiceQuestionType);
            JSONArray rootNodes = new JSONArray();
            Map<Integer, JSONObject> idMap = new HashMap<>();
            Map<Integer, JSONArray> idMapNoMounted = new HashMap<>();
            for (CusServiceQuestionType e : questionTypeList) {
                JSONObject node = JSONObject.parseObject(JSON.toJSONString(e));
                node.put("child", new JSONArray());
                int id = e.getId();
                idMap.put(id, node);
                int parentId = node.getIntValue("parentId");
                if (parentId == 0) {//第一层节点
                    rootNodes.add(node);
                } else {//子节点
                    JSONObject parentNode = idMap.get(parentId);
                    if (parentNode != null) {
                        JSONArray parentNodeChild = parentNode.getJSONArray("child");
                        parentNodeChild.add(node);
                    } else {
                        JSONArray noMountedNodes = idMapNoMounted.get(parentId);
                        if (noMountedNodes == null) {
                            noMountedNodes = new JSONArray();
                            idMapNoMounted.put(parentId, noMountedNodes);
                        }
                        noMountedNodes.add(node);
                    }
                }
                JSONArray noMountedNodes = idMapNoMounted.get(id);
                if (noMountedNodes != null) {
                    JSONArray child = node.getJSONArray("child");
                    child.addAll(noMountedNodes);
                    idMapNoMounted.remove(id);
                }
            }
            return rootNodes;
        }
    }
}
