package com.example.imserver.controller;

import com.example.imserver.controller.vo.TalkListVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/talk")
public class TalkController {
    @GetMapping("/list")
    public List<TalkListVO> list() {
        List<TalkListVO> list= new ArrayList<>();
        return list;
    }


    @GetMapping("/records")
    public List<TalkListVO> list() {
        List<TalkListVO> list= new ArrayList<>();
        return list;
    }
}
