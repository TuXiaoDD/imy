package com.example.imserver.controller;

import com.example.imserver.controller.vo.TalkListVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/talk")
public class TalkController {

    /**
     * 获取聊天列表服务接口
     *
     * @return
     */
    @GetMapping("/list")
    public List<TalkListVO> list() {
        List<TalkListVO> list = new ArrayList<>();

        return list;
    }

    /**
     * 清除聊天消息未读数服务接口
     *
     * @return
     */
    @PostMapping("/unread/clear")
    public List<TalkListVO> clear() {
        List<TalkListVO> list = new ArrayList<>();
        return list;
    }
}
