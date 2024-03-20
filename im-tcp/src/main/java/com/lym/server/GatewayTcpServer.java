package com.lym.server;

import com.example.common.LifeCycle;
import com.lym.config.BaseConfig;

import com.lym.handler.NettyConnectionHandler;
import com.lym.handler.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class GatewayTcpServer implements LifeCycle {

    private int port = 9999;
    private BaseConfig baseConfig;

    private ServerBootstrap serverBootstrap;

    private EventLoopGroup bossEventLoopGroup;
    private EventLoopGroup workEventLoopGroup;

    public GatewayTcpServer(BaseConfig baseConfig) {
        this.baseConfig = baseConfig;
        if (this.baseConfig.getPort() > 0 && this.baseConfig.getPort() < 65535) {
            this.port = this.baseConfig.getPort();
        }
        init();
    }


    @Override
    public void init() {
        serverBootstrap = new ServerBootstrap();
        //todo epoll
        bossEventLoopGroup = new NioEventLoopGroup(baseConfig.getBossGroupNum());
        workEventLoopGroup = new NioEventLoopGroup(baseConfig.getWorkGroupNum());
    }

    @Override
    public void start() {
        serverBootstrap.group(bossEventLoopGroup, workEventLoopGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)// boss accept
                .option(ChannelOption.SO_REUSEADDR, true)// tcp端口重绑定
                .option(ChannelOption.SO_KEEPALIVE, false)
                .childOption(ChannelOption.TCP_NODELAY, true)//NODELA算法小数据合并
                .childOption(ChannelOption.SO_RCVBUF, 65535)
                .childOption(ChannelOption.SO_SNDBUF, 65535)
                .localAddress(new InetSocketAddress(this.port))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
//                                .addLast(new MessageDecoder())
//                                .addLast(new MessageEncoder())
                                .addLast(new NettyConnectionHandler())
                                .addLast(new NettyServerHandler());
                    }
                });
        try {
            serverBootstrap.bind().sync();
            log.info("NettyServer start success, port: {}", this.port);
        } catch (InterruptedException e) {
            log.error("NettyServer start error", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void shutdown() {
        if (bossEventLoopGroup != null) {
            bossEventLoopGroup.shutdownGracefully();
        }
        if (workEventLoopGroup != null) {
            workEventLoopGroup.shutdownGracefully();
        }
    }
}
