package com.lym.client.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

public class EchoServer {


    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup work = new NioEventLoopGroup(8);
        EventLoopGroup boss = new NioEventLoopGroup();

        ChannelFuture channelFuture = bootstrap.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_RCVBUF, 65535)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
//                                .addLast(new StringEncoder())
//                                .addLast(new StringDecoder())
                                .addLast(new MyByteToMessageDecoder())
                                .addLast(new EchoHandler(4))
                        ;

                    }
                }).bind("localhost", 9999);
        try {
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Slf4j
    public static class MyByteToMessageDecoder extends ByteToMessageDecoder {

        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            int len = in.readInt();

            int l = len - 4;
            if(in.readableBytes()< l){
                in.resetReaderIndex();
                return;
            }

            byte[] bytes = new byte[l];
            in.readBytes(bytes);
            String msg = new String(bytes);

            in.markReaderIndex();

            out.add(new MsgPack(len,msg));
        }
    }
    @Slf4j
    public static class MyLengthFieldBasedFrameDecoder extends LengthFieldBasedFrameDecoder {

        private int lengthFieldLength;

        //        private int lengthFieldOffset;
        public MyLengthFieldBasedFrameDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
            super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
            this.lengthFieldLength = lengthFieldLength;
//            this.lengthFieldOffset=lengthFieldOffset;

        }

        @Override
        protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
            int readableBytes = in.readableBytes();
            if (readableBytes < lengthFieldLength) return null;
//            byte[] bytes = new byte[lengthFieldLength];
//            ByteBuf byteBuf = in.readBytes(bytes, 0, lengthFieldLength);
            int len = in.readInt();
            if (readableBytes<len){
                return in;
            }

            if (len != readableBytes) {
                throw new RuntimeException("长度错误");
            }
            byte[] bytes = new byte[in.readableBytes()];
            in.writeBytes(bytes);
            return new MsgPack(len, new String(bytes));
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
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            MsgPack by = (MsgPack) msg;

            log.info("EchoHandler channelRead msg {}", by.getMsg());


        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MsgPack implements Serializable {
        private int len;
        private String msg;

    }
}
