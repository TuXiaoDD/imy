package com.lym;


import com.example.common.enums.ClientType;
import com.example.common.enums.CodeType;
import com.example.common.netty.Message;
import com.example.common.netty.Request;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;

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
                                .addLast(new MyMessageToByteEncoder())
                                .addLast(new MyMessageDecoder())
                                .addLast(new EchoHandler());
                    }
                }).connect("localhost", 9999);

        try {
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    Channel channel = channelFuture.channel();
                    Integer headerLength = 24;
                    Integer appId = 1;
                    Integer version = 1;
                    Integer command = 1;
                    Integer clientType = 1;
                    Integer codeType = 1;
                    Integer bodyLength = 5;
                    byte[] body = "hello".getBytes();
                    Request message = new Request(headerLength, appId, version, command, clientType, codeType, bodyLength, body);
                    channel.writeAndFlush(message);
                }
            }).channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Slf4j
    public static class EchoHandler extends ChannelInboundHandlerAdapter {


        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            log.info("EchoHandler channelActive");


//            List<Integer> integers = List.of(headerLength, appId, version, command, clientType, codeType, bodyLength);
//            byte[] bytes = new byte[33];
//            int i = 0;
//            for (; i < integers.size(); i++) {
//                bytes[i] = integers.get(i).byteValue();
//            }
//            for (int j = 0; j < body.length; j++) {
//                bytes[i + j + 1] = body[j];
//            }


        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            Message message = (Message) msg;
            log.info("read {}", new String(message.getBody()));
        }
    }

    public static class MyMessageDecoder extends ByteToMessageDecoder {
        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            Message message = new Message(in);
            ReferenceCountUtil.retain(in);
            out.add(message);

        }
    }

    public static class MyMessageToByteEncoder extends MessageToByteEncoder<Message> {

        @Override
        protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
            out.writeInt(msg.getHeaderLength());
            out.writeInt(msg.getAppId());
            out.writeInt(msg.getVersion());
            out.writeInt(msg.getCommand());
            out.writeInt(msg.getClientType());
            out.writeInt(msg.getCodeType());
            out.writeInt(msg.getMessageType());
            out.writeInt(msg.getBodyLength());
            out.writeBytes(msg.getBody());

        }
    }
}
