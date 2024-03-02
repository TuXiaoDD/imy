package com.lym.context;

import io.netty.channel.socket.SocketChannel;

import java.util.concurrent.ConcurrentHashMap;

public class GatewayManager {

    private static ConcurrentHashMap<String, SocketChannel> map = new ConcurrentHashMap<String, SocketChannel>();

    private static final GatewayManager instance = new GatewayManager();

    private GatewayManager() {
    }

    public static GatewayManager getInstance() {
        return instance;
    }

    public void put(SocketChannel socketChannel) {
        map.put(socketChannel.id().asLongText(), socketChannel);
    }

    public SocketChannel get(String channelId) {
        return map.get(channelId);
    }

    public void remove(String channelId) {
        if (map.containsKey(channelId))
            map.remove(channelId);
    }
}
