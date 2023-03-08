package com.example.imserver.service;

import com.example.imserver.controller.dto.group.GroupCreateDTO;
import com.example.imserver.controller.dto.group.GroupListDTO;
import com.example.imserver.controller.vo.group.GroupListVO;

import java.util.List;

public interface GroupService {
    List<GroupListVO> groupList(GroupListDTO dto);

    Long create(GroupCreateDTO dto,Long uid);
}
