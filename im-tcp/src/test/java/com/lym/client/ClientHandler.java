package com.lym.client;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@ChannelHandler.Sharable
@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("msg {}",msg.toString());
        super.channelRead(ctx,msg);
    }

    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("ClientHandler channelRegistered");
        ctx.writeAndFlush("client channelRegistered".getBytes(StandardCharsets.UTF_8));
    }

    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("ClientHandler channelUnregistered");
        super.channelUnregistered(ctx);
    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("ClientHandler channelActive");
        ctx.writeAndFlush("client active".getBytes(StandardCharsets.UTF_8));
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("ClientHandler channelInactive");
        super.channelInactive(ctx);
    }

    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (IdleState.ALL_IDLE.equals(event.state())) {
                log.info("ClientHandler userEventTriggered No data was either received or sent");
                ctx.channel().close();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
