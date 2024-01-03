package com.example.imserver.interceptor;
import com.example.common.utils.DataUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.common.exception.BizException;
import com.example.common.response.ResultCode;

import com.example.common.utils.Assert;
import com.example.imserver.entity.UserDO;
import com.example.imserver.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.imserver.annotation.NotRequireLogin;

import java.util.Map;

@Component
@Slf4j
public class UserPermissionInterceptor implements HandlerInterceptor {

    @Autowired
    CacheService cacheService;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        if (!(DataUtils.isNotEmpty(handler) && handler instanceof HandlerMethod)) {
            return true;
        }
        //标记了NotRequireLogin注解的方法不需要登录验证
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        NotRequireLogin annotation = handlerMethod.getMethodAnnotation(NotRequireLogin.class);
        if (DataUtils.isNotEmpty(annotation)) {
            return true;
        }
        String authorization = request.getHeader("Authorization");
        if (DataUtils.isEmpty(authorization)) {
            log.error("authorization为空");
            throw new BizException(ResultCode.UNAUTHORIZED);
        }
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
            UserDO userDO = cacheService.selectById(uid);
            Assert.notEmpty(userDO);
            password = userDO.getPassword();
            Map<String, String[]> parameterMap = request.getParameterMap();
            String[] uids = parameterMap.get("uid");
            uids[0] = uid + "";

        } catch (Exception j) {
            log.error("interceptor 获取user信息失败 ", j.fillInStackTrace());
            throw new BizException(ResultCode.UNAUTHORIZED);
        }
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(password)).build();
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            log.error("token 校验失败 ", e.fillInStackTrace());
            throw new BizException(ResultCode.UNAUTHORIZED);
        }
        return true;
    }


}
