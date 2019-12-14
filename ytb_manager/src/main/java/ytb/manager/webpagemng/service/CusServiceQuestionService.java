package ytb.manager.webpagemng.service;

import ytb.manager.webpagemng.model.CusServiceQuestion;
import ytb.manager.webpagemng.utils.PageData;

/**
 * @author lxz 2018/12/22 15:57
 */
public interface CusServiceQuestionService {

    void add(CusServiceQuestion cusServiceQuestion);

    void removeByQid(int qId);

    void modify(CusServiceQuestion cusServiceQuestion);

    CusServiceQuestion selectOne(CusServiceQuestion cusServiceQuestion);

    PageData<CusServiceQuestion> pageQuery(CusServiceQuestion cusServiceQuestion, int pageNo, int limit);

    PageData<CusServiceQuestion> pageQueryHotSearch(CusServiceQuestion cusServiceQuestion, int pageNo, int limit);
}
