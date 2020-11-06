package com.deepazure.visualdata.config;

import com.deepazure.visualdata.util.Message;
import com.deepazure.visualdata.util.Observable;
import com.deepazure.visualdata.util.Observer;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Component
@ServerEndpoint("/websocket/{username}/{sid}")
public class WebSocketServer implements Observable {

    public static int onlineCount = 0;

    public static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    private static final List<Observer> observers = new ArrayList<>();

    private static final Gson gson = new Gson();

    private Session session;

    private String sid = "";

    private String username = "";

    private boolean isValid = true;

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username, @PathParam("sid") String sid) throws IOException {

        // 校验sid是否重复
        for (WebSocketServer server : webSocketSet) {
            if (sid.equals(server.sid)) {
                log.warn("同一sid只能有一个连接！");
                isValid = false;
                break;
            }
        }

        this.sid = sid;
        this.username = username;
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        if (!isValid) {
            session.close();
            return;
        }
        log.info("有客户端上线: [" + sid + "], 当前在线人数为: " + getOnlineCount());
        sendOnlineInfo();
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        if (!isValid) return;

        Message msg = new Message();
        msg.setSid(sid);
        msg.setType("offline");

        sendInfo(gson.toJson(msg), null);
        log.info("有客户端断开连接！当前在线人数为: " + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自[" + sid + "]的信息: " + message);
        //群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        notifyOn(message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("Websocket Server 发生错误, " + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 主动推送群消息
     *
     * @param message
     * @param sid
     */
    public static void sendInfo(String message, @PathParam("sid") String sid) {
        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if (sid == null) {
                    log.info("推送群体消息, " + message);
                    item.sendMessage(message);
                } else if (item.sid.equals(sid)) {
                    log.info("推送消息到窗口[" + sid + "]，推送内容:" + message);
                    item.sendMessage(message);
                    break;
                }
            } catch (IOException e) {
                log.error("消息推送异常, 目标: [" + sid + "], 异常消息: " + e.getMessage());
            }
        }
    }

    /**
     * 服务器主动推送
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return webSocketSet;
    }

    public static void sendOnlineInfo() {
        Message msg = new Message();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (WebSocketServer item : webSocketSet) {
            sb.append(item.getSid())
                    .append(",");
            sb2.append(item.getUsername())
                    .append(",");
        }
        if (sb.length() > 0) {
            msg.setSid(sb.substring(0, sb.length() - 1));
        }
        if (sb2.length() > 0) {
            msg.setUsername(sb2.substring(0, sb2.length() - 1));
        }
        msg.setType("online");
        String json = gson.toJson(msg);
        sendInfo(json, "0");
    }

    public String getSid() {
        return sid;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void remove(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyOn(Message msg) {
        observers.forEach(observer -> observer.update(msg));
    }

    @Override
    public void notifyOn(String msg) {
        observers.forEach(observer -> observer.update(msg));
    }
}
