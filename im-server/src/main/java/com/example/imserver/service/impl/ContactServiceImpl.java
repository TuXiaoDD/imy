package com.example.imserver.service.impl;

import com.example.common.page.PageQuery;
import com.example.common.utils.Assert;
import com.example.common.utils.DataUtils;
import com.example.imserver.controller.dto.AddFriendDTO;
import com.example.imserver.controller.dto.ContactListDTO;
import com.example.imserver.controller.vo.*;
import com.example.imserver.dao.mapper.UserMapper;
import com.example.imserver.entity.FriendDO;
import com.example.imserver.entity.UserDO;
import com.example.imserver.service.ContactService;
import com.example.imserver.service.FriendService;
import com.example.imserver.service.UserService;
import com.example.imserver.service.converter.UserConverter;
import com.example.imserver.service.query.UserQuery;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    @Autowired
    UserService userService;
    @Autowired
    FriendService friendService;

    @Autowired
    UserMapper userMapper;


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

    /**
     * @params: []
     * @return: java.util.List<com.example.imserver.controller.vo.ContactListVO>
     * @description: 根据该用户的id，查询其所有通讯录好友列表
     */
//    @Override
//    public List<ContactVO> queryContactList(Long uid) {
//        //查询该uid用户下的所有好友关联信息
//        List<FriendDO> friendList = contactMapper.queryFriendRelationInfo(uid);
//        //获取所有好友的uid
//        List<Long> friendIdList = new ArrayList<>();
//        for (FriendDO friendDO : friendList) {
//            Long friendUid = friendDO.getFriendUid();
//            friendIdList.add(friendUid);
//        }
//        log.info("获取好友id结果：<{}>", friendIdList);
//        //查找所有好友信息
//        List<ContactVO> contactList = contactMapper.queryContactList(friendIdList);
//        log.info("获取好友列表结果：<{}>", contactList);
//        return contactList;
//    }
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
    public SearchUserVO search(Long uid, String mobile) {
        UserQuery query = new UserQuery();
        query.setMobile(mobile);
        List<UserDO> userDOS = userService.queryUser(query);
        Assert.notEmpty(userDOS, "用户不存在");
        UserDO userDO = userDOS.get(0);
        SearchUserVO vo = new SearchUserVO();
        BeanUtils.copyProperties(userDO, vo);
        return vo;
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
