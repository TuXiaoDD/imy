package com.example.imserver.controller;

import com.example.imserver.controller.dto.LoginDTO;
import com.example.imserver.controller.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
//@RequestMapping("/v1/auth")
@Slf4j
public class UserController {

//    @GetMapping("/setting")
//    public Ob setting(){
//
//    }

    @GetMapping("test")
    public String test(){
        return "test";
    }



}
