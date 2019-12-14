package bangbangtest;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import ytb.bangbang.model.FriendGroupInfo;
import ytb.bangbang.service.FriendsInfoService;
import ytb.bangbang.service.impl.FriendsInfoServiceImpl;

/**
 * @author lxz 2019/1/7 19:22
 */

public class sqTest {

    public static void main(String[] args) {
        FriendsInfoService friendsInfoService = new FriendsInfoServiceImpl();
        FriendGroupInfo friendGroupInfo = friendsInfoService.getFriendGroupInfo(1300);
        System.out.println(JSON.toJSONString(friendGroupInfo, SerializerFeature.PrettyFormat));

    }


}
