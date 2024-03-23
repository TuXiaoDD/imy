package com.lym.client;

import com.example.common.LifeCycle;
import com.lym.entity.DispatcherInstanceInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DispatcherInstanceClient implements LifeCycle {


    private final DispatcherInstanceInfo dispatcherInstanceInfo;
    private Bootstrap bootstrap;
    private EventLoopGroup eventLoopGroup;
    private SocketChannel socketChannel;

    public DispatcherInstanceClient(DispatcherInstanceInfo instance) {
        this.dispatcherInstanceInfo = instance;
        init();
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    @Override
    public void init() {
        this.bootstrap = new Bootstrap();
        this.eventLoopGroup = new NioEventLoopGroup();
    }

    @Override
    public void start() {
        ChannelFuture channelFuture = bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new DispatcherInstanceHandler());
                    }
                }).connect(this.dispatcherInstanceInfo.getHost(), this.dispatcherInstanceInfo.getPort());
        try {
            socketChannel = (SocketChannel) channelFuture.channel();
            socketChannel.closeFuture();
            log.info("连接dispatcherInstance成功 {}", socketChannel.id());
//            channelFuture.addListener(new ChannelFutureListener() {
//                        @Override
//                        public void operationComplete(ChannelFuture channelFuture) throws Exception {
//                            if (channelFuture.isSuccess()) {
//
//                                log.info("client connect");
//                            } else {
//                                channelFuture.channel().close();
//                                shutdown();
//                            }
//                        }
//                    }).channel().closeFuture()
//                    .sync()
            ;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    @Override
    public void shutdown() {
        if (this.socketChannel != null) {
            this.socketChannel.close();
        }
        if (this.eventLoopGroup != null) {
            this.eventLoopGroup.shutdownGracefully();
        }

    }
}
