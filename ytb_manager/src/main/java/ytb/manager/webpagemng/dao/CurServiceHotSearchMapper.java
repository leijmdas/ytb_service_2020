package ytb.manager.webpagemng.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ytb.manager.webpagemng.model.CurServiceHotSearch;
import ytb.manager.webpagemng.model.CurServiceHotSearchExample;

public interface CurServiceHotSearchMapper {
    long countByExample(CurServiceHotSearchExample example);

    int deleteByExample(CurServiceHotSearchExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CurServiceHotSearch record);

    int insertSelective(CurServiceHotSearch record);

    List<CurServiceHotSearch> selectByExample(CurServiceHotSearchExample example);

    CurServiceHotSearch selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CurServiceHotSearch record, @Param("example") CurServiceHotSearchExample example);

    int updateByExample(@Param("record") CurServiceHotSearch record, @Param("example") CurServiceHotSearchExample example);

    int updateByPrimaryKeySelective(CurServiceHotSearch record);

    int updateByPrimaryKey(CurServiceHotSearch record);

    int updateByKeyWordSelective(CurServiceHotSearch record);


}