//package com.lym.handler;
//
//import com.example.common.netty.Message;
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.MessageToByteEncoder;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.Objects;
//
//@Slf4j
//public class MessageEncoder extends MessageToByteEncoder<Message> {
//
//    @Override
//    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
//        if (Objects.isNull(msg)) {
//            log.info("***********当前无数据报文，无需编码***********");
//            return;
//        }
//        out.writeInt(msg.getHeaderLength());
//        out.writeInt(msg.getAppId());
//        out.writeInt(msg.getVersion());
//        out.writeInt(msg.getCommand());
//        out.writeInt(msg.getClientType());
//        out.writeInt(msg.getCodeType());
//        out.writeInt(msg.getMessageType());
//        out.writeInt(msg.getBodyLength());
//        out.writeBytes(msg.getBody());
//    }
//
//}
