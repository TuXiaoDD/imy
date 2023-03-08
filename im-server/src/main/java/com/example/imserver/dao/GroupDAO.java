package com.example.imserver.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.imserver.controller.dto.group.GroupListDTO;
import com.example.imserver.dao.mapper.GroupMapper;
import com.example.imserver.dao.mapper.UserMapper;
import com.example.imserver.entity.GroupDO;
import com.example.imserver.entity.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupDAO extends ServiceImpl<GroupMapper, GroupDO> {
    @Autowired
    GroupMapper groupMapper;

    public List<GroupDO> groupList(GroupListDTO dto) {
        return groupMapper.groupList(dto);
    }
}
