package com.example.imserver.controller;

import com.example.imserver.controller.dto.LoginDTO;
import com.example.imserver.controller.vo.LoginVO;
import com.example.imserver.controller.vo.UserSettingVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    /**
     * 用户相关设置
     * @return
     */
    @GetMapping("/setting")
    public UserSettingVO setting(){

        return new UserSettingVO();
    }

    /**
     * 修改用户密码
     * @return
     */
    @PostMapping("/change/password")
    public UserSettingVO changePassword(){

        return new UserSettingVO();
    }
    /**
     * 修改用户手机
     * @return
     */
    @PostMapping("/change/mobile")
    public UserSettingVO changeMobile(){

        return new UserSettingVO();
    }
    /**
     * 修改用户信息
     * @return
     */
    @PostMapping("/change/detail")
    public UserSettingVO changeDetail(){

        return new UserSettingVO();
    }
    /**
     * 修改用户邮箱
     * @return
     */
    @PostMapping("/change/email")
    public UserSettingVO changeEmail(){

        return new UserSettingVO();
    }
    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/detail")
    public String detail(){
        return "test";
    }



}
