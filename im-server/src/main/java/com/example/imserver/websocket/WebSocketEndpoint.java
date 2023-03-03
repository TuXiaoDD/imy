package com.example.imserver.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/wss/default.io/{token}")
@Slf4j
public class WebSocketEndpoint {

    private Session session;
    private String token;

    private static Map<String, WebSocketEndpoint> clients = new ConcurrentHashMap<>();


    @OnOpen
    public void onOpen(@PathParam("token") String token, Session session) {
        this.session = session;
        this.token = token;
        clients.put(this.token, this);
        log.info("websocket onOpen");
    }


    @OnClose
    public void onClose() {
        log.info("websocket onClose");
        if(clients.containsKey(this.token)){
            clients.remove(this.token);
        }
    }


    @OnMessage
    public void onMessage(String msg) {
        clients.forEach((token, client) -> {
            if (Objects.equals(this.token, token)) {
                client.session.getAsyncRemote().sendText(msg);
            }
        });
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.info("websocket onError");
    }


}
