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

    @GetMapping("/setting")
    public UserSettingVO setting(){

        return new UserSettingVO();
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }



}
