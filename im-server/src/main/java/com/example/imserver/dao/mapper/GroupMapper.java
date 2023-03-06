package com.example.imserver.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.imserver.entity.GroupDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * GroupMapper继承基类
 */
@Mapper
public interface GroupMapper extends BaseMapper<GroupDO> {
}