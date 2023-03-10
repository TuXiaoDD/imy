package com.example.imserver.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.imserver.entity.FriendApplyDO;
import com.example.imserver.entity.FriendDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FriendApplyMapper extends BaseMapper<FriendApplyDO> {
    int deleteByPrimaryKey(Long id);

    int insert(FriendApplyDO record);

    int insertSelective(FriendApplyDO record);

    FriendApplyDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FriendApplyDO record);

    int updateByPrimaryKey(FriendApplyDO record);
}