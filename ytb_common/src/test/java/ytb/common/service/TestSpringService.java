package ytb.common.service;

import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTestInject;
import com.jtest.testframe.ITestImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ytb.common.SpringBootCommon;
import ytb.common.test.demo.ehcache.IEhCacheDemo;

  @RunWith(SpringRunner.class)
  @SpringBootTest(classes = SpringBootCommon.class,  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
  //@WebAppConfiguration
  public class TestSpringService extends ITestImpl {

  //String base_url = XwConst.base_url;
    @JTestInject("iparkDev")
    JDbNode iparkDevDb;
    @JTestInject("iparkTest")
    JDbNode iparkTestDb;
  @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    @Autowired
    IEhCacheDemo ehCacheDemo;

    @Before
    public void setup() {
    }


  @Test
  public void testActMasterServiceIssueCoupon() throws Exception {
    String s = ehCacheDemo.getTimestamp("");

    System.out.println(s);
  }


}
