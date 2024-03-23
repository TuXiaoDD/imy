package com.lym.handler;

import com.alibaba.fastjson2.JSONObject;
import com.example.common.netty.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;

public class ClientHandler implements ChannelInboundHandler {
    @Override
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ByteBuf byteBuf = (ByteBuf) o;
        Message message = new Message(byteBuf);
        System.out.println(JSONObject.toJSONString(message));


    }

    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {

    }
}
