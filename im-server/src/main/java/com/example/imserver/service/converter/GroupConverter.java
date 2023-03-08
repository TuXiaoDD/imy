package com.example.imserver.service.converter;

import com.example.imserver.controller.dto.group.GroupCreateDTO;
import com.example.imserver.controller.vo.group.GroupListVO;
import com.example.imserver.entity.GroupDO;

import java.util.Objects;

public class GroupConverter {

    public static GroupListVO groupDO2VO(GroupDO groupDO,Long uid){
        GroupListVO vo = new GroupListVO();
        vo.setAvatar(groupDO.getAvatar());
        vo.setGroupName(groupDO.getGroupName());
        vo.setId(groupDO.getId());
        vo.setIsDisturb(groupDO.getIsDisturb());
        // 前端群主是2
        vo.setLeader(Objects.equals(uid,groupDO.getMasterUid())?2:0);
        vo.setProfile(groupDO.getAnnounce());
        return vo;
    }

    public static GroupDO groupCreateDTO2DO(GroupCreateDTO dto,Long uid) {
        GroupDO groupDO = new GroupDO();
        groupDO.setGroupName(dto.getName());
        groupDO.setMasterUid(uid);
        groupDO.setIntroduce(dto.getProfile());
        groupDO.setAvatar(dto.getAvatar());
        return groupDO;
    }
}
