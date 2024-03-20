package com.lym.entity;

import com.example.common.constants.Constants;
import com.example.common.enums.RequestType;
import com.example.common.netty.Request;
import com.lym.protobuf.AuthenticateRequestProto;
import io.netty.channel.socket.SocketChannel;

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
                RequestType.AUTH.getValue(),
                Constants.request_sequence_default,
                authenticateRequestBytes.length,
                authenticateRequestBytes
        );
        socketChannel.writeAndFlush(request.getByteBuf());
    }
}
