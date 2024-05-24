package com.lym.client;

import com.alibaba.fastjson2.JSONObject;
import com.example.common.enums.MessageType;
import com.example.common.enums.RequestType;
import com.example.common.netty.Message;
import com.example.common.netty.Response;
import com.lym.context.DispatcherInstanceManager;
import com.lym.context.SessionManager;
import com.lym.protobuf.AuthenticateResponseProto;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

import static com.example.common.constants.Constants.channelIdFunc;

@Slf4j
public class DispatcherInstanceHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        DispatcherInstanceManager dispatcherInstanceManager = DispatcherInstanceManager.getInstance();
        SocketChannel channel = (SocketChannel) channelHandlerContext.channel();
        if(channel==null){
            return;
        }
        dispatcherInstanceManager.remove(channelIdFunc.apply(channel));
        log.info("channelInactive 跟TCP接入系统的连接断开，地址为：" + channel.remoteAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ByteBuf byteBuf = (ByteBuf) o;
        Message message = new Message(byteBuf);
        log.info("DispatcherInstanceHandler message {}", JSONObject.toJSONString(message));
        if (Objects.equals(message.getRequestType(), RequestType.RESPONSE.getVal())) {
            Response response = new Response(byteBuf);
            int requestType = response.getRequestType();
            if (Objects.equals(requestType, MessageType.AUTH.getValue())) {
                byte[] body = response.getBody();
                AuthenticateResponseProto.AuthenticateResponse authenticateResponse = AuthenticateResponseProto.AuthenticateResponse.parseFrom(body);
                String uid = authenticateResponse.getUid();
                SocketChannel channel = SessionManager.getInstance().getChannel(Long.parseLong(uid));
                log.info("DispatcherInstanceHandler channel {}", channel.id());
                byte[] byteArray = authenticateResponse.toByteArray();
                ByteBuf bb = Unpooled.copiedBuffer(byteArray);
                channel.writeAndFlush(bb);
            }
        }

    }

}
