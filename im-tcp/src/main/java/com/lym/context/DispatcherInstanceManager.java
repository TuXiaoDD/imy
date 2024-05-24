package com.lym.context;

import com.lym.client.DispatcherInstanceClient;
import com.lym.entity.DispatcherInstance;
import com.lym.entity.DispatcherInstanceInfo;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.example.common.constants.Constants.channelIdFunc;

/**
 * 连接断开要移除
 */
@Slf4j
public class DispatcherInstanceManager {
    private DispatcherInstanceManager() {
    }

    private final static DispatcherInstanceManager instance = new DispatcherInstanceManager();

    public static DispatcherInstanceManager getInstance() {
        return instance;
    }

    // 分发系统地址列表
    private static List<DispatcherInstanceInfo> dispatcherInstanceInfoList = new CopyOnWriteArrayList<>();

    static {
        dispatcherInstanceInfoList.add(new DispatcherInstanceInfo("localhost", 9000));
        dispatcherInstanceInfoList.add(new DispatcherInstanceInfo("127.0.0.1", 9001));
    }

    // 分发系统实例
    private Map<String, DispatcherInstance> dispatcherInstanceMap = new ConcurrentHashMap<>();


    public void connectDispatcherInstances() {
        if (dispatcherInstanceInfoList == null) return;
        for (DispatcherInstanceInfo instance : dispatcherInstanceInfoList) {
            DispatcherInstanceClient dispatcherInstanceClient = new DispatcherInstanceClient(instance);
            dispatcherInstanceClient.start();
            SocketChannel channel = dispatcherInstanceClient.getSocketChannel();
            if (channel == null) {
                //todo handler err
                continue;
            }
            String dispatcherId = channelIdFunc.apply(channel);
            dispatcherInstanceMap.put(dispatcherId, new DispatcherInstance(channel));
        }
    }

    public DispatcherInstance chooseInstance() {
        List<DispatcherInstance> dispatcherInstanceList = new ArrayList<>(dispatcherInstanceMap.values());
        Random random = new Random();
        int i = random.nextInt(dispatcherInstanceList.size());
        DispatcherInstance dispatcherInstance = dispatcherInstanceList.get(i);
        log.info("chooseInstance {}", dispatcherInstance.getSocketChannel().id());
        return dispatcherInstance;
    }

    public void remove(String channelId) {
        dispatcherInstanceMap.remove(channelId);
    }

    public void put(String channelId, DispatcherInstance instance) {
        dispatcherInstanceMap.put(channelId, instance);
    }


}
