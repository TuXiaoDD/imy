package com.example.imserver.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.imserver.entity.FriendDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserDao继承基类
 */
@Mapper
public interface FriendMapper extends BaseMapper<FriendDO> {
    int deleteFriend(Long uid, Long friendUid);

}