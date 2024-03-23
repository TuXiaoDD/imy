package com.lym.entity;

import com.example.common.constants.Constants;
import com.example.common.enums.MessageType;
import com.example.common.netty.Request;
import com.lym.protobuf.AuthenticateRequestProto;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DispatcherInstance {

    private SocketChannel socketChannel;

    public DispatcherInstance(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }


    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void authenticate(AuthenticateRequestProto.AuthenticateRequest authenticateRequest) {
        byte[] authenticateRequestBytes = authenticateRequest.toByteArray();
        Request request = new Request(
                Constants.request_header_length,
                Constants.app_sdk_version,
                MessageType.AUTH.getValue(),
                Constants.request_sequence_default,
                authenticateRequestBytes.length,
                authenticateRequestBytes
        );
        log.info("DispatcherInstance authenticate {}",socketChannel.id());
        socketChannel.writeAndFlush(request.getByteBuf());
    }
}
