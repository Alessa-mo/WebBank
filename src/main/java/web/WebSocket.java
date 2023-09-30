package web;

import com.alibaba.fastjson.JSON;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//用户链接对象类
@ServerEndpoint("/websocket")
public class WebSocket {
    private static final Map<WebSocket, String> clients = new ConcurrentHashMap<>();
    private Session session;
    private Processor processor = new Processor();

    public WebSocket() throws IOException {
    }

    @OnOpen
    public void onOpen(Session session) throws IOException {
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
        String res = processor.parseMessage();
        sendEcho(res);
    }

    public void sendEcho(String message) throws IOException {
//        String text = JSON.toJSONString(message);
        System.out.println("发回消息:\n" + message);
        session.getBasicRemote().sendText(message);
    }
}
