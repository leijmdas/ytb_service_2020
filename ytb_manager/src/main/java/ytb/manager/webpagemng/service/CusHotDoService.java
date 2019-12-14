package ytb.manager.webpagemng.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ytb.manager.webpagemng.model.CurServiceHotSearch;
import ytb.manager.webpagemng.model.CurServiceHotSearchExample;
import ytb.manager.webpagemng.service.impl.CusHotImpl;
import ytb.common.context.model.YtbError;

import java.util.List;

/**
 * @author lxz 2018/12/22 15:57
 */
@Scope("prototype")
@Service
public class CusHotDoService {
    CusHotImpl hotService = new CusHotImpl();

    public Boolean add(CurServiceHotSearch record) {
        try {
            hotService.insertSelective(record);
        } catch (Exception e) {
            CurServiceHotSearchExample example = new CurServiceHotSearchExample();
            example.createCriteria().andKeywordEqualTo(record.getKeyword());
            List<CurServiceHotSearch> hotSearches = hotService.selectByExample(example);
            if (hotSearches.size() > 0) {

                hotService.updateByKeyWordSelective(hotSearches.get(0));

            }
        }
        return true;
    }


    public Boolean delete(Integer id) {
        int data = hotService.deleteByPrimaryKey(id);
        return data > 0;
    }


    public List<CurServiceHotSearch> selectByHot(String keyword, Integer dataTotal) {

        if (keyword != null & dataTotal != null) {
            CurServiceHotSearchExample example = new CurServiceHotSearchExample();
            CurServiceHotSearchExample.Criteria criteria = example.createCriteria();

            example.setLimit(dataTotal);
            example.setOffset(0);
            example.setOrderByClause("cur_service_hot_search.count desc");


            criteria.andKeywordLike("%" + keyword + "%");


            List<CurServiceHotSearch> data = hotService.selectByExample(example);
            return data;
        } else return null;


    }

    public List<CurServiceHotSearch> selectAll() {
        CurServiceHotSearchExample curServiceHotSearchExample = new CurServiceHotSearchExample();
        curServiceHotSearchExample.setOrderByClause("count asc");
        return hotService.selectByExample(curServiceHotSearchExample);
    }

    public CurServiceHotSearch selectByPrimaryKey(Integer id) {


        CurServiceHotSearch data = hotService.selectByPrimaryKey(id);

        return data;
    }


    public boolean updateByPrimaryKeySelective(CurServiceHotSearch record) {


        int data = hotService.updateByPrimaryKeySelective(record);
        if (data > 0) {

            return true;
        } else {
            return false;
        }


    }

    public void updateCountByKeyWord(String keyword) {
        CurServiceHotSearchExample example = new CurServiceHotSearchExample();
        CurServiceHotSearchExample.Criteria criteria = example.createCriteria();
        criteria.andKeywordEqualTo(keyword);

        List<CurServiceHotSearch> curServiceHotSearches = hotService.selectByExample(example);
        if (curServiceHotSearches.size() > 1) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "too many record");
        }
        if (curServiceHotSearches.size() == 1) {
            //更新
            CurServiceHotSearch curServiceHotSearch = curServiceHotSearches.get(0);
            curServiceHotSearch.setCount(curServiceHotSearch.getCount() + 1);
            hotService.updateByPrimaryKeySelective(curServiceHotSearch);
        } else {
            //新增
            CurServiceHotSearch record = new CurServiceHotSearch();
            record.setKeyword(keyword);
            hotService.insertSelective(record);
        }
    }


}
