package com.example.imserver.controller;

import com.example.imserver.controller.vo.GroupVo;
import com.example.imserver.controller.vo.UnReadNumVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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


    /**
      * @author: leMin
      * @date: 2023/2/28  ~  23:12
      * @params: []
      * @return: java.util.List<com.example.imserver.controller.vo.GroupVo>
      * @description:     通讯录群组列表
      */
    @GetMapping("/group/list")
    public List<GroupVo> groupList() {
        List<GroupVo> groupList = new ArrayList();
        return groupList;
    }

}
