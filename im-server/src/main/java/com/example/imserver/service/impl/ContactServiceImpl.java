package com.example.imserver.service.impl;

import com.example.common.page.PageQuery;
import com.example.common.utils.Assert;
import com.example.imserver.controller.dto.AddFriendDTO;
import com.example.imserver.controller.vo.ContactDetailVO;
import com.example.imserver.controller.vo.ContactVO;
import com.example.imserver.controller.vo.FriendApplyRecordVO;
import com.example.imserver.dao.mapper.ContactMapper;
import com.example.imserver.dao.mapper.UserMapper;
import com.example.imserver.entity.FriendDO;
import com.example.imserver.entity.UserDO;
import com.example.imserver.service.ContactService;
import com.example.imserver.service.FriendService;
import com.example.imserver.service.UserService;
import com.example.imserver.service.converter.UserConverter;
import com.example.imserver.service.query.UserQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    @Autowired
    UserService userService;
    @Autowired
    FriendService friendService;

    @Autowired
    ContactMapper contactMapper;

    @Autowired
    UserMapper userMapper;


    @Override
    public ContactDetailVO contactDetail(Long friendUid, Long uid) {
        FriendDO friendDO = friendService.queryByUid(uid,friendUid);

        List<UserDO> userDOS = userService.queryUser(UserQuery.builder().id(friendUid).build());
        Assert.notEmpty(userDOS, "好友信息不存在");
        UserDO userDO = userDOS.get(0);
        ContactDetailVO contactDetailVO = UserConverter.userDO2ContactDetailVO(userDO);

        return null;
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
    @Override
    public List<ContactVO> queryContactList(Long uid) {
        //查询该uid用户下的所有好友关联信息
        List<FriendDO> friendList = contactMapper.queryFriendRelationInfo(uid);
        //获取所有好友的uid
        List<Long> friendIdList = new ArrayList<>();
        for (FriendDO friendDO : friendList) {
            Long friendUid = friendDO.getFriendUid();
            friendIdList.add(friendUid);
        }
        log.info("获取好友id结果：<{}>", friendIdList);
        //查找所有好友信息
        List<ContactVO> contactList = contactMapper.queryContactList(friendIdList);
        log.info("获取好友列表结果：<{}>", contactList);
        return contactList;
    }

    @Override
    public void applyCreate(AddFriendDTO dto, Long uid) {
        FriendDO friendDO = friendService.queryByUid(uid,dto.getFriendId());


    }
}
