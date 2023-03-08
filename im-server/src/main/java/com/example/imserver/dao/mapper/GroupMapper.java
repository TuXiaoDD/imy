package com.example.imserver.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.imserver.controller.dto.group.GroupListDTO;
import com.example.imserver.entity.GroupDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * GroupMapper继承基类
 */
@Mapper
public interface GroupMapper extends BaseMapper<GroupDO> {
    List<GroupDO> groupList(@Param("dto") GroupListDTO dto);
}