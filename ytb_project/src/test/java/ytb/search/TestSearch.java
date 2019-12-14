package ytb.search;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.RestMessage.MsgRequest;

import java.io.IOException;
import java.util.UUID;

/**
 * @Author hj
 * @Description //TODO
 * @Date 2018/11/20
 **/
@JTestClass.author("hj")
public class TestSearch extends ITestImpl {
    String url_base="http://project.youtobon.com/rest/projectCenter";
    String token="b79374ab55324e9385ba70e64f1e1640";

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;

    MsgRequest req = new MsgRequest();
    String data;
    @JTest
    @JTestClass.title("供应商商品类别")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/projectCenter")
    @JTestClass.exp("ok")
    public void test0001_goodsType() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "supplierCommodity";
        req.cmd = "goodsType";
        req.msgBody = JSONObject.parseObject("{parentId:0}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
    }
    @JTest
    @JTestClass.title("供应商商品搜索")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/projectCenter")
    @JTestClass.exp("ok")
    public void test0002_goodsSearch() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "supplierCommodity";
        req.cmd = "SearchGoodsList";
        req.msgBody = JSONObject.parseObject("{creditGrade:'B'}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
    }
    @JTest
    @JTestClass.title("采购商商品类别")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/projectCenter")
    @JTestClass.exp("ok")
    public void test0003_goodsType() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "purchaseGoods";
        req.cmd = "goodsType";
        req.msgBody = JSONObject.parseObject("{parentId:0}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
    }
    @JTest
    @JTestClass.title("采购商商品搜索")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/projectCenter")
    @JTestClass.exp("ok")
    public void test0004_goodsSearch() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "purchaseGoods";
        req.cmd = "purchaseGoodsList";
        req.msgBody = JSONObject.parseObject("{creditGrade:'B'}");
        data=new Gson().toJson(req).toString();
        String ret = httpclient.post(url_base,data , "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json=JSONObject.parseObject(ret);
        checkEQ(0,json.getInteger("retcode"));
    }
    public static void main(String[] args) throws IOException {
        run(TestSearch.class, "test0001_goodsType");
    }
}
