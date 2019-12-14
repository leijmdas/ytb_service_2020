package ytb.common;

import java.math.BigDecimal;

/*
* 测试的公共变量
* */
public class TestProjectConst {

    static String domain_localhost = "localhost";
    static String domain_admin_d = "admin.youtobon.com";
    static String domain_project_d = "project.youtobon.com";
    static String domain_admin = domain_localhost;
    static String domain_project = domain_localhost;

    public final static String url_login = "http://localhost/rest/ytbuser".replace("localhost", domain_project);
    public final static String url_context = "http://localhost/rest/context".replace("localhost", domain_admin);

    public final static String url_projectCenter = "http://localhost/rest/projectCenter".replace("localhost", domain_project);

    public final static String url_project_tagTableService = "http://localhost/rest/tagTableService/project".replace("localhost", domain_project);
    public final static String url_manager_tagTableService = "http://localhost/rest/tagTableService/manager".replace("localhost", domain_admin);

    public final static int projectId = 731;

    public final static int projectTypeId = 75;
    public final static int projectTypeId_purchase = 502;
    public final static int projectTypeId_processing = 95;

    public final static int talkId = 389;
    public final static String projectName = "GC回收";

    //甲方
    public final static int paId = 1300;
    //乙方
    public final static int pbId = 202;
    //邀请好友
    public final static String friends = "202,182,183";//pb
    //非组员
    public final static String unPmFriends = "190,191,192";//not pm
    //乙方成员
    public final static String pmFriends = "193";//pm
    public final static Integer pmId = Integer.parseInt(pmFriends.split(",")[0]);//pmId

    public final static String payPasswodEn = "e10adc3949ba59abbe56e057f20f883e";
    public final static String passwod = "123456";
    public final static String passwodEn = "e10adc3949ba59abbe56e057f20f883e";

    public final static int paBalance = 100000;

    public final static BigDecimal projectTotalFee_Phase1 =new BigDecimal(100);
    public final static BigDecimal projectTotalFee_Phase2 = new BigDecimal(100);
    public final static BigDecimal projectTotalFee_Phase3 = new BigDecimal(100);
    public final static BigDecimal projectTotalFee_Phase4 = new BigDecimal(100);
    public final static BigDecimal projectTotalFee_Phase5 =  new BigDecimal(100);
    public final static BigDecimal projectTotalFee_Phase6 =  new BigDecimal(100);

    public final static BigDecimal talkCostSum = new BigDecimal(600);
    public final static BigDecimal talkServiceSum =  new BigDecimal(10);;
    public final static BigDecimal talkTaxSum =  new BigDecimal(10);;

}
