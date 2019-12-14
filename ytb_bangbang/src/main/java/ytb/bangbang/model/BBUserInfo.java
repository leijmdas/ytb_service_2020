package ytb.bangbang.model;

import ytb.user.model.UserInfoModel;

/**
 * @author Lxz 2019/2/21 16:09
 */
public class BBUserInfo {

    private int id;

    private String username;

    private String avatar;

    private String status;

    private String sign;


    public BBUserInfo() {
    }

    public BBUserInfo parseToUserInfo(UserInfoModel userInfoModel) {
        this.id = userInfoModel.getUserId();
        this.username = userInfoModel.getNickName();
        this.avatar = userInfoModel.getUserHead();
        this.status = userInfoModel.getIsOnline() ? "online" : "hide";
        this.sign = userInfoModel.getUserSign();
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
