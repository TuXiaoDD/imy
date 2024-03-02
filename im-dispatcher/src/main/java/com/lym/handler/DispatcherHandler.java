package com.lym.handler;

import com.lym.context.GatewayManager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DispatcherHandler implements ChannelInboundHandler {

    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        GatewayManager gatewayManager = GatewayManager.getInstance();
        SocketChannel channel = (SocketChannel) channelHandlerContext.channel();
        gatewayManager.put(channel);
        log.info("缓存 {}",channel.id().asLongText());

    }

    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        GatewayManager gatewayManager = GatewayManager.getInstance();
        SocketChannel channel = (SocketChannel) channelHandlerContext.channel();
        gatewayManager.remove(channel.id().asLongText());
        log.info("移除缓存 {}",channel.id().asLongText());
    }

    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {

    }
}
