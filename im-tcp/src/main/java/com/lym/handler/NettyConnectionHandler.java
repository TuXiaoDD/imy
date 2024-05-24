package com.lym.handler;

import com.example.common.constants.RedisConstants;
import com.example.common.utils.JedisUtil;
import com.example.common.utils.RemotingUtil;
import com.lym.context.SessionManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import static com.example.common.constants.Constants.channelIdFunc;

@Slf4j
public class NettyConnectionHandler extends ChannelInboundHandlerAdapter {


    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("NettyHttpConnectionHandler channelActive {}", RemotingUtil.getLocalAddress());
        // todo remove channel
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("NettyHttpConnectionHandler channelInactive {}", RemotingUtil.getLocalAddress());
        SocketChannel channel = (SocketChannel) ctx.channel();
        SessionManager instance = SessionManager.getInstance();

        String channelId = channelIdFunc.apply(channel);
        JedisUtil.del(RedisConstants.sessionPrefix + instance.getUidByChannelId(channelId));

        instance.removeSession(channel);


    }

    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (IdleState.ALL_IDLE.equals(event.state())) {
                log.info("NettyHttpConnectionHandler userEventTriggered No data was either received or sent {}", RemotingUtil.getLocalAddress());
                ctx.channel().close();
            }
        } else {
            ctx.fireUserEventTriggered(evt);
        }
    }

}
