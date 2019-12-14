package ytb.manager.webpagemng.dao;

import ytb.manager.webpagemng.model.CusServiceQuestionType;

import java.util.List;

/**
 * @author lxz 2018/12/22 15:04
 */
public interface CusServiceQuestionTypeMapper {

    int insert(CusServiceQuestionType cusServiceQuestionType);

    int deleteById(int id);

    int update(CusServiceQuestionType cusServiceQuestionType);

    CusServiceQuestionType selectOne(CusServiceQuestionType cusServiceQuestionType);

    List<CusServiceQuestionType> selectList(CusServiceQuestionType cusServiceQuestionType);

}
