package com.lym.handler;


import com.lym.protobuf.AuthenticateRequestProto;
import com.lym.protobuf.AuthenticateResponseProto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestHandler {

    private RequestHandler() {
    }

    private static final RequestHandler requestHandler = new RequestHandler();

    public static RequestHandler getInstance() {
        return requestHandler;
    }

    public AuthenticateResponseProto.AuthenticateResponse authenticate(AuthenticateRequestProto.AuthenticateRequest authenticateRequest) {
        long timestamp = authenticateRequest.getTimestamp();
        String toke = authenticateRequest.getToke();
        String uid = authenticateRequest.getUid();
        AuthenticateResponseProto.AuthenticateResponse.Builder builder = new AuthenticateResponseProto.AuthenticateResponse.Builder();
        builder.setToke(toke);
        builder.setUid(uid);
        builder.setTimestamp(timestamp);
        try {
            // sso
            log.info("authenticate time {} toke {} uid {}", timestamp, toke, uid);
            builder.setStatus(100);
        } catch (Exception e) {//todo
            builder.setStatus(500);
            builder.setErrorCode(-1);
            builder.setErrorMessage(e.getMessage());
        }
        return builder.build();


    }

}
