package com.lym.entity;

import io.netty.channel.socket.SocketChannel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DispatcherInstanceInfo {
    private String host;
    private String ip;
    private int port;
}
