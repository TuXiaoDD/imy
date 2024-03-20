package com.lym.context;

import com.lym.client.ConnectDispatcherInstanceClient;
import com.lym.entity.DispatcherInstance;
import io.netty.channel.socket.SocketChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DispatcherInstanceManager {
    private DispatcherInstanceManager() {
    }

    private final static DispatcherInstanceManager instance = new DispatcherInstanceManager();

    public static DispatcherInstanceManager getInstance() {
        return instance;
    }

    private List<DispatcherInstance> dispatcherInstanceList
            = List.of(new DispatcherInstance("localhost", "127.0.0.1", 9000));

    private List<SocketChannel> socketChannels = new CopyOnWriteArrayList<>();

    public void connectDispatcherInstances() {
        if (dispatcherInstanceList == null) return;
        for (DispatcherInstance instance : dispatcherInstanceList) {
            ConnectDispatcherInstanceClient connectDispatcherInstanceClient = new ConnectDispatcherInstanceClient(instance);
            connectDispatcherInstanceClient.start();
            socketChannels.add(connectDispatcherInstanceClient.getSocketChannel());
        }
    }

}
