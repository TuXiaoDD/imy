package com.example.imserver.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.imserver.entity.GroupMemberDO;
import com.example.imserver.entity.GroupMessageDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupMessageMapper extends BaseMapper<GroupMessageDO> {
    int deleteByPrimaryKey(Long id);

    int insert(GroupMessageDO record);

    int insertSelective(GroupMessageDO record);

    GroupMessageDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupMessageDO record);

    int updateByPrimaryKey(GroupMessageDO record);
}