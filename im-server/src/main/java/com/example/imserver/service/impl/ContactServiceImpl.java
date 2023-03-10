package com.example.imserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.page.PageQuery;
import com.example.common.utils.Assert;
import com.example.common.utils.DataUtils;
import com.example.imserver.controller.dto.AddFriendDTO;
import com.example.imserver.controller.dto.ApplyAcceptTO;
import com.example.imserver.controller.dto.ContactListDTO;
import com.example.imserver.controller.vo.*;
import com.example.imserver.dao.mapper.FriendApplyMapper;
import com.example.imserver.dao.mapper.FriendMapper;
import com.example.imserver.dao.mapper.UserMapper;
import com.example.imserver.entity.FriendApplyDO;
import com.example.imserver.entity.FriendDO;
import com.example.imserver.entity.UserDO;
import com.example.imserver.enums.ApplyStatusEnum;
import com.example.imserver.enums.FriendStatusEnum;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
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
    @Autowired
    FriendMapper friendMapper;


    @Autowired
    FriendApplyMapper friendApplyMapper;


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
    public List<FriendApplyRecordVO> applyRecords(Long uid) {
        LambdaQueryWrapper<FriendApplyDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FriendApplyDO::getIsDelete, 0);
        wrapper.eq(FriendApplyDO::getApplyUid, uid);
        wrapper.eq(FriendApplyDO::getApplyStatus, ApplyStatusEnum.APPLYING.getCode());
        List<FriendApplyDO> friendApplyDOS = friendApplyMapper.selectList(wrapper);
        if (DataUtils.isEmpty(friendApplyDOS)) return List.of();
        List<Long> uids = friendApplyDOS.stream().map(FriendApplyDO::getUid).collect(Collectors.toList());
        List<UserDO> userDOS = userService.query(uids);
        Assert.notEmpty(userDOS, "用户信息为空");
        Map<Long, UserDO> userMap = userDOS.stream().collect(Collectors.toMap(UserDO::getId, Function.identity(), (v1, v2) -> v1));
        return friendApplyDOS.stream().map(friendApplyDO -> {
            UserDO userDO = userMap.get(friendApplyDO.getUid());
            FriendApplyRecordVO vo = new FriendApplyRecordVO();
            vo.setAvatar(userDO.getAvatar());
            vo.setCreatedAt(friendApplyDO.getAddTime());
            vo.setFriendId(userDO.getId());
            vo.setId(friendApplyDO.getId());
            vo.setNickname(userDO.getNickname());
            vo.setRemark(friendApplyDO.getRemark());
            vo.setUserId(uid);
            return vo;
        }).collect(Collectors.toList());
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
    @Transactional
    public void applyCreate(AddFriendDTO dto, Long uid) {
        Long friendId = dto.getFriendId();
        // 查friendId是不是我的好友
        FriendDO friendDO = friendService.queryByUid(uid, friendId);
        if (DataUtils.isNotEmpty(friendDO)) { // 黑名单逻辑暂时不管
            // 查uid是不是friendId的好友
            FriendDO friendDO1 = friendService.queryByUid(friendId, uid);
            Assert.isNull(friendDO1, "已经是好友关系");
        }
        LambdaQueryWrapper<FriendApplyDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FriendApplyDO::getIsDelete, 0);
        wrapper.eq(FriendApplyDO::getUid, uid);
        wrapper.eq(FriendApplyDO::getApplyUid, friendId);
        wrapper.orderBy(true, false, FriendApplyDO::getAddTime);
        FriendApplyDO friendApplyDO = friendApplyMapper.selectOne(wrapper);
        Assert.isFalse(DataUtils.isNotEmpty(friendApplyDO) &&
                Objects.equals(friendApplyDO.getApplyStatus(), ApplyStatusEnum.APPLYING.getCode()), "已发送好友申请，请等待好友恢复。");
        FriendApplyDO applyDO = new FriendApplyDO();
        applyDO.setUid(uid);
        applyDO.setApplyUid(dto.getFriendId());
        applyDO.setRelationType(dto.getRelationType());
        applyDO.setRemark(dto.getRemark());
        friendApplyMapper.insert(applyDO);
    }

    @Override
    public List<ContactListVO> queryContactList(ContactListDTO dto) {
        List<FriendDO> list = friendService.list(dto.getUid(), null);
        if (DataUtils.isEmpty(list)) return List.of();
        List<Long> friendIds = list.stream().map(FriendDO::getFriendUid).collect(Collectors.toList());
        List<UserDO> userDOS = userService.query(friendIds);
        Assert.notEmpty(userDOS, "用户信息为空");
        Map<Long, UserDO> userDOMap = userDOS.stream().collect(Collectors.toMap(UserDO::getId, Function.identity(), (v1, v2) -> v1));
        return list.stream().map(friendDO -> {
            UserDO userDO = userDOMap.get(friendDO.getFriendUid());
            ContactListVO vo = new ContactListVO();
            vo.setAvatar(userDO.getAvatar());
            vo.setGender(userDO.getGender());
//            vo.setGroupId();
            vo.setId(friendDO.getUid());
//            vo.setIsOnline();
            vo.setMotto(userDO.getMotto());
            vo.setNickname(userDO.getNickname());
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
    }

    @Override
    @Transactional
    public void applyAccept(ApplyAcceptTO dto, Long uid) {
        FriendApplyDO applyDO = friendApplyMapper.selectById(dto.getApplyId());
        Assert.notNull(applyDO, "未找到好友申请信息");
        Assert.isTrue(Objects.equals(applyDO.getApplyStatus(), ApplyStatusEnum.APPLYING.getCode()), "该条好友申请已处理");
        applyDO.setApplyStatus(ApplyStatusEnum.AGREE.getCode());
        friendApplyMapper.updateById(applyDO);

        // 发起申请的那个人
        Long applyUid = applyDO.getUid();
        FriendDO friendDO = friendService.queryByUid(uid, applyUid);
        if (DataUtils.isNotEmpty(friendDO)) {
            log.info("已经是好友关系 uid {} friendId {}", uid, applyUid);
            return;
        }
        FriendDO friendDONew = new FriendDO();
        friendDONew.setFriendUid(applyUid);
        friendDONew.setUid(uid);
        friendDONew.setRemark(dto.getRemark());
        friendDONew.setRelationType(applyDO.getRelationType());
//        friendDONew.setLatestMsg();
//        friendDONew.setDirection();
//        friendDONew.setUnread();
        friendDONew.setFriendStatus(FriendStatusEnum.NORMAL.getStatus());
        friendMapper.insert(friendDONew);

        FriendDO friendDO1 = friendService.queryByUid(applyUid, uid);
        if (DataUtils.isNotEmpty(friendDO1)) return;
        FriendDO friendDONew1 = new FriendDO();
        friendDONew1.setFriendUid(uid);
        friendDONew1.setUid(applyUid);
        friendDONew1.setRelationType(applyDO.getRelationType());
        friendDONew1.setFriendStatus(FriendStatusEnum.NORMAL.getStatus());
        friendMapper.insert(friendDONew1);

    }
}
