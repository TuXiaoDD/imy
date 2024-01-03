package com.example.imserver.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Slf4j
public class NettyServer {

    private static final int READ_IDLE_TIME = 60; // 读空闲时间（秒）
    private static final int WRITE_IDLE_TIME = 20; // 写空闲时间（秒）
    private static final int ALL_IDLE_TIME = 0; // 所有空闲时间（秒）
    EventLoopGroup bossGroup;
    EventLoopGroup workGroup;
    ServerBootstrap bootstrap;

    public NettyServer() {
        this.bootstrap = new ServerBootstrap();
        this.bossGroup = new NioEventLoopGroup(1);
        this.workGroup = new NioEventLoopGroup();
    }

    public void start() {
        try {
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("logging", new LoggingHandler("DEBUG"));//设置log监听器，并且日志级别为debug，方便观察运行流程
                            pipeline.addLast("http-codec", new HttpServerCodec());//设置解码器
                            pipeline.addLast("aggregator", new HttpObjectAggregator(65536));//聚合器，使用websocket会用到
                            pipeline.addLast("http-chunked", new ChunkedWriteHandler());//用于大数据的分区传输
                            pipeline.addLast(new IdleStateHandler(READ_IDLE_TIME, WRITE_IDLE_TIME, ALL_IDLE_TIME));
                            pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
                            pipeline.addLast(new WebSocketEncoder());
                            pipeline.addLast(new WebSocketDecoder());
                            pipeline.addLast(new NioWebSocketHandler());//自定义的业务handler
                        }
                    });
            bootstrap.bind(8003).sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void preDestroy() {
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
        log.info("destroy....");
    }

    public static void main(String[] args) {
        new NettyServer().start();
    }
}
