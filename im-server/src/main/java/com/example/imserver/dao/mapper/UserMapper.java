package com.example.imserver.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.imserver.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}