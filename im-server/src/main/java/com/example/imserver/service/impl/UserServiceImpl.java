package com.example.imserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.imserver.controller.dto.RegisterDTO;
import com.example.imserver.entity.UserDO;
import com.example.imserver.mapper.UserDao;
import com.example.imserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public List<UserDO> query(){
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
        return userDao.selectList(wrapper);
    }

    public RegisterDTO selectUser(String mobile) {
        return new RegisterDTO();
    }

    public Integer register(String nickname, String password, String mobile) {
        return 1;
    }
}
