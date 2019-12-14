package bangbangtest;

import com.alibaba.fastjson.JSON;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.RestMessage.MsgRequest;

/**
 * @ClassName ProjectBangBangTest
 * @Description TODO
 * @Author qsy
 * @Date 2019/4/17 13:23
 **/
public class ProjectBangBangTest extends ITestImpl {
    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    private MsgRequest req = new MsgRequest();

    private String url_manager = "http://mysql.kunlong.com:80/projectBangBangRest/bangBangRestAjax";
    String token = "6ad91a1523ec48ffbe115ea442c121ec";
    String apiKey = "1555581646108";

    private String data;

    @Inject("ytb_bangbang")
    JDbNode dbNode;

    @Override
    public void setUp() throws Exception {
        long l = System.currentTimeMillis();
        req.reqtime = l;
        req.seqno = l;
        req.token = token;
        req.cmdtype = "";
        req.cmd = "";
        req.setApiKey(apiKey+"");
        req.msgBody = JSON.parseObject("{}");
    }
    @JTest
    @JTestClass.title("获取好友及组信息")
    @JTestClass.pre("xxx")
    @JTestClass.step("post http://mysql.kunlong.com:80/projectBangBangRest/bangBangRestAjax")
    @JTestClass.exp("ok")
    public void test00001() {
        req.cmdtype = "FriendsManger";
        req.cmd = "getUsersGroups";
        System.out.println("【Post ReqMsg】" + JSON.toJSONString(req, true));
        String r = httpclient.post(url_manager, JSON.toJSONString(req), "application/json");
        System.out.println("【Post ResMsg】");
        System.out.println(JSON.toJSONString(r, true));
    }
    public static void main(String[] args) {
        run(ProjectBangBangTest.class, 1);
    }
}
