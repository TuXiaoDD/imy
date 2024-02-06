package com.imtcp.context;

import io.netty.channel.Channel;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    private static ConcurrentHashMap<Long, Channel> uid2SocketChannelMap = new ConcurrentHashMap<>();

    public static void addSession(Long uid, Channel channel) {
        uid2SocketChannelMap.put(uid, channel);
    }

    public static void removeSession(Long uid) {
        uid2SocketChannelMap.remove(uid);
    }


    public static Channel getChannel(Long uid) {
        return uid2SocketChannelMap.get(uid);
    }
}
