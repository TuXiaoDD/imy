package com.example.imserver.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.imserver.entity.UserDO;
import com.example.imserver.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public List<UserDO> query(){
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
        return userDao.selectList(wrapper);
    }

}
