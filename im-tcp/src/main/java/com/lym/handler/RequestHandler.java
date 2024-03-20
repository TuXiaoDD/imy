package com.lym.handler;

import com.lym.context.DispatcherInstanceManager;
import com.lym.entity.DispatcherInstance;
import com.lym.entity.DispatcherInstanceInfo;
import com.lym.protobuf.AuthenticateRequestProto;
import com.lym.protobuf.AuthenticateResponseProto;
import io.netty.channel.socket.SocketChannel;

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

    }

}
