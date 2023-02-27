package com.example.imserver.controller;

import com.example.imserver.controller.dto.LoginDTO;
import com.example.imserver.controller.dto.RegisterDTO;
import com.example.imserver.controller.dto.SmsCodeDTO;
import com.example.imserver.controller.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    @PostMapping("/login")
    public LoginVO login(@RequestBody LoginDTO dto){
        log.info("dto:{}",dto);
        LoginVO loginVO = new LoginVO();
        loginVO.setAccess_token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJndWFyZCI6ImFwaSIsImlzcyI6ImltLndlYiIsImV4cCI6MTY3NzQxNTA0MCwiaWF0IjoxNjc3MzI4NjQwLCJqdGkiOiIyMDU0In0.ay5bhjhU18ckcVOdbuL4uj4hq3sctL3aL6lO7SykjEw");
        loginVO.setExpires_in(86400L);
        return loginVO;
    }
    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO dto){

        return "";
    }

    @GetMapping("test")
    public String test(){
        return "test";
    }



}
