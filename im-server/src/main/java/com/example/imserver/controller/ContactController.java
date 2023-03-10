package com.example.imserver.controller;

import com.example.common.page.PageQuery;
import com.example.imserver.annotation.NotRequireLogin;
import com.example.imserver.controller.dto.AddFriendDTO;
import com.example.imserver.controller.dto.ContactListDTO;
import com.example.imserver.controller.vo.*;
import com.example.imserver.controller.vo.group.GroupVo;
import com.example.imserver.service.CacheService;
import com.example.imserver.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


/**
 * 包含群聊 单聊
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

    @Autowired
    ContactService contactService;

    @Autowired
    CacheService cacheService;

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
     * @return: java.util.List<com.example.imserver.controller.vo.group.GroupVo>
     * @description: 获取通讯录群组列表
     */
    @GetMapping("/group/list")
    @NotRequireLogin
    public List<GroupVo> groupList() {
        List<GroupVo> groupList = new ArrayList();
        return groupList;
    }

    /**
      * @params: []
      * @return: java.util.List<com.example.imserver.controller.vo.ContactListVO>
      * @description:  获取通讯录好友列表
      */
    @GetMapping("/list")
    public List<ContactListVO> contactList(ContactListDTO dto) {
        return contactService.queryContactList(dto);
    }

    @PostMapping("/add")
    @NotRequireLogin
    public Long addContact() {

        return 1L;
    }

    @GetMapping("/detail")
    public ContactDetailVO contactDetail(@NotNull Long friendUid,Long uid) {
        return contactService.contactDetail(friendUid,uid);
    }

    @GetMapping("/apply/records")
    public FriendApplyRecordVO applyRecords(Long uid, PageQuery pageQuery) {
        return contactService.applyRecords(uid, pageQuery);
    }

    @GetMapping("/search")
    public SearchUserVO search(Long uid, @NotNull String mobile) {
        return contactService.search(uid, mobile);
    }

    /**
     * 添加好友
     * @param dto
     * @param uid
     * @return
     */
    @PostMapping("/apply/create")
    public void applyCreate(@RequestBody @Valid AddFriendDTO dto, Long uid) {
        contactService.applyCreate(dto, uid);
    }
}
