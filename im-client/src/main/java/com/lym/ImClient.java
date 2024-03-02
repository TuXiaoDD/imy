package com.lym;

import com.example.common.LifeCycle;
import com.lym.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImClient implements LifeCycle {

    private int port;
    private String host;
    private Bootstrap bootstrap;
    private EventLoopGroup eventLoopGroup;

    private SocketChannel socketChannel;

    public ImClient(String host, int port) {
        this.host = host;
        this.port = port;
        init();
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
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                }).connect(this.host, this.port);
        try {
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        socketChannel = (SocketChannel) channelFuture.channel();
                        log.info("client connect");
                    } else {
                        channelFuture.channel().close();
                        shutdown();
                    }
                }
            }).channel().closeFuture()
//                    .sync()
            ;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void auth(Long uid, String token) {
        String msg = "auth:" + token + ":" + uid;
        byte[] bytes1 = msg.getBytes();
        ByteBuf byteBuf1 = Unpooled.copiedBuffer(bytes1);
        socketChannel.writeAndFlush(byteBuf1);
        log.info("auth {}",msg);
    }
    public void send(String msg){
        byte[] bytes1 = msg.getBytes();
        ByteBuf byteBuf1 = Unpooled.copiedBuffer(bytes1);
        socketChannel.writeAndFlush(byteBuf1);
        log.info("send {}",msg);

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
