package com.lym.context;

import com.lym.client.ConnectDispatcherInstanceClient;
import com.lym.entity.DispatcherInstance;
import com.lym.entity.DispatcherInstanceInfo;
import io.netty.channel.socket.SocketChannel;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DispatcherInstanceManager {
    private DispatcherInstanceManager() {
    }

    private final static DispatcherInstanceManager instance = new DispatcherInstanceManager();

    public static DispatcherInstanceManager getInstance() {
        return instance;
    }

    private List<DispatcherInstanceInfo> dispatcherInstanceInfoList
            = List.of(new DispatcherInstanceInfo("localhost", "127.0.0.1", 9000));

    private List<DispatcherInstance> dispatcherInstanceList = new CopyOnWriteArrayList<>();

    public void connectDispatcherInstances() {
        if (dispatcherInstanceInfoList == null) return;
        for (DispatcherInstanceInfo instance : dispatcherInstanceInfoList) {
            ConnectDispatcherInstanceClient connectDispatcherInstanceClient = new ConnectDispatcherInstanceClient(instance);
            connectDispatcherInstanceClient.start();
            SocketChannel socketChannel = connectDispatcherInstanceClient.getSocketChannel();
            if (socketChannel==null){
                //todo handler err
                continue;
            }
            dispatcherInstanceList.add(new DispatcherInstance(socketChannel));
        }
    }

    public DispatcherInstance chooseInstance(){
        return dispatcherInstanceList.get(0);
    }

}
