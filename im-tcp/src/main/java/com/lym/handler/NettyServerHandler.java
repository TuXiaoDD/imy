package com.lym.handler;

import com.lym.context.NettyChannelManager;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

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
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String content = new String(bytes);
        System.out.println(content);//auth:token:uid
        String[] split = content.split(":");
        if (content.startsWith("auth")) {// 认证请求
            String token = split[2];
            //sso验证token是否合法
            Long uid = Long.parseLong(split[2]);
            manager.addChannel(uid, (NioSocketChannel) ctx.channel());
            byte[] bytes1 = "认证成功".getBytes();
            ByteBuf byteBuf1 = Unpooled.copiedBuffer(bytes1);
            ctx.channel().writeAndFlush(byteBuf1);
        } else {
            Long uid = Long.parseLong(split[2]);
            if (!manager.contains(uid)) {
                log.info("未认证");
                byte[] bytes1 = "未认证".getBytes();
                ByteBuf byteBuf1 = Unpooled.copiedBuffer(bytes1);
                ctx.channel().writeAndFlush(byteBuf1);
            }else{
                System.out.println("收到消息："+content);
                byte[] bytes1 = "消息已收到".getBytes();
                ByteBuf byteBuf1 = Unpooled.copiedBuffer(bytes1);
                ctx.channel().writeAndFlush(byteBuf1);
            }
        }

    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
}
