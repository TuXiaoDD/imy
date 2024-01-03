package com.imtcp;


import com.imtcp.config.BaseConfig;
import com.imtcp.config.ConfigLoader;
import com.imtcp.server.NettyServer;

public class Bootstrap {
    public static void main(String[] args) {

        BaseConfig baseConfig = ConfigLoader.getInstants().getBaseConfig();
        NettyServer nettyServer = new NettyServer(baseConfig);
        nettyServer.start();
    }
}
