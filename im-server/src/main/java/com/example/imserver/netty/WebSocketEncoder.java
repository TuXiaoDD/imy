package com.example.imserver.netty;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

import java.util.List;

public class WebSocketEncoder extends MessageToMessageEncoder<MessagePack>{
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessagePack pack, List<Object> list) throws Exception {
        String jsonString = JSONObject.toJSONString(pack);
        byte[] bytes = jsonString.getBytes();
        int length = bytes.length;
        ByteBuf byteBuf = Unpooled.directBuffer(8+length);
        byteBuf.writeInt(pack.getCommand());
        byteBuf.writeInt(length);
        byteBuf.writeBytes(bytes);
        list.add(new BinaryWebSocketFrame(byteBuf));

    }
}
