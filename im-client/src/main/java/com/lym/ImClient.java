package com.lym;

import com.example.common.LifeCycle;
import com.example.common.constants.Constants;
import com.example.common.enums.MessageType;
import com.example.common.enums.RequestType;
import com.lym.handler.ClientHandler;
import com.lym.protobuf.AuthenticateRequestProto;
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
                    protected void initChannel(SocketChannel socketChannel) {
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                }).connect(this.host, this.port);
        try {
            channelFuture.addListener((ChannelFutureListener) channelFuture1 -> {
                if (channelFuture1.isSuccess()) {
                    socketChannel = (SocketChannel) channelFuture1.channel();
                    log.info("client connect {}",socketChannel.id());
                } else {
                    channelFuture1.channel().close();
                    shutdown();
                }
            }).channel().closeFuture()
//                    .sync()
            ;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void auth(Long uid, String token) {
        AuthenticateRequestProto.AuthenticateRequest.Builder builder = new AuthenticateRequestProto.AuthenticateRequest.Builder();
        builder.setUid(String.valueOf(uid));
        builder.setToke(token);
        builder.setTimestamp(System.currentTimeMillis());
        AuthenticateRequestProto.AuthenticateRequest authenticateRequest = builder.build();

        byte[] body = authenticateRequest.toByteArray();

        ByteBuf byteBuf = Unpooled.buffer(Constants.request_header_length + body.length);
        // 整个消息头的长度
        byteBuf.writeInt(Constants.request_header_length);
        // app 版本号
        byteBuf.writeInt(Constants.app_sdk_version);
        // 授权 请求
        byteBuf.writeInt(MessageType.AUTH.getValue());
        // 消息序列号
        byteBuf.writeInt(Constants.request_sequence_default);
        byteBuf.writeInt(RequestType.REQUEST.getVal());

        byteBuf.writeInt(body.length);

        byteBuf.writeBytes(body);
        socketChannel.writeAndFlush(byteBuf);
        log.info("auth {}", new String(body));
    }

    public void send(String msg) {
        byte[] body = msg.getBytes();

        ByteBuf byteBuf = Unpooled.buffer(Constants.request_header_length + body.length);
        // 整个消息头的长度
        byteBuf.writeInt(Constants.request_header_length);
        // app 版本号
        byteBuf.writeInt(Constants.app_sdk_version);
        // 授权 请求
        byteBuf.writeInt(MessageType.TEXT_MSG.getValue());
        // 消息序列号
        byteBuf.writeInt(Constants.request_sequence_default);
        byteBuf.writeInt(RequestType.REQUEST.getVal());
        byteBuf.writeInt(body.length);
        byteBuf.writeBytes(body);
        socketChannel.writeAndFlush(byteBuf);
        log.info("send {}", msg);
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
