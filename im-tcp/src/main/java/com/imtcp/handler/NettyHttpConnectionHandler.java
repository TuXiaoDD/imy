package com.imtcp.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class NettyHttpConnectionHandler extends ChannelInboundHandlerAdapter {


    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("NettyHttpConnectionHandler channelRegistered");

    }

    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("NettyHttpConnectionHandler channelUnregistered");
    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("NettyHttpConnectionHandler channelActive");

    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("NettyHttpConnectionHandler channelInactive");
    }

    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (IdleState.ALL_IDLE.equals(event.state())) {
                log.info("NettyHttpConnectionHandler userEventTriggered No data was either received or sent");
                ctx.channel().close();
            }
        } else {
            ctx.fireUserEventTriggered(evt);
        }
    }

}
