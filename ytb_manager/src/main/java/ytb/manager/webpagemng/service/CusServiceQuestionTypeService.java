package ytb.manager.webpagemng.service;

import com.alibaba.fastjson.JSONArray;
import ytb.manager.webpagemng.model.CusServiceQuestionType;

import java.util.List;

/**
 * @author lxz 2018/12/22 15:52
 */
public interface CusServiceQuestionTypeService {

    CusServiceQuestionType add(CusServiceQuestionType cusServiceQuestionType);

    int removeById(int id);

    CusServiceQuestionType modify(CusServiceQuestionType cusServiceQuestionType);

    CusServiceQuestionType selectOne(CusServiceQuestionType cusServiceQuestionType);

    JSONArray selectList(CusServiceQuestionType cusServiceQuestionType);
}
