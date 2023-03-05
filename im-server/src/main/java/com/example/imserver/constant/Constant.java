package com.example.imserver.constant;

public interface Constant {
    // 毫秒，登录token过期时间 10分钟，
    long TOKEN_EXPIRE = 36000000L;

    // user查询缓存，对应的key
    String USER_CACHE_KEY = "user_cache";
}
