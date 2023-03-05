package com.example.imserver.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.imserver.entity.UserDO;
import com.example.imserver.dao.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserDao extends ServiceImpl<UserMapper, UserDO> {

}
