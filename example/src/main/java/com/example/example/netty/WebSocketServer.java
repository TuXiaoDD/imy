package com.example.example.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import javax.annotation.PreDestroy;

public class WebSocketServer {

    private static final int READ_IDLE_TIME = 60; // 读空闲时间（秒）
    private static final int WRITE_IDLE_TIME = 20; // 写空闲时间（秒）
    private static final int ALL_IDLE_TIME = 0; // 所有空闲时间（秒）

    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private ServerBootstrap serverBootstrap;

    private final int port;

    //构造方法
    public WebSocketServer(int port) {
        this.port = port;
        this.bossGroup = new NioEventLoopGroup(1);
        this.workGroup = new NioEventLoopGroup();
        this.serverBootstrap = new ServerBootstrap();
    }


    //启动方法
    public void start() {
        try {
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
//                            pipeline.addLast("logging", new LoggingHandler("DEBUG"));//设置log监听器，并且日志级别为debug，方便观察运行流程
                            pipeline.addLast("http-codec", new HttpServerCodec());//设置解码器
                            pipeline.addLast("aggregator", new HttpObjectAggregator(65536));//聚合器，使用websocket会用到
                            pipeline.addLast("http-chunked", new ChunkedWriteHandler());//用于大数据的分区传输
                            pipeline.addLast(new IdleStateHandler(READ_IDLE_TIME, WRITE_IDLE_TIME, ALL_IDLE_TIME));
                            pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
                            pipeline.addLast(new WebSocketHandler());//自定义的业务handler
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(port).addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功!");
                } else {
                    System.out.println("端口[" + port + "]绑定失败!");
                }
            }).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //关闭方法
    @PreDestroy
    public void close() {
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }

    public static void main(String[] args) {
        WebSocketServer server = new WebSocketServer(9503);
        server.start();
    }

}
