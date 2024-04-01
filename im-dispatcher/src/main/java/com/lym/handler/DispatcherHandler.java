package com.lym.handler;

import com.alibaba.fastjson2.JSONObject;
import com.example.common.enums.MessageType;
import com.example.common.enums.RequestType;
import com.example.common.netty.Message;
import com.example.common.netty.Request;
import com.example.common.netty.Response;
import com.example.common.utils.JedisUtil;
import com.lym.context.GatewayManager;
import com.lym.entity.AuthRedisValue;
import com.lym.protobuf.AuthenticateRequestProto;
import com.lym.protobuf.AuthenticateResponseProto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Objects;

import static com.example.common.constants.Constants.auth_status_success;
import static com.example.common.constants.Constants.socketKeyFunc;

@Slf4j
public class DispatcherHandler extends ChannelInboundHandlerAdapter {


    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        GatewayManager gatewayManager = GatewayManager.getInstance();
        SocketChannel channel = (SocketChannel) channelHandlerContext.channel();
        gatewayManager.put(channel);
        log.info("缓存 {}", channel.id().asLongText());

    }

    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        GatewayManager gatewayManager = GatewayManager.getInstance();
        SocketChannel channel = (SocketChannel) channelHandlerContext.channel();
        gatewayManager.remove(socketKeyFunc.apply(channel));
        log.info("移除缓存 {}", channel.id().asLongText());
    }

    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ByteBuf byteBuf = (ByteBuf) o;
        Message message = new Message(byteBuf);
        log.info("DispatcherHandler message {}", JSONObject.toJSONString(message));
        SocketChannel channel = (SocketChannel) channelHandlerContext.channel();
        log.info("channelId {}", channel.id().asLongText());
        if (Objects.equals(RequestType.REQUEST.getVal(), message.getRequestType())) {
            Request request = new Request(byteBuf);
            int messageType = request.getMessageType();
            byte[] body = request.getBody();
            RequestHandler requestHandler = RequestHandler.getInstance();
            if (Objects.equals(messageType, MessageType.AUTH.getValue())) {// 认证请求
                AuthenticateRequestProto.AuthenticateRequest authenticateRequest
                        = AuthenticateRequestProto.AuthenticateRequest.parseFrom(body);

                // 处理连接请求
                AuthenticateResponseProto.AuthenticateResponse authenticateResponse = requestHandler.authenticate(authenticateRequest);

                // 认证成功
                if (auth_status_success == authenticateResponse.getStatus()) {
                    AuthRedisValue v = new AuthRedisValue();
                    v.setToken(authenticateResponse.getToke());
                    v.setGatewayChannelId(socketKeyFunc.apply(channel));
                    v.setIsAuth(Boolean.TRUE);
                    v.setAuthTimestamp(System.currentTimeMillis());
                    v.setTimestamp(authenticateResponse.getTimestamp());
                    JedisUtil.set(authenticateResponse.getUid(), JSONObject.toJSONString(v));

                }


                Response response = new Response(request, authenticateResponse.toByteArray());
                channel.writeAndFlush(response.getByteBuf());
            }
        }

    }

}
