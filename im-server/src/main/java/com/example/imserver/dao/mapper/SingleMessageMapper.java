package com.example.imserver.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.imserver.entity.GroupMessageDO;
import com.example.imserver.entity.SingleMessageDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SingleMessageMapper extends BaseMapper<GroupMessageDO> {
    int deleteByPrimaryKey(Long id);

    int insert(SingleMessageDO record);

    int insertSelective(SingleMessageDO record);

    SingleMessageDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SingleMessageDO record);

    int updateByPrimaryKey(SingleMessageDO record);
}