package ytb.bangbang.model;

import java.util.Date;

/**
 * @Author hj
 * @Description //用户基本信息
 * @Date 2018/9/11
 **/
public class UserBaseInfo {
    //昵称
    private String nickName = "";

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


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCreditGread() {
        return creditGread;
    }

    public void setCreditGread(String creditGread) {
        this.creditGread = creditGread;
    }

}
