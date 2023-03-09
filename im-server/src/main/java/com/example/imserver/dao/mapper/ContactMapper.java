package com.example.imserver.dao.mapper;



import com.example.imserver.controller.vo.ContactVO;
import com.example.imserver.entity.FriendDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
@Mapper
public interface ContactMapper {
    /**
     *  根据该用户的id，查询其所有通讯录好友列表
     */
    List<ContactVO> queryContactList(@Param("friendIdList") List<Long> friendIdList);

    /**
     *  根据该用户的id，查询其所有好友关联信息
     */
    List<FriendDO> queryFriendRelationInfo(Long uid);

}