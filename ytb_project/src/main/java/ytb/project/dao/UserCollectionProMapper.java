package ytb.project.dao;

import ytb.project.model.UserCollectionProModel;

import java.util.List;
import java.util.Map;

public interface UserCollectionProMapper {
    //获取搜藏列表
    List<Map<String,Object>> getUserCollectionList(Map<String,Object> map);

    //获取总条数
    int getUserCollectionCount(Map<String,Object> map);

    //添加收藏
    void addUserCollection(UserCollectionProModel userCollectionProModel);

    //取消收藏
    void deleteUserCollection(Map<String,Object> map);
}
