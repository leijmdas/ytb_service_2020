package ytb.bangbang.model;

public class GroupUsers extends Group_UserModel {
    private BBUserInfo bbUserInfo;

    public BBUserInfo getBbUserInfo() {
        return bbUserInfo;
    }

    public void setBbUserInfo(BBUserInfo bbUserInfo) {
        this.bbUserInfo = bbUserInfo;
    }
}
