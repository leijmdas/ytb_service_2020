
package ytb.manager.testcase;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.manager.ManagerCommon.ManagerLogin;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.io.IOException;
import java.util.UUID;

@JTestClass.author("leijm")
public class TestSysMetadata extends ITestImpl {
    String url = "http://localhost/rest/sysmetadata";
    String url_base = "http://localhost:80/rest";

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;
    String token = "c1f119fd8ef24954b2485b6f4625779a";
    ManagerLogin login = new ManagerLogin();

    MsgRequest req = new MsgRequest();
    String data;

    public void suiteSetUp() {
        req.token = token;
    }
    public void suiteTearDown() throws IOException {
    }

    @Override
    public void setUp() {
        token = login.login(req);
        //req.setApiKey(login.getApiKey());
        //req.token = token;
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "projectType";
        req.cmd = "getProjectTypeList";
        req.msgBody = JSONObject.parseObject("{\"x\":1}");


     }

    @Override
    public void tearDown() {

    }

    @JTest
    @JTestClass.title("获取dict_datatype表中的数据")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0001_rest_mngr() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "getDictDataTypeList";

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:80/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("通过输入typeid,返回dataid,和名称")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0002_getTypeAndId() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "getTypeAndIdByTypeId";
        req.msgBody = JSONObject.parseObject("{\"typeId\":1}");

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("根据主键删除dict_datatype表的数据")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void deleteById() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "deleteDictDataTypeById";
        req.msgBody = JSONObject.parseObject("{\"dataInnerId\":3}");

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("deleteById"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("向dict_datatype表中增加一条数据")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void addDictData() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "addDictDataType";
        req.msgBody = JSONObject.parseObject("{\"typeId\":2,\"typeName\":\"字段style\",\"dataName\":\"varchar\",\"dataId\":3}");

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title(" //根据主键修改dict_datatype表中的数据")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void updateDictDataTypeById() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "updateDictDataTypeById";
        req.msgBody = JSONObject.parseObject("{\"typeId\":2,\"typeName\":\"fsdfsdfdsddfsfsd\",\"dataName\":\"varchar\",\"dataId\":1}");

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title(" //获取主表中的所有数据")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void getDictList() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "getDictList";
        req.msgBody = JSONObject.parseObject("{\"metaDataId\":1}");

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title(" //通过主表主键查询数据")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void getDictById() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "getDictById";
        req.msgBody = JSONObject.parseObject("{\"metaDataId\":2}");

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title(" //输入一个数据表名称，返回指定的表信息和字段列表，并按显示顺序排序")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void getDictAndFileds() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "getDictAndFileds";
        req.msgBody = JSONObject.parseObject("{\"metaDataName\":\"user\"}");

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:8080/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title(" //构造树形结构")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void getDictTree() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "getDictTree";
        req.msgBody = JSONObject.parseObject("{\"typeId\":1}");

        data = new Gson().toJson(req).toString();
        System.out.println(data);
        String ret = httpclient.post("http://localhost:8080/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title(" //通过主表主键查询对应的字段信息")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void getFieldListByDictId() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "getFieldListByDictId";
        req.msgBody = JSONObject.parseObject("{\"metaDataId\":1}");

        data = new Gson().toJson(req).toString();
        System.out.println(data);
        String ret = httpclient.post("http://localhost:8080/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title(" //通过从表主键查询")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void getFieldById() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "getFieldById";
        req.msgBody = JSONObject.parseObject("{\"fieldId\":1}");

        data = new Gson().toJson(req).toString();
        System.out.println(data);
        String ret = httpclient.post("http://localhost:8080/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title(" //通过从表主键修改")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void updateFieldById() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "updateFieldById";
        req.msgBody = JSONObject.parseObject("{\"fieldId\":5,\"fieldMemo\":\"字gfdgd段\",\"metaDataId\":1,\"fieldName\":\"myuser\"}");

        data = new Gson().toJson(req).toString();
        System.out.println(data);
        String ret = httpclient.post("http://localhost:8080/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title(" //通过主键删除字段")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void deleteFieldById() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "deleteFieldById";
        req.msgBody = JSONObject.parseObject("{\"fieldId\":5}");

        data = new Gson().toJson(req).toString();
        System.out.println(data);
        String ret = httpclient.post("http://localhost:8080/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title(" //增加从表字段")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void addField() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "addField";
        req.msgBody = JSONObject.parseObject("{\"metaDataId\":1,\"fieldVisible\":1,\"fieldName\":\"user_name\",\"fieldMemo\":\"用戶名\",\"fieldAuto\":0,\"fieldOrder\":1}");

        data = new Gson().toJson(req).toString();
        System.out.println(data);
        String ret = httpclient.post("http://localhost:8080/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title(" //根据数据字典表主键查询")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void getDictDataTypeById() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "getDictDataTypeById";
        req.msgBody = JSONObject.parseObject("{\"dataInnerId\":3}");

        data = new Gson().toJson(req).toString();
        System.out.println(data);
        String ret = httpclient.post("http://localhost:8080/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title(" //增加主表数据")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void addDict() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "addDict";
        req.msgBody = JSONObject.parseObject("{\"metaDataName\":\"usfsfsfsfsffer\",\"metaDataAlias\":\"user\",\"metaDataMemo\":\"用户表\",\"metaDataType\":1,\"metaDataDB\":\"ytb_manager\",\"metaDataAutoCreate\":0}");

        data = new Gson().toJson(req).toString();
        System.out.println(data);
        String ret = httpclient.post("http://localhost:8080/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title(" //修改主表数据")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void updateDictById() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "updateDictById";
        req.msgBody = JSONObject.parseObject("{\"metaDataId\":25,\"metaDataAlias\":\"edsdsdsdsr\",\"metaDataMemo\":\"订单表\",\"metaDataType\":1,\"metaDataDB\":\"ytb_manager\",\"metaDataAutoCreate\":0}");

        data = new Gson().toJson(req).toString();
        System.out.println(data);
        String ret = httpclient.post("http://localhost:8080/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }


    @JTest
    @JTestClass.title(" //生成表")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void makeTableByDictId() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "updateDictById";
        req.msgBody = JSONObject.parseObject("{\"token\":null,\"reqtime\":1536745347946,\"seqno\":1536745347946,\"cmdtype\":\"metadata\",\"cmd\":\"makeTableByDictId\", \"msgBody\": \"{metadataId:45}\" }");

        data = new Gson().toJson(req).toString();
        System.out.println(data);
        String ret = httpclient.post("http://localhost:8080/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }

    @JTest
    @JTestClass.title(" //selectByTable-接口-表数据查询接口")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void selectByTable() {

        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
        req.token = token;
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "selectByTable";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("table","dd");
        data = new Gson().toJson(req).toString();
        System.out.println(data);
        String ret = httpclient.post("http://localhost:80/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.get("msgBody"));
        System.out.println(json);
    }



    @JTest
    @JTestClass.title("testGetSubsys")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void testGetSubsys() {

        req.token = token;//UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "getSubSysDictList";
        req.msgBody = JSONObject.parseObject("{\"subsysId\":1 }");

        data = JSONObject.toJSONString(req);
        String ret = httpclient.post(url, data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json.getJSONObject("msgBody"));
    }

    @JTest
    @JTestClass.title(" //selectByTable-接口-表数据查询接口")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void selectByTablesubsys_dict() {

        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
        req.token = token;
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "selectByTable";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("table","subsys_dict");
        data = JSONObject.toJSONString(req);
        System.out.println(data);
        String ret = httpclient.post("http://localhost:80/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json);
    }
    @JTest
    @JTestClass.title(" //getDictTableAndField-接口-表数据查询接口")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void getDictTableAndField() {

        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
        req.token = token;
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "getDictTableAndField";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("metadataName","work_group_task");
        data = JSONObject.toJSONString(req);
        System.out.println(data);
        String ret = httpclient.post("http://localhost:80/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp=MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp);

    }
    @JTest
    @JTestClass.title(" //getDictTableAndField-接口-表数据查询接口")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void getDictListAndField() {

        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
        req.token = token;
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "getDictListAndField";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("metadataId",67);
        data = JSONObject.toJSONString(req);
        System.out.println(data);
        String ret = httpclient.post("http://localhost:80/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp=MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp);

    }
    @JTest
    @JTestClass.title(" //insertMaster-接口-表数据查询接口")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void insertMaster() {

        //sysmetadata	sysmetadata	sysmetadata	sysmetadata	sysmetadata
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
        req.token = token;
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "dictByInsertSelective";
        req.msgBody = JSONObject.parseObject("{}");

        data = JSONObject.toJSONString(req);
        System.out.println(data);
        String ret = httpclient.post("http://localhost/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp=MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp);
    }

    @JTest
    @JTestClass.title(" //selectByTableProject_plan_total-接口-表数据查询接口-分页")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void selectByTableProject_plan_total () {
        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "selectByTable";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.fluentPut("table","ytb_project.project_plan_total");

        System.out.println(req.toJSONStringPretty());
        String ret = httpclient.post(url, req.toJSONStringPretty(), "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(req.toJSONStringPretty());
        System.out.println(url);
    }


    public static void main(String[] args) {
        run(TestSysMetadata.class, "selectByTableProject_plan_total");

    }
}



