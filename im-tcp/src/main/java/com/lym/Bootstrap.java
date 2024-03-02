package com.lym;


import com.lym.config.BaseConfig;
import com.lym.config.ConfigLoader;
import com.lym.server.NettyServer;

public class Bootstrap {
    public static void main(String[] args) {

        BaseConfig baseConfig = ConfigLoader.getInstants().getBaseConfig();
        NettyServer nettyServer = new NettyServer(baseConfig);
        nettyServer.start();
    }
}
