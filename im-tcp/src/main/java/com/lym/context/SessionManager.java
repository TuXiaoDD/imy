package com.lym.context;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class SessionManager {
    private SessionManager() {
    }

    private static final SessionManager instance = new SessionManager();

    public static SessionManager getInstance() {
        return instance;
    }

    private ConcurrentHashMap<Long, SocketChannel> uid2SocketChannelMap = new ConcurrentHashMap<>();

    public void addSession(Long uid, SocketChannel channel) {
        log.info("addSession {} {}", uid, channel.id());
        uid2SocketChannelMap.put(uid, channel);
    }

    public void removeSession(Long uid) {
        uid2SocketChannelMap.remove(uid);
    }


    public SocketChannel getChannel(Long uid) {
        return uid2SocketChannelMap.get(uid);
    }
}
