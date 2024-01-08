package com.example.imserver.constant;

public interface Constant {
    // 毫秒，登录token过期时间 1小时，
    long TOKEN_EXPIRE = 36000000L;
    long REFRESH_TOKEN_EXPIRE = 24*36000000L;

    // user查询缓存，对应的key
    String USER_CACHE_KEY = "user_cache";
}
