package com.lym.context;

import io.netty.channel.socket.SocketChannel;

import java.util.concurrent.ConcurrentHashMap;

import static com.example.common.constants.Constants.channelIdFunc;

public class GatewayInstanceManager {

    private static ConcurrentHashMap<String, SocketChannel> map = new ConcurrentHashMap<String, SocketChannel>();

    private static final GatewayInstanceManager instance = new GatewayInstanceManager();

    private GatewayInstanceManager() {
    }

    public static GatewayInstanceManager getInstance() {
        return instance;
    }

    public void put(SocketChannel socketChannel) {
        map.put(channelIdFunc.apply(socketChannel), socketChannel);
    }

    public SocketChannel get(String hostPort) {
        return map.get(hostPort);
    }

    public void remove(String hostPort) {
        map.remove(hostPort);
    }
}
