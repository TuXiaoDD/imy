package com.example.imserver.controller;

import com.example.imserver.annotation.NotRequireLogin;
import com.example.imserver.controller.dto.LoginDTO;
import com.example.imserver.controller.dto.RegisterDTO;
import com.example.imserver.controller.vo.LoginVO;
import com.example.imserver.controller.vo.SettingVO;
import com.example.imserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    @Autowired
    UserService userService;

    /**
     * 登录
     *
     * @param dto
     * @return
     */
    @PostMapping("/login")
    @NotRequireLogin
    public LoginVO login(@RequestBody @Valid LoginDTO dto, HttpServletResponse response) {
        return userService.login(dto,response);
    }

    /**
     * 注册
     *
     * @param dto
     * @return
     */
    @PostMapping("/register")
    @NotRequireLogin
    public Long register(@RequestBody @Valid RegisterDTO dto) {
        return userService.register(dto);
    }


}
