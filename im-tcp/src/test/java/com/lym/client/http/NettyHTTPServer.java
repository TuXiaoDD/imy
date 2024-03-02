package com.lym.client.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class NettyHTTPServer {

    private int port = 9999;

    private ServerBootstrap serverBootstrap;

    private EventLoopGroup bossEventLoopGroup;
    private EventLoopGroup workEventLoopGroup;


    public NettyHTTPServer() {
        init();
    }

    public void init() {
        serverBootstrap = new ServerBootstrap();
        bossEventLoopGroup = new NioEventLoopGroup();
        workEventLoopGroup = new NioEventLoopGroup();
    }

    public static void main(String[] args) {
        new NettyHTTPServer().start();
    }

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
                                .addLast(new HttpServerCodec())
                                .addLast(new HttpObjectAggregator(65535))
                                .addLast(new HttpServerExpectContinueHandler())
//                                .addLast(new NettyHttpConnectionHandler())
//                                .addLast(new NettyHttpServerHandler())
                        ;
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


    @Slf4j
    public static class MyByteToMessageDecoder extends HttpRequestDecoder {



    }

    public void shutdown() {
        if (bossEventLoopGroup != null) {
            bossEventLoopGroup.shutdownGracefully();
        }
        if (workEventLoopGroup != null) {
            workEventLoopGroup.shutdownGracefully();
        }
    }
}
