package com.example.imserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.imserver.dao.mapper.FriendMapper;
import com.example.imserver.entity.FriendDO;
import com.example.imserver.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendMapper friendMapper;

    @Override
    public FriendDO queryByUid(Long uid, Long friendUid) {
        LambdaQueryWrapper<FriendDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FriendDO::getIsDelete, 0);
        wrapper.eq(FriendDO::getUid, uid);
        wrapper.eq(FriendDO::getFriendUid, friendUid);
        return friendMapper.selectOne(wrapper);
    }
}
