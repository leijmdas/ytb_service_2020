package ytb.common.ManagerCommon;

public class ManagerConst {
    static String domain_localhost = "localhost";
    static String domain_admin_d = "admin.youtobon.com";
    static String domain_project_d = "project.youtobon.com";
    static String domain_admin = domain_localhost;
    static String domain_project = domain_localhost;

    public final static String url_login = "http://localhost/rest/sysuser".replace("localhost", domain_admin);
    public final static String url_context = "http://localhost/rest/context".replace("localhost", domain_admin);


    public final static String url_manager = "http://localhost/rest/tagTableService/manager".replace("localhost", domain_admin);
    public final static String url_project = "http://localhost/rest/tagTableService/project".replace("localhost", domain_project);
    public static final String url_template = "http://localhost/rest/template".replace("localhost", domain_admin);

}
