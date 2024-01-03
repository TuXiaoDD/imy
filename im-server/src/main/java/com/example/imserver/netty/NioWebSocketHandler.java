package com.example.imserver.netty;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.imserver.entity.UserDO;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

@Slf4j
public class NioWebSocketHandler extends SimpleChannelInboundHandler<Message> {


    private static final ByteBuf HEARTBEAT_SEQUENCE =
            Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("HEARTBEAT", CharsetUtil.UTF_8));
    private static final int HEARTBEAT_INTERVAL_SECONDS = 20;

    private ScheduledFuture<?> heartbeatScheduledFuture;

    private WebSocketServerHandshaker handshaker;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        log.info("msg: {}",JSONObject.toJSONString(msg));


         ctx.channel().writeAndFlush(msg);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //添加连接
        System.out.println("客户端加入连接：" + ctx.channel());
        startHeartbeat(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        stopHeartbeat();
        super.channelInactive(ctx);

    }

    private void startHeartbeat(ChannelHandlerContext ctx) {

        System.out.println("+++++++++++startHeartbeat"+ctx);

        heartbeatScheduledFuture = ctx.executor().scheduleAtFixedRate(() -> {
            ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());
        }, 0, HEARTBEAT_INTERVAL_SECONDS, TimeUnit.SECONDS);
    }

    private void stopHeartbeat() {
        System.out.println("+++++++++++stopHeartbeat");

        if (heartbeatScheduledFuture != null) {
            heartbeatScheduledFuture.cancel(true);
            heartbeatScheduledFuture = null;
        }
    }


    /**
     * 处理HTTP请求
     *
     * @param ctx
     * @param request
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        // WebSocket访问，处理握手升级。
        if (request.headers().get("Connection").equals("Upgrade")) {
            // Handshake
            WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("", null, true);
            handshaker = wsFactory.newHandshaker(request);
            if (handshaker == null) {
                // 无法处理的WebSocket版本
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
                return;
            }

            // 验证token
            String token = getRequestParameter(request, "token");
            if (token == null) {
                FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.UNAUTHORIZED,
                        Unpooled.copiedBuffer("Token not found in request url", CharsetUtil.UTF_8));
                ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
                log.error("Token not found in request url");
                return;
            }


            try {
                String decode = JWT.decode(token).getAudience().get(0);
                String[] strings = decode.split("\\.");

            } catch (Exception e) {
                FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.UNAUTHORIZED,
                        Unpooled.copiedBuffer("Token is not available", CharsetUtil.UTF_8));
                ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
                log.error("Token is not available");
                return;
            }

            // 向客户端发送WebSocket握手，完成握手。
            ChannelFuture channelFuture = handshaker.handshake(ctx.channel(), request);
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        future.channel().close();
                        return;
                    }
                    // 加入到ChannelHolders中
                    ChannelSupervise.addChannel(future.channel());
                }
            });
            return;
        }
    }
    private static String getRequestParameter(FullHttpRequest req, String name) {
        QueryStringDecoder decoder = new QueryStringDecoder(req.uri());
        Map<String, List<String>> parameters = decoder.parameters();
        Set<Map.Entry<String, List<String>>> entrySet = parameters.entrySet();
        for (Map.Entry<String, List<String>> entry : entrySet) {
            if (entry.getKey().equalsIgnoreCase(name)) {
                return entry.getValue().get(0);
            }
        }
        return null;
    }
}
