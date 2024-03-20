package com.lym.handler;

import com.example.common.enums.RequestType;
import com.example.common.netty.Request;
import com.lym.context.NettyChannelManager;
import com.lym.protobuf.AuthenticateRequestProto;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf byteBuf=(ByteBuf)msg;
//        Message message =new Message(byteBuf);
//        Message message =(Message)msg;
//        if (Objects.equals(message.getMessageType(), MessageType.REQUEST.getVal())){
//            Request request = new Request(message.getByteBuf());
//            log.info("NettyServerHandler channelRead {}",new String(request.getBody()));
//            Response response =new Response(request,"hello,this is server.".getBytes());
//            ctx.channel().writeAndFlush(response);
//        }
        NettyChannelManager manager = NettyChannelManager.getManager();
        ByteBuf byteBuf = (ByteBuf) msg;
        Request request = new Request(byteBuf);
        RequestType requestType = RequestType.parse(request.getRequestType());
        byte[] body = request.getBody();

        if (Objects.equals(requestType, RequestType.AUTH)) {// 认证请求
            AuthenticateRequestProto.AuthenticateRequest authenticateRequest
                    = AuthenticateRequestProto.AuthenticateRequest.parseFrom(body);
            Long uid = Long.parseLong(authenticateRequest.getUid());

            //sso验证token是否合法
            manager.addChannel(uid, (NioSocketChannel) ctx.channel());

            byte[] bytes1 = "认证成功".getBytes();
            ByteBuf byteBuf1 = Unpooled.copiedBuffer(bytes1);
            ctx.channel().writeAndFlush(byteBuf1);
        } else {
//            if (!manager.contains(uid)) {
//                log.info("未认证");
//                byte[] bytes1 = "未认证".getBytes();
//                ByteBuf byteBuf1 = Unpooled.copiedBuffer(bytes1);
//                ctx.channel().writeAndFlush(byteBuf1);
//            }
        }
        if (Objects.equals(requestType, RequestType.TEXT_MSG)) {// 普通消息
            System.out.println(new String(body));
        }

    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
}
