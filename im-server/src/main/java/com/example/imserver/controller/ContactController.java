package com.example.imserver.controller;

import com.example.imserver.annotation.NotRequireLogin;
import com.example.imserver.controller.dto.*;
import com.example.imserver.controller.vo.*;
import com.example.imserver.controller.vo.group.GroupVo;
import com.example.imserver.service.CacheService;
import com.example.imserver.service.ContactService;
import com.example.imserver.service.FriendApplyService;
import com.example.imserver.service.FriendService;
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
    FriendService friendService;

    @Autowired
    CacheService cacheService;

    @Autowired
    FriendApplyService friendApplyService;

    /**
     * 好友申请未读消息数
     *
     * @return
     */
    @GetMapping("/apply/unread-num")
    public int unReadNum(Long uid) {
        return friendApplyService.countUnread(uid);
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
     * @description: 获取通讯录好友列表
     */
    @GetMapping("/list")
    public List<ContactListVO> contactList(ContactListDTO dto) {
        return contactService.queryContactList(dto);
    }

    @GetMapping("/detail")
    public ContactDetailVO contactDetail(@NotNull Long friendUid, Long uid) {
        return contactService.contactDetail(friendUid, uid);
    }

    /**
     * 好友申请列表
     * @param uid
     * @return
     */
    @GetMapping("/apply/records")
    public List<FriendApplyRecordVO> applyRecords(Long uid) {
        return contactService.applyRecords(uid);
    }

    /**
     * 通过手机号搜索好友
     * @param uid
     * @param mobile
     * @return
     */
    @GetMapping("/search")
    public SearchUserVO search(Long uid, @NotNull String mobile) {
        return contactService.search(uid, mobile);
    }

    /**
     * 添加好友申请
     *
     * @param dto
     * @param uid
     * @return
     */
    @PostMapping("/apply/create")
    public void applyCreate(@RequestBody @Valid AddFriendDTO dto, Long uid) {
        contactService.applyCreate(dto, uid);
    }
    /**
     * 处理好友申请
     *
     * @param dto
     * @param uid
     * @return
     */
    @PostMapping("/apply/accept")
    public void applyAccept(@RequestBody @Valid ApplyAcceptTO dto, Long uid) {
        contactService.applyAccept(dto, uid);
    }

    /**
    * @description: 删除好友
    * @params:
    * @return:
      */
    @PostMapping("/delete")
    public int deleteFriend(@RequestBody DeleteFriendDTO dto) {
        return friendService.deleteFriend(dto.getUid(), dto.getFriendUid());
    }

    /**
    * @description: 修改好有备注
    * @params:
    * @return:
      */
    @PostMapping("/edit-remark")
    public int editRemark(@RequestBody EditFriendDTO editFriendDTO) {
        Long friendUid = editFriendDTO.getFriendUid();
        return friendService.editRemark(editFriendDTO);
    }

}
