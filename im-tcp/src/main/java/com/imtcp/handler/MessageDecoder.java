package com.imtcp.handler;

import com.example.common.netty.Message;
import com.example.common.netty.Request;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        if (in.readableBytes() <= 0) {
            log.info("***********当前无数据报文，无需解码***********");
            return;
        }
        Message message = new Message(in);
        ReferenceCountUtil.retain(in);
        out.add(message);
    }
}
