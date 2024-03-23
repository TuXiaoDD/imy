package com.lym.handler;

import com.alibaba.fastjson2.JSONObject;
import com.example.common.enums.MessageType;
import com.example.common.enums.RequestType;
import com.example.common.netty.Message;
import com.example.common.netty.Request;
import com.lym.context.SessionManager;
import com.lym.protobuf.AuthenticateRequestProto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Message message = new Message(byteBuf);
        log.info("NettyServerHandler message {}", JSONObject.toJSONString(message));
        if (Objects.equals(RequestType.REQUEST.getVal(), message.getRequestType())) {
            Request request = new Request(byteBuf);
            MessageType messageType = MessageType.parse(request.getMessageType());
            byte[] body = request.getBody();
            SessionManager sessionManager = SessionManager.getInstance();
            RequestHandler requestHandler = RequestHandler.getInstance();
            if (Objects.equals(messageType, MessageType.AUTH)) {// 认证请求
                AuthenticateRequestProto.AuthenticateRequest authenticateRequest
                        = AuthenticateRequestProto.AuthenticateRequest.parseFrom(body);

                // 处理连接请求
                requestHandler.authenticate(authenticateRequest);
                SocketChannel channel = (SocketChannel) ctx.channel();
                log.info("channel {}", channel.id());
                sessionManager.addSession(Long.parseLong(authenticateRequest.getUid()), channel);

            } else {
//            if (!manager.contains(uid)) {
//                log.info("未认证");
//                byte[] bytes1 = "未认证".getBytes();
//                ByteBuf byteBuf1 = Unpooled.copiedBuffer(bytes1);
//                ctx.channel().writeAndFlush(byteBuf1);
//            }
            }
            if (Objects.equals(messageType, MessageType.TEXT_MSG)) {// 普通消息
                System.out.println(new String(body));
            }
        }


    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
}
