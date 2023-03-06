package com.example.imserver.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.imserver.entity.GroupDO;
import com.example.imserver.entity.GroupMemberDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupMemberMapper extends BaseMapper<GroupMemberDO> {

}