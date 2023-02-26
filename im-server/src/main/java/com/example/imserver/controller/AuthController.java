package com.example.imserver.controller;

import com.example.imserver.controller.dto.LoginDTO;
import com.example.imserver.controller.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
//@RequestMapping("/v1/auth")
@Slf4j
public class AuthController {

    @PostMapping("/login")
    public LoginVO test(@RequestBody LoginDTO dto){
        log.info("dto:{}",dto);
        LoginVO loginVO = new LoginVO();
        loginVO.setAccess_token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJndWFyZCI6ImFwaSIsImlzcyI6ImltLndlYiIsImV4cCI6MTY3NzQxNTA0MCwiaWF0IjoxNjc3MzI4NjQwLCJqdGkiOiIyMDU0In0.ay5bhjhU18ckcVOdbuL4uj4hq3sctL3aL6lO7SykjEw");
        loginVO.setExpires_in(86400L);
        return loginVO;
    }

    @GetMapping("test")
    public String test(){
        return "test";
    }



}
