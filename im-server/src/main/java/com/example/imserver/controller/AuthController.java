package com.example.imserver.controller;

import com.example.common.exception.BizException;
import com.example.common.response.Response;
import com.example.common.response.ResultCode;
import com.example.imserver.controller.dto.LoginDTO;
import com.example.imserver.controller.dto.RegisterDTO;
import com.example.imserver.controller.vo.LoginVO;
import com.example.imserver.controller.vo.SettingVO;
import com.example.imserver.service.UserService;
import com.example.imserver.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    @Autowired
    UserService userService;

    /**
     * 登录
     * @param dto
     * @return
     */
    @PostMapping("/login")
    public LoginVO login(@RequestBody LoginDTO dto){
        log.info("dto:{}",dto);
        LoginVO loginVO = new LoginVO();
        loginVO.setAccessToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJndWFyZCI6ImFwaSIsImlzcyI6ImltLndlYiIsImV4cCI6MTY3NzQxNTA0MCwiaWF0IjoxNjc3MzI4NjQwLCJqdGkiOiIyMDU0In0.ay5bhjhU18ckcVOdbuL4uj4hq3sctL3aL6lO7SykjEw");
        loginVO.setExpiresIn(86400L);
        return loginVO;
    }

    /**
     * 注册
     * @param dto
     * @return
     */
    @PostMapping("/register")
    public Response<Long> register(@RequestBody RegisterDTO dto){
        //1、校验注册参数
        if (Objects.isNull(dto)) {
            return Response.fail("注册用户为空！");
        }
        String mobile = dto.getMobile();
        String nickName = dto.getNickName();
        String password = dto.getPassword();
        if (StringUtils.isAnyBlank(mobile, nickName, password)) {
            throw new BizException(ResultCode.PARAM_BIND_ERROR, "注册参数含空值，请检查注册要素！");
        }
        //2、注册账号，落地账户信息
        Long id = userService.register(nickName, password, mobile);
        return Response.data(id, "注册成功！");
    }

    @GetMapping("test")
    public SettingVO test(){
        SettingVO settingVO = new SettingVO();
        settingVO.setThemeBagImg("test4");
        settingVO.setNotifyCueTone("test2");
        settingVO.setThemeMode("test1");
        return settingVO;
    }



}
