package com.example.imserver.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.utils.Assert;
import com.example.common.utils.DataUtils;
import com.example.imserver.constant.Constant;
import com.example.imserver.controller.dto.LoginDTO;
import com.example.imserver.controller.dto.RegisterDTO;
import com.example.imserver.controller.vo.LoginVO;
import com.example.imserver.controller.vo.UserSettingVO;
import com.example.imserver.entity.UserDO;
import com.example.imserver.dao.mapper.UserMapper;
import com.example.imserver.service.UserService;
import com.example.imserver.service.converter.UserConverter;
import com.example.imserver.service.query.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletResponse;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    public List<UserDO> query() {
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
        return userMapper.selectList(wrapper);
    }

    @Override
    public LoginVO login(LoginDTO dto, HttpServletResponse response) {
        String mobile = dto.getMobile();
        String password = getEncryptPassword(dto.getPassword());
        List<UserDO> userDOS = queryUser(UserQuery.builder().mobile(mobile).build());
        Assert.isTrue(DataUtils.isNotEmpty(userDOS), "用户不存在!");
        UserDO userDO = userDOS.get(0);
        Assert.isTrue(Objects.equals(password, userDO.getPassword()), "密码不正确!");
        String token = getToken(userDO);
        LoginVO vo = new LoginVO();
        vo.setAccessToken(token);
        vo.setExpiresIn(Constant.TOKEN_EXPIRE);
        return vo;
    }

    public Long register(RegisterDTO dto) {
        String mobile = dto.getMobile();
        String password = getEncryptPassword(dto.getPassword());
        String nickname = dto.getNickname();
        UserDO userDO = queryUserUsingOr(mobile, nickname);
        if (DataUtils.isNotEmpty(userDO)) {
            Assert.isFalse(Objects.equals(userDO.getMobile(), mobile), "该手机号已被注册!");
            Assert.isFalse(Objects.equals(userDO.getNickname(), nickname), "该昵称已被注册!");
        }
        //密码加密
        UserDO user = new UserDO();
        user.setMobile(mobile);
        user.setNickname(nickname);
        user.setPassword(password);
        userMapper.insert(user);
        return user.getId();
    }


    public UserSettingVO getSetting(Long uid) {
        UserSettingVO vo = new UserSettingVO();
        UserDO userDO = userMapper.selectById(uid);
        vo.setUserInfo(UserConverter.userDO2UserInfoVO(userDO));
        return vo;
    }

    public List<UserDO> queryUser(UserQuery query) {
        String mobile = query.getMobile();
        Long id = query.getId();
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDO::getIsDelete, 0);
        wrapper.eq(DataUtils.isNotEmpty(mobile), UserDO::getMobile, mobile);
        wrapper.eq(DataUtils.isNotEmpty(id), UserDO::getId, id);
        return userMapper.selectList(wrapper);
    }

    /**
     * 或关系的查询
     *
     * @return
     */
    private UserDO queryUserUsingOr(String mobile, String nickname) {
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDO::getIsDelete, 0);
        wrapper.eq(DataUtils.isNotEmpty(mobile), UserDO::getMobile, mobile)
                .or(DataUtils.isNotEmpty(nickname), queryWrapper -> queryWrapper.eq(UserDO::getNickname, nickname));
        return userMapper.selectOne(wrapper);
    }

    private String getEncryptPassword(String password) {
        return DigestUtils.md5DigestAsHex(("leMin" + password).getBytes());
    }

    private String getToken(UserDO user) {
        Date start = new Date();
        Date end = new Date(start.getTime() + Constant.TOKEN_EXPIRE);
        String token = "";
        token = JWT.create().withAudience(user.getId() + ".yyM").withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
