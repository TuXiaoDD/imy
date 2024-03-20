package com.lym;

import com.google.protobuf.InvalidProtocolBufferException;
import com.lym.protobuf.AuthenticateRequestProto;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        AuthenticateRequestProto.AuthenticateRequest.Builder builder = AuthenticateRequestProto.AuthenticateRequest.newBuilder();
        builder.setToke("test-token");
        builder.setTimestamp(System.currentTimeMillis());
        builder.setUid("1");
        AuthenticateRequestProto.AuthenticateRequest authenticateRequest = builder.build();
        byte[] byteArray = authenticateRequest.toByteArray();
        System.out.println(byteArray.length);
        AuthenticateRequestProto.AuthenticateRequest request = AuthenticateRequestProto.AuthenticateRequest.parseFrom(byteArray);
        System.out.println(request);
    }
}
