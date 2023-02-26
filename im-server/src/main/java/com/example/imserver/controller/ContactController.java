package com.example.imserver.controller;

import com.example.imserver.controller.vo.UnReadNumVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

    /**
     * 未读消息数
     *
     * @return
     */
    @GetMapping("/apply/unread-num")
    public UnReadNumVO unReadNum() {
        UnReadNumVO unReadNumVO = new UnReadNumVO();
        return unReadNumVO;
    }
}
