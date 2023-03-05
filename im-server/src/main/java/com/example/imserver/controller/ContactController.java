package com.example.imserver.controller;

import com.example.common.page.PageQuery;
import com.example.imserver.controller.vo.*;
import com.example.imserver.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

    @Autowired
    ContactService contactService;

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
     * @description: 通讯录群组列表
     */
    @GetMapping("/group/list")
    public List<GroupVo> groupList() {
        List<GroupVo> groupList = new ArrayList();
        return groupList;
    }

    @GetMapping("/list")
    public List<ContactListVO> contactList() {
        List<ContactListVO> contactListVOS = new ArrayList();
        return contactListVOS;
    }

    @GetMapping("/detail")
    public ContactDetailVO contactDetail(@NotNull Long friendUid,Long uid) {
        return contactService.contactDetail(friendUid,uid);
    }

    @GetMapping("/apply/records")
    public FriendApplyRecordVO applyRecords(Long uid, PageQuery pageQuery) {
        return contactService.applyRecords(uid, pageQuery);
    }
}
