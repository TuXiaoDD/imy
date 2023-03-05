package com.example.imserver.service;

import com.example.imserver.entity.FriendDO;

public interface FriendService {


    FriendDO queryByUid(Long uid, Long friendUid);
}
