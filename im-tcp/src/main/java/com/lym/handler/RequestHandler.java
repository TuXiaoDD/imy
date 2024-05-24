package com.lym.handler;

import com.lym.context.DispatcherInstanceManager;
import com.lym.entity.DispatcherInstance;
import com.lym.protobuf.AuthenticateRequestProto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestHandler {

    private RequestHandler() {
    }

    private static final RequestHandler requestHandler = new RequestHandler();

    public static RequestHandler getInstance() {
        return requestHandler;
    }

    public void authenticate(AuthenticateRequestProto.AuthenticateRequest authenticateRequest) {
        // 选择一个分发系统发消息
        DispatcherInstanceManager dispatcherInstanceManager = DispatcherInstanceManager.getInstance();
        DispatcherInstance dispatcherInstance = dispatcherInstanceManager.chooseInstance();
        dispatcherInstance.authenticate(authenticateRequest);
        log.info("authenticate 随机选择的分发系统地址 {} 请求为 {}", dispatcherInstance.getSocketChannel().remoteAddress(), authenticateRequest.toByteString());
    }

}
