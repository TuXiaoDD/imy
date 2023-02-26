package com.example.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/test/v1")
    public String test1(){
        return "test v1";
    }

}
