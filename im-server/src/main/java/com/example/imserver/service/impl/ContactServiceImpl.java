package com.example.imserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.page.PageQuery;
import com.example.common.utils.Assert;
import com.example.common.utils.DataUtils;
import com.example.imserver.controller.dto.AddFriendDTO;
import com.example.imserver.controller.dto.ContactListDTO;
import com.example.imserver.controller.vo.ContactDetailVO;
import com.example.imserver.controller.vo.ContactListVO;
import com.example.imserver.controller.vo.FriendApplyRecordVO;
import com.example.imserver.controller.vo.UserInfoVO;
import com.example.imserver.entity.FriendDO;
import com.example.imserver.entity.UserDO;
import com.example.imserver.service.ContactService;
import com.example.imserver.service.FriendService;
import com.example.imserver.service.UserService;
import com.example.imserver.service.converter.UserConverter;
import com.example.imserver.service.query.UserQuery;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    @Autowired
    UserService userService;
    @Autowired
    FriendService friendService;


    @Override
    public ContactDetailVO contactDetail(Long friendUid, Long uid) {
        UserQuery query = new UserQuery();
        query.setId(friendUid);
        List<UserDO> userDOS = userService.queryUser(query);
        Assert.notEmpty(userDOS, "用户不存在");
        UserDO userDO = userDOS.get(0);
        ContactDetailVO vo = new ContactDetailVO();
        vo.setAvatar(userDO.getAvatar());
        vo.setFriendApply(0);//todo
        vo.setGender(userDO.getGender());
        vo.setId(friendUid);
        vo.setMobile(userDO.getMobile());
        vo.setMotto(userDO.getMotto());
        vo.setNickname(userDO.getNickname());
//        vo.setNicknameRemark();


        FriendDO friendDO = friendService.queryByUid(uid, friendUid);
        if (DataUtils.isEmpty(friendDO)) {
            vo.setFriendStatus(1);
        }

        return vo;
    }

    @Override
    public FriendApplyRecordVO applyRecords(Long uid, PageQuery pageQuery) {
        FriendApplyRecordVO vo = new FriendApplyRecordVO();
        return vo;
    }

    @Override
    public void applyCreate(AddFriendDTO dto, Long uid) {
        FriendDO friendDO = friendService.queryByUid(uid, dto.getFriendId());


    }

    @Override
    public List<ContactListVO> queryContactList(ContactListDTO dto) {
        return friendService.list(dto.getUid(), null).stream().map(friendDO -> {
            ContactListVO vo = new ContactListVO();
//            vo.setAvatar();
//            vo.setGender();
//            vo.setGroupId();
            vo.setId(friendDO.getUid());
//            vo.setIsOnline();
//            vo.setMotto();
//            vo.setNickname();
            vo.setRemark(friendDO.getRemark());

            return vo;

        }).collect(Collectors.toList());

    }

    @Override
    public Long search(Long uid, String mobile) {
        UserQuery query = new UserQuery();
        query.setMobile(mobile);
        List<UserDO> userDOS = userService.queryUser(query);
        Assert.notEmpty(userDOS, "用户不存在");
        UserDO userDO = userDOS.get(0);
        return userDO.getId();
//
//        Long id = userDO.getId();
//        FriendDO friendDO = friendService.queryByUid(uid, id);
//        if (DataUtils.isEmpty(friendDO)) {
//            vo.setFriendStatus(1);
//        }
//        if(DataUtils.isNotEmpty(friendDO)){
//            FriendDO friendDO1 = friendService.queryByUid(id, uid);
//            if(DataUtils.isNotEmpty(friendDO1)){
//
//            }
//        }

    }
}
