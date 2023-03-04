package com.example.imserver.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.common.exception.BizException;
import com.example.common.response.ResultCode;

import com.example.common.utils.Assert;
import com.example.imserver.entity.UserDO;
import com.example.imserver.mapper.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.common.utils.DataUtils;
import com.example.imserver.annotation.NotRequireLogin;

@Component
@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    UserDao userDao;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        if (!(DataUtils.isNotEmpty(handler) && handler instanceof HandlerMethod)) {
            return true;
        }
        //标记了NotRequireLogin注解的方法不需要登录验证
        NotRequireLogin annotation = ((HandlerMethod) handler).getMethodAnnotation(NotRequireLogin.class);
        if (DataUtils.isNotEmpty(annotation)) {
            return true;
        }
        String authorization = request.getHeader("Authorization");
        String[] split = authorization.split(" ");
        if (DataUtils.isEmpty(split) || split.length != 2) {
            log.error("authorization格式错误");
            throw new BizException(ResultCode.UNAUTHORIZED);
        }
        String token = split[1];
        String password;
        try {
            String decode = JWT.decode(token).getAudience().get(0);
            String[] strings = decode.split("\\.");
            Long uid = Long.parseLong(strings[0]);
            // todo 2023-03-04 18:37:18 加缓存
            UserDO userDO = userDao.selectById(uid);
            Assert.notEmpty(userDO);
            password = userDO.getPassword();
//            Cache<Long, String> cache = Caffeine.newBuilder().build();
//            password = cache.getIfPresent(uid);
        } catch (Exception j) {
            log.error("interceptor 获取user信息失败 ", j.getCause());
            throw new BizException(ResultCode.UNAUTHORIZED);
        }
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(password)).build();
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            log.error("token 校验失败 ", e.getCause());
            throw new BizException(ResultCode.UNAUTHORIZED);
        }
        return true;
    }


}
