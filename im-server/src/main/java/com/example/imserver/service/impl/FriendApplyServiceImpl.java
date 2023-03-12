package com.example.imserver.service.impl;

import com.example.common.utils.Assert;
import com.example.imserver.dao.mapper.FriendApplyMapper;
import com.example.imserver.service.FriendApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: leMin
 * @className: FriendApplyServiceImpl
 * @date: 2023/3/12 ~ 23:50
 * @description:
 */
@Service
public class FriendApplyServiceImpl implements FriendApplyService {

    @Autowired
    FriendApplyMapper friendApplyMapper;
    @Override
    public int countUnread(Long uid) {
        int result = friendApplyMapper.countUnread(uid);
        Assert.isTrue(result > 0, "没有好友申请消息!");
        return result;
    }
}
