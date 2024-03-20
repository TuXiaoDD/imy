package com.lym;


import com.lym.config.BaseConfig;
import com.lym.config.ConfigLoader;
import com.lym.context.DispatcherInstanceManager;
import com.lym.server.GatewayTcpServer;

public class ImTCPServer {
    public static void main(String[] args) {

        BaseConfig baseConfig = ConfigLoader.getInstants().getBaseConfig();

        // 和分发系统建立连接
        DispatcherInstanceManager instance = DispatcherInstanceManager.getInstance();
        instance.connectDispatcherInstances();
        GatewayTcpServer gatewayTcpServer = new GatewayTcpServer(baseConfig);
        gatewayTcpServer.start();
    }
}
