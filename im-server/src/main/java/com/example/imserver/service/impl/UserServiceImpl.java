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
import com.example.imserver.dao.UserDao;
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
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserDao userDao;


    public List<UserDO> query(List<Long> ids) {
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDO::getIsDelete, 0);
        wrapper.in(DataUtils.isNotEmpty(ids), UserDO::getId, ids);
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
        Date start = new Date();
        String token = getToken(userDO, start);
        String refreshToken = getRefreshToken(userDO, start);
        LoginVO vo = new LoginVO();
        vo.setAccessToken(token);
        vo.setRefreshToken(refreshToken);
        vo.setExpiresIn(Constant.TOKEN_EXPIRE);
        return vo;
    }

    public Long register(RegisterDTO dto) {
        String mobile = dto.getMobile();
        String nickname = dto.getNickname();
        UserDO userDO = queryUserUsingOr(mobile, nickname);
        Optional.ofNullable(userDO).ifPresent(user -> {
            Assert.isFalse(Objects.equals(user.getMobile(), mobile), "该手机号已被注册!");
            Assert.isFalse(Objects.equals(user.getNickname(), nickname), "该昵称已被注册!");
        });
        //密码加密
        String password = getEncryptPassword(dto.getPassword());
        UserDO user = new UserDO();
        user.setMobile(mobile);
        user.setNickname(nickname);
        user.setPassword(password);
        userMapper.insert(user);
        return user.getId();
    }


    public UserSettingVO getSetting(Long uid) {
        UserSettingVO vo = new UserSettingVO();
        UserDO userDO = userDao.queryUsers(List.of(uid)).get(0);
        Optional.ofNullable(userDO).ifPresent(u -> vo.setUserInfo(UserConverter.userDO2UserInfoVO(userDO)));
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
     * 判断用户是否存在
     *
     * @param ids 不存在的用户
     * @return
     */
    @Override
    public List<Long> getNotExistUid(List<Long> ids) {
        List<UserDO> userDOS = userDao.queryUsers(ids);
        ids.removeAll(userDOS.stream().map(UserDO::getId).collect(Collectors.toList()));
        return ids;
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

    private String getToken(UserDO user,Date start) {
        Date end = new Date(start.getTime() + Constant.TOKEN_EXPIRE);
        Date refreshEnd = new Date(start.getTime() + Constant.REFRESH_TOKEN_EXPIRE);
        String token = "";
        String refreshToken = "";
        token = JWT.create().withAudience(user.getId() + ".yyM").withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;

    }

    private String getRefreshToken(UserDO user,Date start) {
        Date end = new Date(start.getTime() + Constant.REFRESH_TOKEN_EXPIRE);
        String token = "";
        token = JWT.create().withAudience(user.getId() + ".yyM").withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

}
