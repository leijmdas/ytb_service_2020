package ytb.bangbang.model;

import java.util.Date;

/**
 * @Author hj
 * @Description //用户信息
 * @Date 2018/9/11
 **/
public class UserInfoModel {
    //表主键
    private int userId = 0;

    //状态:0可用 1禁用
    private int status = 0;

    //登录账号
    private String loginMobile = "";

    //昵称
    private String nickName = "";

    //注册时间
    private Date registeredTime = null;

    //用户头像
    private String userHead = "";

    //用户签名
    private String userSign = "";

    //性别 1:男 2:女
    private int sex = 0;

    //出生年月
    private Date birthday = null;

    //信用等级
    private String creditGread = "B";

    //用户地址
    private String userAddress = "";

    //学历名称
    private String educationName ="";

    //公司名称
    private String companyName = "";

    //标签ID
    private int tagId = 0;

    //标签类型 1：兴趣爱好  2：专业能力
    private int tagType = 0;

}
