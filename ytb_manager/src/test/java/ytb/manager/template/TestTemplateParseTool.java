package ytb.manager.template;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.abel533.sql.SqlMapper;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.manager.ManagerCommon.ManagerConst;
import ytb.manager.context.MyBatisUtil;
import ytb.manager.template.model.Dict_document;
import ytb.manager.templateexcel.service.impl.TemplateDocumentServiceImpl;
import ytb.common.RestMessage.MsgRequest;

import java.util.Date;

/**
 * @author lxz 2019/2/12 16:22
 */
public class TestTemplateParseTool extends ITestImpl {

    @Inject(filename = "node.xml", value = "httpclient")
    private HttpClientNode httpClient;

    private MsgRequest req = new MsgRequest();

    private String urlProjectTemplate = ManagerConst.url_template;

    @Inject("ytb_manager")
    private JDbNode managerDB;

    @Override
    public void setUp() throws Exception {
        req.token = "15373258c3924df0b1c4836f521c4ef1";
        long l = System.currentTimeMillis();
        req.reqtime = l;
        req.seqno = l;
        req.cmdtype = "";
        req.cmd = "";
        req.msgBody = JSONObject.parseObject("{}");
    }

    @JTest
    @JTestClass.title("TestTemplateParseTool.test0001_TemplateDocumentService_parseExcel")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0001_TemplateDocumentService_parseExcel() throws Exception {
        boolean b = TemplateDocumentServiceImpl.getTemplateDocumentService().parseExcel(549);
        checkEQ(true, b);
        SqlMapper sqlMapper = new SqlMapper(MyBatisUtil.getSession());
        Dict_document document = sqlMapper.selectOne("select * from dict_template t,dict_document d where t.document_id=d.document_id and t.template_id=549", Dict_document.class);
        System.out.println(JSON.toJSONString(document, SerializerFeature.PrettyFormat));
    }

    @JTest
    @JTestClass.title("TestTemplateParseTool.test0002_test_long_cast_string")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0002_test_long_cast_string() {
        Date date = new Date();
        String s = String.valueOf(date.getTime());
        System.out.println(s);
    }

    @JTest
    @JTestClass.title("TestTemplateParseTool.test0002_test_long_cast_string")
    @JTestClass.pre("")
    @JTestClass.step("/rest/template")
    @JTestClass.exp("ok")
    public void test0003_test_long_cast_string() {
        req.cmdtype = "projType";
        req.cmd = "parseDocTemplate";
    }


    public static void main(String[] args) {
        run(TestTemplateParseTool.class, 3);
    }

}
