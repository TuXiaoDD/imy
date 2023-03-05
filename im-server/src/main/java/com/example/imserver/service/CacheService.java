package com.example.imserver.service;

import com.example.imserver.constant.Constant;
import com.example.imserver.entity.UserDO;
import com.example.imserver.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 缓存处理
 */
@Service
public class CacheService {

    @Autowired
    UserMapper userMapper;

    @Cacheable(value = Constant.USER_CACHE_KEY)
    public UserDO selectById(Long uid) {
        return userMapper.selectById(uid);
    }

}
