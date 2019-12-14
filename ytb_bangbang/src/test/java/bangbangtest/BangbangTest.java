package bangbangtest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;

import java.sql.SQLException;
import java.util.UUID;

@JTestClass.author("hj")
public class BangbangTest extends ITestImpl {

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;

    private MsgRequest req = new MsgRequest();

    private static final String BANGBANG_URL = "http://localhost/bangbangRest/Ajax/";

    private static final String token = "9e45778e0afa478199e58da9757f64b8";

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
        req.msgBody = JSON.parseObject("{}");
    }

    @JTest
    @JTestClass.title("获取bangbang首页信息记录")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0001_rest_mngr() {
        req.cmdtype = "UserManger";
        req.cmd = "getIndexUserInfos";
        req.msgBody.put("userIddd", 1300);

        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));

        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());
    }

    @JTest
    @JTestClass.title("好友管理>>申请好友")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0002_rest_applyUserFriend() {

//        req.token = UUID.randomUUID().toString();
//        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
//
//        req.seqno = System.currentTimeMillis();
//        req.cmdtype = "FriendsManger";
//        req.cmd = "applyUserFriend";
//        req.msgBody = JSONObject.parseObject("{\"toUserId\":17,\"userId\":13}");
//
//        data = new Gson().toJson(req).toString();
//
//        String ret = httpclient.post(BANGBANG_URL, data, "application/json");
//        httpclient.checkStatusCode(200);
//        JSONObject json = JSONObject.parseObject(ret);
//        checkEQ(0, json.getInteger("retcode"));
//        System.out.println(data);
//        System.out.println(json);
        req.cmdtype = "FriendsManger";
        req.cmd = "applyUserFriend";
        req.msgBody.put("userIdd", 1300);
        req.msgBody.put("toUserId", 356);
        req.msgBody.put("friendsTypeId", 5);
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("好友管理>>获取好友申请记录")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0003_GetApplyUserFriend() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "FriendsManger";
        req.cmd = "GetApplyUserFriend";
        req.msgBody = JSONObject.parseObject("{\"toUserId\":1002}");

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post(BANGBANG_URL, data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("好友管理>>同意申请好友")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0004_rest_isAddFriend() {

//        req.token = UUID.randomUUID().toString();
//        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
//
//        req.seqno = System.currentTimeMillis();
//        req.cmdtype = "FriendsManger";
//        req.cmd = "isAddFriend";
//        req.msgBody = JSONObject.parseObject("{\"friendId\":1300,\"toUserId\":356,\"isAgree\":2,\"friendsTypeId\":7}");
//
//        data = new Gson().toJson(req).toString();
//
//        String ret = httpclient.post(BANGBANG_URL, data, "application/json");
//        httpclient.checkStatusCode(200);
//        JSONObject json = JSONObject.parseObject(ret);
//        checkEQ(0, json.getInteger("retcode"));
//        System.out.println(data);
//        System.out.println(json);
        req.cmdtype = "FriendsManger";
        req.cmd = "isAddFriend";
        req.msgBody.put("inviteId", 19);
        req.msgBody.put("inviteStatus", 2);
        req.msgBody.put("friendsTypeId", 7);
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("好友管理>>删除好友")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0005_rest_deleteUserFriend() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "FriendsManger";
        req.cmd = "deleteUserFriend";
        req.msgBody = JSONObject.parseObject("{\"userId\":13,\"toUserId\":1003}");

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post(BANGBANG_URL, data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("聊天记录")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0006_rest_recordList() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "RecordManger";
        req.cmd = "getRecordList";
        req.msgBody = JSONObject.parseObject("{\"userId\":13,\"id\":1002,\"type\":'friend'}");

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post(BANGBANG_URL, data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("创建群组")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0007_rest_addGroup() {

//        req.token = UUID.randomUUID().toString();
//        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
//
//        req.seqno = System.currentTimeMillis();
//        req.cmdtype = "GroupManger";
//        req.cmd = "addGroup";
//        req.msgBody = JSONObject.parseObject("{\"userId\":13,\"groupName\":\"嘻哈群123\",\"groupType\":2}");
//
//        data = new Gson().toJson(req).toString();
//
//        String ret = httpclient.post(BANGBANG_URL, data, "application/json");
//        httpclient.checkStatusCode(200);
//        JSONObject json = JSONObject.parseObject(ret);
//        checkEQ(0, json.getInteger("retcode"));
//        System.out.println(data);
//        System.out.println(json);
        req.cmdtype = "GroupManger";
        req.cmd = "addGroup";
        req.msgBody.put("groupName", "帮帮学习群");
        req.msgBody.put("groupType", 2);

        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("添加群组成员")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0008_rest_addGroupUser() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "GroupManger";
        req.cmd = "addGroupUser";
        req.msgBody = JSONObject.parseObject("{\"userId\":13,\"groupId\":1,\"groupUserId\":1002}");

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post(BANGBANG_URL, data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("获取群组所有成员")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0009_rest_getGroupUserInfos() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "GroupManger";
        req.cmd = "getGroupUserInfos";
        req.msgBody = JSONObject.parseObject("{\"userId\":13,\"groupId\":1}");

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post(BANGBANG_URL, data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("添加工作成员")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0010_rest_addWorkGroupUser() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "GroupManger";
        req.cmd = "addWorkGroupUser";
        req.msgBody = JSONObject.parseObject("{\"userId\":13,\"groupId\":1,\"groupUserId\":1003}");

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post(BANGBANG_URL, data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("设置组员身份")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0011_rest_setGroupUserType() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "GroupManger";
        req.cmd = "setGroupUserType";
        req.msgBody = JSONObject.parseObject("{\"userId\":13,\"groupId\":1,\"groupUserId\":1002,\"groupUserType\":2}");

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post(BANGBANG_URL, data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("用户管理>>获取用户信息")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0012_rest_GetUserInfo() {

//        req.token = UUID.randomUUID().toString();
//        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
//
//        req.seqno = System.currentTimeMillis();
//        req.cmdtype = "UserManger";
//        req.cmd = "GetUserInfo";
//        req.msgBody = JSONObject.parseObject("{\"userId\":13}");
//
//        data = new Gson().toJson(req).toString();
//
//        String ret = httpclient.post(BANGBANG_URL, data, "application/json");
//        httpclient.checkStatusCode(200);
//        JSONObject json = JSONObject.parseObject(ret);
//        checkEQ(0, json.getInteger("retcode"));
//        System.out.println(data);
//        System.out.println(json);
        req.cmdtype = "UserManger";
        req.cmd = "GetUserInfo";
        req.msgBody.put("friendId", 1301);
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());
    }

    @JTest
    @JTestClass.title("用户管理>>设置用户签名")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0013_rest_getIndexUserInfos() {

//        req.token = UUID.randomUUID().toString();
//        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
//
//        req.seqno = System.currentTimeMillis();
//        req.cmdtype = "UserManger";
//        req.cmd = "setUserSign";
//        req.msgBody = JSONObject.parseObject("{\"userId\":17,\"userSign\":'柳暗花明又一村'}");
//
//        data = new Gson().toJson(req).toString();
//
//        String ret = httpclient.post(BANGBANG_URL, data, "application/json");
//        httpclient.checkStatusCode(200);
//        JSONObject json = JSONObject.parseObject(ret);
//        checkEQ(0, json.getInteger("retcode"));
//        System.out.println(data);
//        System.out.println(json);
        req.cmdtype = "UserManger";
        req.cmd = "setUserSign";
        req.msgBody.put("userId", 17);
        req.msgBody.put("userSign", "柳暗花明又一村");
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("用户管理>>设置用户备注信息")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0014_rest_setUserRemarks() {

//        req.token = UUID.randomUUID().toString();
//        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
//
//        req.seqno = System.currentTimeMillis();
//        req.cmdtype = "UserManger";
//        req.cmd = "setUserRemarks";
//        req.msgBody = JSONObject.parseObject("{\"userId\":13,\"friendId\":1002,\"remarks\":'张杰'}");
//
//        data = new Gson().toJson(req).toString();
//
//        String ret = httpclient.post("http://localhost:80/bangbangRest/Ajax/", data, "application/json");
//        httpclient.checkStatusCode(200);
//        JSONObject json = JSONObject.parseObject(ret);
//        checkEQ(0, json.getInteger("retcode"));
//        System.out.println(data);
//        System.out.println(json);
        req.cmdtype = "UserManger";
        req.cmd = "setUserRemarks";
        req.msgBody.put("userId", 17);
        req.msgBody.put("friendId", 123);
        req.msgBody.put("remarks", "球球");
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());
    }

    @JTest
    @JTestClass.title("群组管理>>查询群组信息")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0015_rest_findGroupByGroupName() {

//        req.token = UUID.randomUUID().toString();
//        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())
//
//        req.seqno = System.currentTimeMillis();
//        req.cmdtype = "GroupManger";
//        req.cmd = "findGroupByGroupName";
//        req.msgBody = JSONObject.parseObject("{\"groupName\":'组'}");
//
//        data = new Gson().toJson(req).toString();
//
//        String ret = httpclient.post("http://localhost:80/bangbangRest/Ajax/", data, "application/json");
//        httpclient.checkStatusCode(200);
//        JSONObject json = JSONObject.parseObject(ret);
//        checkEQ(0, json.getInteger("retcode"));
//        System.out.println(data);
//        System.out.println(json);
        req.cmdtype = "GroupManger";
        req.cmd = "findGroupByGroupName";
        req.msgBody.put("groupName", "工作小组");
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());
    }

    @JTest
    @JTestClass.title("群组管理>>删除群组")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0016_rest_deleteGroup() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "GroupManger";
        req.cmd = "deleteGroup";
        req.msgBody = JSONObject.parseObject("{\"userId\":10002,\"groupId\":13}");

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:80/bangbangRest/Ajax/", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("用户管理>>获取平台所有用户信息")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0017_rest_userInfoPage() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "UserManger";
        req.cmd = "userInfoPage";
        req.msgBody = JSONObject.parseObject("{}");

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:80/bangbangRest/Ajax/", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("群组管理>>删除群组组员或退出群组")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0018_rest_deleteGroupUser() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "GroupManger";
        req.cmd = "deleteGroupUser";
        req.msgBody = JSONObject.parseObject("{\"userId\":13,\"groupUserId\":13,\"groupId\":1}");

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:80/bangbangRest/Ajax/", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("群组管理>>发布公告")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0019_GroupManger_addGroupNotice() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        req.cmdtype = "GroupManger";
        req.cmd = "addGroupNotice";
        req.msgBody.put("groupId", 2934);
        req.msgBody.put("title", "进群须知111");
        req.msgBody.put("noticeMsg", "本群为有托帮分享群，欢迎大家加入！");
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

        dbNode.clearSql().appendSql("select * from group_notice where noticeId=" + msgResponse.getMsgBody().getIntValue("noticeId"));
        dbNode.checkRecordNumber(1);

    }

    @JTest
    @JTestClass.title("群组管理>>获取公告")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0020_rest_getGroupNotice() {

        req.cmdtype = "GroupManger";
        req.cmd = "getGroupNotice";
        req.msgBody.put("groupId", 2934);

        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));

        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());
    }

    @JTest
    @JTestClass.title("群组管理>>删除群组公告")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0021_rest_deleteGroupNotice() {

        req.token = UUID.randomUUID().toString();
        req.reqtime = System.currentTimeMillis();// DateFormat(new Date())

        req.seqno = System.currentTimeMillis();
        req.cmdtype = "GroupManger";
        req.cmd = "deleteGroupNotice";
        req.msgBody = JSONObject.parseObject("{\"userId\":13,\"groupId\":15,\"noticeId\":12}");

        data = new Gson().toJson(req).toString();

        String ret = httpclient.post("http://localhost:80/bangbangRest/Ajax/", data, "application/json");
        httpclient.checkStatusCode(200);
        JSONObject json = JSONObject.parseObject(ret);
        checkEQ(0, json.getInteger("retcode"));
        System.out.println(data);
        System.out.println(json);
    }

    @JTest
    @JTestClass.title("test0022_rest_FriendsManger")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0022_rest_FriendsManger() {

        req.token = token;
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "FriendsManger";
        req.cmd = "getUsersGroups";
        req.msgBody = JSONObject.parseObject("{}");

        String ret = httpclient.post(BANGBANG_URL, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);
        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());

    }

    @JTest
    @JTestClass.title("邦邦-搜索用户")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0023_UserManger_userInfoPage() {
        req.cmdtype = "UserManger";
        req.cmd = "userInfoPage";

        JSONObject reqJsonObj = (JSONObject) JSON.toJSON(req);
        reqJsonObj.put("pageNo", 1);
        reqJsonObj.put("limit", 10);

        System.err.println(JSON.toJSONString(reqJsonObj, SerializerFeature.PrettyFormat));

        String respMsg = httpclient.post(BANGBANG_URL, reqJsonObj.toJSONString(), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());
    }

    @JTest
    @JTestClass.title("好友管理>>获取个人好友分组列表")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0024_rest_getfriendsgrouplist() {
        req.cmdtype = "FriendsManger";
        req.cmd = "getfriendsgrouplist";
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("好友管理>>申请好友消息回复")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0025_rest_answer() {
        req.cmdtype = "FriendsManger";
        req.cmd = "answer";
        req.msgBody.put("inviteId", 29);
//        req.msgBody.put("messageId", 16);
        req.msgBody.put("formUserId", 1301);
        req.msgBody.put("toUserId", 1300);
        req.msgBody.put("content", "OK,我是1300!");

        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("好友管理>>获取好友验证/群系统消息列表接口")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0026_rest_getMsgList() {
        req.cmdtype = "FriendsManger";
        req.cmd = "getMsgList";
        req.msgBody.put("userIdd", 1300);

        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("好友管理>>新建好友分组")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0027_rest_addFriendsType() {
        req.cmdtype = "FriendsManger";
        req.cmd = "delFriendsType";
        req.msgBody.put("friendsTypeId", 8);
//        req.msgBody.put("groupName", "同学");
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("好友管理>>移动好友至某个分组")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0028_rest_reFriend() {
        req.cmdtype = "FriendsManger";
        req.cmd = "reFriend";
        req.msgBody.put("friendId", 196);
        req.msgBody.put("friendsTypeId", 8);
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("查询工作组和兴趣组")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0029_rest_selGroupById() {

        req.cmdtype = "GroupManger";
        req.cmd = "selGroupById";
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());
    }

    @JTest
    @JTestClass.title("搜群-显示群公告")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0030_rest_findGroupsByName() {

        req.cmdtype = "GroupManger";
        req.cmd = "findGroupsByName";
        req.msgBody.put("groupName", "帮帮分享群");
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());
    }

    @JTest
    @JTestClass.title("搜群-显示群公告")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0031_rest_findGroupsByName() {
        req.cmdtype = "FriendsManger";
        req.cmd = "updateStatus";
        req.msgBody.put("msgIdArr", new int[]{41});
        req.msgBody.put("status", 2);
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());
    }

    @JTest
    @JTestClass.title("申请加群")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0032_rest_userApplyGroup() {
        req.cmdtype = "GroupManger";
        req.cmd = "userApplyGroup";
        req.msgBody.put("userIdd", 1300);
        req.msgBody.put("groupId", 1962);
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());
    }


    @JTest
    @JTestClass.title("群组管理>>申请群组消息回复")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0033_rest_answer() {
        req.cmdtype = "GroupManger";
        req.cmd = "answer";
        req.msgBody.put("inviteId", 1);
//        req.msgBody.put("messageId", 16);
        req.msgBody.put("formUserId", 1300);
        req.msgBody.put("toUserId", 17);
        req.msgBody.put("content", "你好,我想入群!");

        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("群组管理>>申请群组 同意/拒绝")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0034_rest_isAddGroup() {
        req.cmdtype = "GroupManger";
        req.cmd = "isAddGroup";
        req.msgBody.put("inviteId", 2);
        req.msgBody.put("inviteStatus", 2);
        req.msgBody.put("groupId", 1962);

        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("聊天记录管理>>单聊-聊天记录")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0035_rest_getUserRecordList() {
        req.cmdtype = "RecordManger";
        req.cmd = "getUserRecordList";
        req.msgBody.put("fromUser", 1300);
        req.msgBody.put("toUser", 1301);
//        req.msgBody.put("beganTime", "2018-10-26 16:56:13");
//        req.msgBody.put("endTime", "2018-11-05 16:56:13");
        req.msgBody.put("type", 2);
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("群聊-显示群人员")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0036_rest_getGroupUsers() {
        req.cmdtype = "GroupManger";
        req.cmd = "getGroupUsers";
        req.msgBody.put("groupId", 1300);
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("聊天记录管理>>群聊-聊天记录")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0037_rest_getGroupRecordList() {
        req.cmdtype = "RecordManger";
        req.cmd = "getGroupRecordList";
        req.msgBody.put("groupId", 72);
        req.msgBody.put("type", 1);
//        req.msgBody.put("beganTime", "2018-10-26 16:58:13");
//        req.msgBody.put("endTime", "2018-11-05 16:56:13");
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("添加群分组")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0038_rest_addGroupType() {
        req.cmdtype = "GroupManger";
        req.cmd = "addGroupType";
        req.msgBody.put("groupName", "工作群聊");
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("重命名群分组")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0039_rest_editGroupType() {
        req.cmdtype = "GroupManger";
        req.cmd = "editGroupType";
        req.msgBody.put("groupTypeId", 3);
        req.msgBody.put("groupName", "工作-生活群聊");
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("删除群分组")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0040_rest_delGroupType() {
        req.cmdtype = "GroupManger";
        req.cmd = "delGroupType";
        req.msgBody.put("groupTypeId", 3);
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("申请加群-->搜索群")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0041_rest_searchGroup() {
        req.cmdtype = "GroupManger";
        req.cmd = "searchGroup";
//        req.msgBody.put("groupName", "帮帮学习群");
        req.msgBody.put("pageNo", 1);
        req.msgBody.put("limit", 10);
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("申请加群-->查看群资料")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0042_rest_ViewingGroupData() {
        req.cmdtype = "GroupManger";
        req.cmd = "ViewingGroupData";
        req.msgBody.put("groupId", 1300);
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }

    @JTest
    @JTestClass.title("申请加群-->群主拉人进群")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/bangbangRest/Ajax")
    @JTestClass.exp("ok")
    public void test0043_rest_ppinGroups() {
        req.cmdtype = "GroupManger";
        req.cmd = "ppinGroups";
        req.msgBody.put("groupId", 1962);
        req.msgBody.put("userIdArr", new int[]{1301, 1300});
        System.err.println(JSON.toJSONString(req, SerializerFeature.PrettyFormat));
        String respMsg = httpclient.post(BANGBANG_URL, JSON.toJSONString(req), "application/json");
        MsgResponse msgResponse = MsgResponse.parseResponse(respMsg);

        System.err.println(JSON.toJSONString(msgResponse, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));

        httpclient.checkStatusCode(200);
        checkEQ(0, msgResponse.getRetcode());

    }


    public static void main(String[] args) {
        run(BangbangTest.class, 19);
    }
}
