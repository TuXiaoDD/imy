package com.lym.context;

import com.lym.client.DispatcherInstanceClient;
import com.lym.entity.DispatcherInstance;
import com.lym.entity.DispatcherInstanceInfo;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class DispatcherInstanceManager {
    private DispatcherInstanceManager() {
    }

    private final static DispatcherInstanceManager instance = new DispatcherInstanceManager();

    public static DispatcherInstanceManager getInstance() {
        return instance;
    }

    private List<DispatcherInstanceInfo> dispatcherInstanceInfoList
            = List.of(
            new DispatcherInstanceInfo("localhost", 9000),
            new DispatcherInstanceInfo("127.0.0.1", 9001)
    );

    private List<DispatcherInstance> dispatcherInstanceList = new CopyOnWriteArrayList<>();

    public void connectDispatcherInstances() {
        if (dispatcherInstanceInfoList == null) return;
        for (DispatcherInstanceInfo instance : dispatcherInstanceInfoList) {
            DispatcherInstanceClient dispatcherInstanceClient = new DispatcherInstanceClient(instance);
            dispatcherInstanceClient.start();
            SocketChannel socketChannel = dispatcherInstanceClient.getSocketChannel();
            if (socketChannel == null) {
                //todo handler err
                continue;
            }
            dispatcherInstanceList.add(new DispatcherInstance(socketChannel));
        }
    }

    public DispatcherInstance chooseInstance() {
        Random random = new Random();
        int i = random.nextInt(dispatcherInstanceList.size());
        DispatcherInstance dispatcherInstance = dispatcherInstanceList.get(i);
        log.info("chooseInstance {}", dispatcherInstance.getSocketChannel().id());
        return dispatcherInstance;
    }

}
