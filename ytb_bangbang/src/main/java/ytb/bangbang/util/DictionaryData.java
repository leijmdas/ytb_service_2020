package ytb.bangbang.util;

public interface DictionaryData {
    /**
     * 默认等待同意
     */
    public  final static int ISAGREEDEFAULT=1;
    /**
     * 同意加好友
     */
    public final static int ISAGREEOK=2;
    /**
     * 拒绝加好友
     */
    public final static int ISAGREENOTE=3;
    /**
     * 过期dict_userinfo_credit
     */
    public final static int ISAGREEIGNORE=4;
    /**
     * 群主
     */
    public  final static int GROUPOWER=1;
    /**
     * 群组管理员
     */
    public final static int GROUPADMIN=2;
    /**
     * 群组普通用户
     */
    public final static int GROUPUSUAL=3;
    /**
     * 工作组
     */
    public final static int WORKGROUP=1;
    /**
     * 兴趣组
     */
    public final static int INTERESTGROUP=2;
}
