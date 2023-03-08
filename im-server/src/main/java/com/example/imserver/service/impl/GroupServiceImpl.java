package com.example.imserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.utils.DataUtils;
import com.example.imserver.controller.dto.group.GroupListDTO;
import com.example.imserver.controller.vo.group.GroupListVO;
import com.example.imserver.dao.GroupDAO;
import com.example.imserver.dao.mapper.GroupMapper;
import com.example.imserver.entity.GroupDO;
import com.example.imserver.service.GroupService;
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

    @Override
    public List<GroupListVO> groupList(GroupListDTO dto) {
        List<GroupDO> groupDOS = groupDAO.groupList(dto);
        return Optional.ofNullable(groupDOS)
                .map(list -> list.stream()
                        .map(groupDO -> GroupConverter.groupDO2VO(groupDO, dto.getUid()))
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }
}
