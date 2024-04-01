package com.lym.context;

import io.netty.channel.socket.SocketChannel;

import java.util.concurrent.ConcurrentHashMap;

import static com.example.common.constants.Constants.socketKeyFunc;

public class GatewayManager {

    private static ConcurrentHashMap<String, SocketChannel> map = new ConcurrentHashMap<String, SocketChannel>();

    private static final GatewayManager instance = new GatewayManager();

    private GatewayManager() {
    }

    public static GatewayManager getInstance() {
        return instance;
    }

    public void put(SocketChannel socketChannel) {
        map.put(socketKeyFunc.apply(socketChannel), socketChannel);
    }

    public SocketChannel get(String hostPort) {
        return map.get(hostPort);
    }

    public void remove(String hostPort) {
        map.remove(hostPort);
    }
}
