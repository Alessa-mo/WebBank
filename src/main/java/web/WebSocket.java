package web;

import com.alibaba.fastjson.JSON;
import pojo.User;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//用户链接对象类
@ServerEndpoint("/websocket")
public class WebSocket {
    public static final Map<WebSocket, String> clients = new ConcurrentHashMap<>();
    public static final Map<String,WebSocket> U2W = new HashMap<String,WebSocket>();
    private Session session;
    private Processor processor = new Processor();

    public WebSocket() throws IOException {
    }

    @OnOpen
    public void onOpen(Session session) throws IOException
    {
        this.session = session;
        clients.put(this, "*unnamed");
        System.out.println("新用户已连接,webSocket:" + this);
    }

    @OnClose
    public void onClose() throws IOException {
        clients.remove(this);
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        System.out.println("收到message:\n" + message);
        processor.setMessage(JSON.parseObject(message));
        String res = processor.parseMessage(this);
        sendEcho(res);
    }

    public void sendEcho(String message) throws IOException
    {
//        String text = JSON.toJSONString(message);
        System.out.println("发回消息:\n" + message);
        session.getBasicRemote().sendText(message);
    }

    public void PushMessage(String message) throws IOException
    {
        System.out.println("推送消息:\n" + message);
        session.getBasicRemote().sendText(message);
    }
}
