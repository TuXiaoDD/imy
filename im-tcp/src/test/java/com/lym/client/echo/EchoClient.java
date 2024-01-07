package com.lym.client.echo;


import com.alibaba.fastjson2.JSONObject;
import com.example.common.utils.ByteUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

public class EchoClient {

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();

        ChannelFuture channelFuture = bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_SNDBUF, 65535)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
//                                .addLast(new StringEncoder())
//                                .addLast(new StringDecoder())
                                .addLast(new MyMessageToByteEncoder())
                                .addLast(new EchoHandler(4));
                    }
                }).connect("localhost", 9999);

        try {
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Slf4j
    public static class EchoHandler extends ChannelInboundHandlerAdapter {
        private int lengthFieldLength;

        public EchoHandler(int lengthFieldLength) {
            this.lengthFieldLength = lengthFieldLength;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            log.info("EchoHandler channelActive");
            Channel channel = ctx.channel();
            String hello = "hello 14:23:32,651 |-INFO in c.q.l.core.rolling.DefaultTimeBasedFileNamingAndTriggeringPolicy - The date pattern is 'yyyy-MM-dd' from file name pattern './log/error/%d.log'.\n";
            String msg = "";
            for (int i = 0; i < 10; i++) {
                msg = msg + hello;

            }
            int length = msg.getBytes().length + lengthFieldLength;
            EchoServer.MsgPack msgPack = new EchoServer.MsgPack(length, msg);
            ctx.channel().writeAndFlush(msgPack);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf byteBuf = (ByteBuf) msg;
            byte[] bytes = ByteBufUtil.getBytes(byteBuf);

            log.info("EchoHandler channelRead msg {}", new String(bytes));
        }
    }

    public static class MyMessageToByteEncoder extends MessageToByteEncoder<EchoServer.MsgPack> {

        @Override
        protected void encode(ChannelHandlerContext ctx, EchoServer.MsgPack msg, ByteBuf out) throws Exception {
            out.writeInt(msg.getLen());
            String string = msg.getMsg();
            out.writeBytes(string.getBytes(StandardCharsets.UTF_8));

        }
    }
}
