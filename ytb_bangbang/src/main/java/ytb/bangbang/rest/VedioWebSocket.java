package ytb.bangbang.rest;


import com.alibaba.fastjson.JSONObject;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/vedioWebSocket/{userId}")
public class VedioWebSocket {

    private static int onlineCount = 0;
    private static ConcurrentHashMap<String, Session> mapUS = new ConcurrentHashMap<String, Session>();
    private static ConcurrentHashMap<Session, String> mapSU = new ConcurrentHashMap<Session, String>();
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public synchronized void onOpen(Session session, @PathParam("userId") String userId) {

        if(null!=mapUS.get(userId)){
            mapUS.remove(userId);
            mapSU.remove(session,userId);
        }
        mapUS.put(userId, session);
        mapSU.put(session,userId);
        //上线通知由客户端自主发起
        onlineCount++;           //在线数加1
        //System.out.println("用户" + userId + "进入！VW当前在线人数为" + onlineCount);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public synchronized void onClose(Session session) {
        String userId = mapSU.get(session);
        onlineCount--;           //在线数减1
        //System.out.println("用户" + userId + "退出！VW当前在线人数为" + onlineCount);
            mapUS.remove(userId);
            mapSU.remove(session);
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
       //消息格式{to:"",type:"",客户端键值}
        System.out.println("来自客户端的消息:" + message);
        JSONObject jsonObject = JSONObject.parseObject(message);
        String type = jsonObject.getString("type");
        String toIdStr = jsonObject.getString("to");//接收消息的id
        int toId=0;
        try {
            toId =Integer.parseInt(toIdStr);
        }catch (Exception e){
            e.printStackTrace();
           return;
        }

        switch (type) {

            default:
                Session session_to = mapUS.get(toIdStr);
                if(null !=session_to) {
                    try {
                        session_to.getBasicRemote().sendText(jsonObject.toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    session.getAsyncRemote().sendText("{\"type\":\"notfound\"}");
                }
                break;
        }
    }


    /**
     * 发生错误时调用
     *
     * @param
     * @param error
     */
    @OnError
    public void onError(Throwable error) {
        System.out.println("ws发生错误");
        error.printStackTrace();
    }

}
