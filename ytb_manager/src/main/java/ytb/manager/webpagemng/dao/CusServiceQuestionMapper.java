package ytb.manager.webpagemng.dao;

import ytb.manager.webpagemng.model.CusServiceQuestion;

import java.util.List;
import java.util.Map;

/**
 * @author lxz 2018/12/22 15:20
 */
public interface CusServiceQuestionMapper {

    int insert(CusServiceQuestion cusServiceQuestion);

    int deleteByQid(int qId);

    int update(CusServiceQuestion cusServiceQuestion);

    CusServiceQuestion selectOne(CusServiceQuestion cusServiceQuestion);

    List<CusServiceQuestion> selectList(Map<String, Object> map);

    int count(Map<String, Object> map);

}
