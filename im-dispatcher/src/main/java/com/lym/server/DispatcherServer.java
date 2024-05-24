package com.lym.server;

import com.example.common.LifeCycle;
import com.lym.handler.DispatcherHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DispatcherServer implements LifeCycle {

    private ServerBootstrap bootstrap;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;

    private String host;
    private int port;

    public DispatcherServer(String host, int port) {
        this.host = host;
        this.port = port;
        init();
    }


    public void init() {
        this.bossGroup = new NioEventLoopGroup();
        this.workGroup = new NioEventLoopGroup();
        this.bootstrap = new ServerBootstrap();
    }

    public void start() {
        ChannelFuture channelFuture = this.bootstrap
                .group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new DispatcherHandler())
                        ;
                    }
                }).bind(this.host, this.port);
        try {
            log.info("分发系统已启动");
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("err ",e);
        }

    }

    public void shutdown() {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workGroup != null) {
            workGroup.shutdownGracefully();
        }
    }
}
