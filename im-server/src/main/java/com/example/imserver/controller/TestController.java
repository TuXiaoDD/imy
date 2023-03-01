package com.example.imserver.controller;

import com.example.imserver.controller.vo.LoginVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    public List<LoginVO> test(){
        LoginVO loginVO = new LoginVO();
        loginVO.setExpires_in(100L);
        loginVO.setAccess_token("2321");
        List<LoginVO> list = new ArrayList<>();
        list.add(loginVO);
        return list;
    }

}
