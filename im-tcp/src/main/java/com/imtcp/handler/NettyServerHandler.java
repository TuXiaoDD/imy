package com.imtcp.handler;

import com.example.common.enums.MessageType;
import com.example.common.netty.Message;
import com.example.common.netty.Request;
import com.example.common.netty.Response;
import com.imtcp.util.RequestUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;

@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message =(Message)msg;
        if (Objects.equals(message.getMessageType(), MessageType.REQUEST.getVal())){
            Request request = new Request(message.getByteBuf());
            log.info("NettyServerHandler channelRead {}",new String(request.getBody()));
            Response response =new Response(request,"hello,this is server.".getBytes());
            ctx.channel().writeAndFlush(response);
        }

    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
}
