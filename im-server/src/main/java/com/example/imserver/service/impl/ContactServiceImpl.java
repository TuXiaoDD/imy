package com.example.imserver.service.impl;

import com.example.common.utils.Assert;
import com.example.imserver.controller.vo.ContactDetailVO;
import com.example.imserver.entity.UserDO;
import com.example.imserver.service.ContactService;
import com.example.imserver.service.FriendService;
import com.example.imserver.service.UserService;
import com.example.imserver.service.converter.UserConverter;
import com.example.imserver.service.query.UserQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    @Autowired
    UserService userService;
    @Autowired
    FriendService friendService;



    @Override
    public ContactDetailVO contactDetail(Long uid) {
        List<UserDO> userDOS = userService.queryUser(UserQuery.builder().id(uid).build());
        Assert.notEmpty(userDOS,"好友信息不存在");
        UserDO userDO = userDOS.get(0);
        ContactDetailVO contactDetailVO = UserConverter.userDO2ContactDetailVO(userDO);
//        friendService
        return null;
    }
}
