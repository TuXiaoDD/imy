package com.lym.context;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.ConcurrentHashMap;

public class NettyChannelManager {

    private static ConcurrentHashMap<Long, NioSocketChannel> uid2SocketChannelMap = new ConcurrentHashMap<>();
    private static final NettyChannelManager manager = new NettyChannelManager();
    private NettyChannelManager(){}
    public static NettyChannelManager getManager() {
        return manager;
    }


    public void addChannel(Long uid,NioSocketChannel channel){
        uid2SocketChannelMap.put(uid,channel);
    }

    public boolean contains(Long uid){
        return uid2SocketChannelMap.containsKey(uid);
    }
}
