package com.example.imserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.imserver.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserDao继承基类
 */
@Mapper
public interface UserDao extends BaseMapper<UserDO> {
}