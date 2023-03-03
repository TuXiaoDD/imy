package com.example.imserver.service.impl;

import com.alibaba.druid.wall.violation.ErrorCode;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.exception.BizException;
import com.example.common.response.Response;
import com.example.common.response.ResultCode;
import com.example.imserver.controller.dto.RegisterDTO;
import com.example.imserver.controller.vo.UserSettingVO;
import com.example.imserver.entity.UserDO;
import com.example.imserver.mapper.UserDao;
import com.example.imserver.service.UserService;
import com.example.imserver.service.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    /*加密的盐值*/
    private static final String SALT = "leMin";

    public List<UserDO> query(){
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
        return userDao.selectList(wrapper);
    }

    public RegisterDTO selectUser(String mobile) {
        return new RegisterDTO();
    }

    public Long register(String nickName, String password, String mobile) {
        //1、校验输入手机号是否包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(mobile);
        if (matcher.find()) {
            throw new BizException(ResultCode.PARAM_BIND_ERROR, "输入的手机号包含特殊字符！");
        }
        //2、该手机号是否已注册
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        Long count = userDao.selectCount(wrapper);
        if (count > 0) {
            throw new BizException("该用户已注册!");
        }
        //2、密码长度不小于4位
        if (password.length() < 4) {
            throw new BizException("密码长度请设置大于4位！");
        }
        //密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        //3、注册成功，落地注册用户信息
        UserDO user = new UserDO();
        user.setMobile(mobile);
        user.setNickname(nickName);
        user.setPassword(encryptPassword);
        int insert = userDao.insert(user);
        if (insert <= 0) {
            throw new BizException(ResultCode.FAILURE,"用户注册失败!");
        }
        return user.getId();
    }


    public UserSettingVO getSetting() {
        UserSettingVO vo = new UserSettingVO();
        UserDO userDO = userDao.selectById(1);
        vo.setUserInfo(UserConverter.userDO2UserInfoVO(userDO));
        return vo;
    }

}
