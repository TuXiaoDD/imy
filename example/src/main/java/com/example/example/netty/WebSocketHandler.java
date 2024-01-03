package com.example.example.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
public class WebSocketHandler extends SimpleChannelInboundHandler<WebSocketFrame> {


    private static final ByteBuf HEARTBEAT_SEQUENCE =
            Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("HEARTBEAT", CharsetUtil.UTF_8));
    private static final int HEARTBEAT_INTERVAL_SECONDS = 5;

    private ScheduledFuture<?> heartbeatScheduledFuture;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, WebSocketFrame o) throws Exception {
        if(o instanceof TextWebSocketFrame){
            TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame)o;
            log.info("java: "+textWebSocketFrame.text());
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //添加连接
        log.info("客户端加入连接：" + ctx.channel());
        startHeartbeat(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        stopHeartbeat();
        super.channelInactive(ctx);

    }

    private void startHeartbeat(ChannelHandlerContext ctx) {

        log.info("+++++++++++startHeartbeat");

        heartbeatScheduledFuture = ctx.executor().scheduleAtFixedRate(() -> {
            ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());
        }, 0, HEARTBEAT_INTERVAL_SECONDS, TimeUnit.SECONDS);
    }

    private void stopHeartbeat() {
        log.info("+++++++++++stopHeartbeat");

        if (heartbeatScheduledFuture != null) {
            heartbeatScheduledFuture.cancel(true);
            heartbeatScheduledFuture = null;
        }
    }
}
