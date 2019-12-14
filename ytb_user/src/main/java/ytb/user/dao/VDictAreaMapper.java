package ytb.user.dao;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/12/14 15:00
 */
public interface VDictAreaMapper {

    //获取
    List<Map<String,Object>> getDictAreaList(int parentId);

}
