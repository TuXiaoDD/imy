package com.lym.config;

import lombok.Data;

@Data
public class BaseConfig {
    //端口
    private int port = 9999;

    // CPU核数映射的线程数
    private int processThead = Runtime.getRuntime().availableProcessors();
    // boss 线程数
    private int bossGroupNum = 1;
    // work 线程数
    private int workGroupNum = processThead;
    // 是否是开启linux的epoll模型
    private boolean useEpoll = false;
    // netty内存分配机制
    private boolean nettyAllocator = true;
    // 64*1024*1024 http body最大长度
    private int maxContentLength = 67108864;
    // http连接超时时间
    private int connectTimeout = 30000;
    // http 请求超时时间
    private int requestTimeout = 30000;
    // http 最大重试次数
    private int maxRequestRetry = 3;
    // http 最大连接数
    private int maxConnections = 10000;
    // http 每台主机最大连接数
    private int maxConnectionsPerHost = 8000;
    // 客户端空闲超时时间
    private int pooledConnectionIdleTimeout = 60000;








}
