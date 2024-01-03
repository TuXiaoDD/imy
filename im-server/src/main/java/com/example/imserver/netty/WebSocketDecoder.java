package com.example.imserver.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

import java.util.List;

public class WebSocketDecoder extends MessageToMessageDecoder<BinaryWebSocketFrame> {

    private static final int MIN_FRAME_LENGTH = 24;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, BinaryWebSocketFrame webSocketFrame, List<Object> list) throws Exception {
        ByteBuf content = webSocketFrame.content();
        if(content.readableBytes()<MIN_FRAME_LENGTH){
            return;
        }
        Message message = ByteBufToMessageUtils.transition(content);
        if (message == null) {
            return;
        }
        list.add(message);
    }

}
