package ytb.manager.webpagemng.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import ytb.manager.webpagemng.model.CurServiceHotSearch;
import ytb.manager.webpagemng.model.CurServiceHotSearchExample;
import ytb.manager.webpagemng.model.CusServiceQuestion;
import ytb.manager.webpagemng.utils.PageData;

import java.util.List;

/**
 * @author lxz 2018/12/22 15:57
 */

@Service
public interface CusHotService {
    long countByExample(CurServiceHotSearchExample example);

    int deleteByExample(CurServiceHotSearchExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CurServiceHotSearch record);

    int insertSelective(CurServiceHotSearch record);

    List<CurServiceHotSearch> selectByExample(CurServiceHotSearchExample example);

    CurServiceHotSearch selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CurServiceHotSearch record, @Param("example") CurServiceHotSearchExample example);

    int updateByKeyWordSelective(CurServiceHotSearch record);

    int updateByExample(@Param("record") CurServiceHotSearch record, @Param("example") CurServiceHotSearchExample example);

    int updateByPrimaryKeySelective(CurServiceHotSearch record);

    int updateByPrimaryKey(CurServiceHotSearch record);

}
