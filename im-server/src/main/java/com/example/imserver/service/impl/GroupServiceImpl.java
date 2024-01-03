package com.example.imserver.service.impl;

import com.example.imserver.controller.dto.group.GroupCreateDTO;
import com.example.imserver.controller.dto.group.GroupListDTO;
import com.example.imserver.controller.vo.group.GroupListVO;
import com.example.imserver.dao.GroupDAO;
import com.example.imserver.dao.mapper.GroupMapper;
import com.example.imserver.entity.GroupDO;
import com.example.imserver.service.FriendService;
import com.example.imserver.service.GroupService;
import com.example.imserver.service.UserService;
import com.example.imserver.service.converter.GroupConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupDAO groupDAO;

    @Autowired
    UserService userService;
    @Autowired
    FriendService friendService;

    @Override
    public List<GroupListVO> groupList(GroupListDTO dto) {
        List<GroupDO> groupDOS = groupDAO.groupList(dto);
        return Optional.ofNullable(groupDOS)
                .map(list -> list.stream()
                        .map(groupDO -> GroupConverter.groupDO2VO(groupDO, dto.getUid()))
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }

    @Override
    public Long create(GroupCreateDTO dto, Long uid) {
        List<Long> ids = dto.getIds();
        Assert.isTrue(ids.size() > 1, "至少选取两个好友");
        // 判断用户是否存在
        List<Long> notExistUids = userService.getNotExistUid(ids);
        Assert.isEmpty(notExistUids, notExistUids + "用户不存在");
        // 判断是否是好友关系
        List<Long> notFriendUids = friendService.getNotFriendUid(uid, ids);
        Assert.isEmpty(notFriendUids, notFriendUids + "用户不是你的好友");
        // 判断存不存在相同的群聊


        GroupMapper baseMapper = groupDAO.getBaseMapper();

        return (long) baseMapper.insert(GroupConverter.groupCreateDTO2DO(dto, uid));
    }
}
