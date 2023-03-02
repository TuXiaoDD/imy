package com.example.imserver.service;

import com.example.imserver.controller.dto.RegisterDTO;
import com.example.imserver.entity.UserDO;

import java.util.List;

/**
 * @packageName: com.example.imserver.service
 * @className: UserService
 * @author: leMin
 * @date: 2023/3/3  0:40
 * @description:
 */
public interface UserService {
    List<UserDO> query();

    RegisterDTO selectUser(String mobile);

    Integer register(String nickname, String password, String mobile);

}
