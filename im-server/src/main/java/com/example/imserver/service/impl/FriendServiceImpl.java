package com.example.imserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.page.PageQuery;
import com.example.imserver.controller.dto.AddFriendDTO;
import com.example.imserver.controller.vo.ContactDetailVO;
import com.example.imserver.controller.vo.FriendApplyRecordVO;
import com.example.imserver.dao.mapper.FriendMapper;
import com.example.imserver.entity.FriendDO;
import com.example.imserver.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendMapper friendMapper;

    @Override
    public FriendDO queryByUid(Long uid, Long friendUid) {
        LambdaQueryWrapper<FriendDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FriendDO::getUid,uid);
        wrapper.eq(FriendDO::getFriendUid,friendUid);
        return friendMapper.selectOne(wrapper);
    }

    @Override
    public ContactDetailVO contactDetail(Long friendUid, Long uid) {
        return null;
    }

    @Override
    public FriendApplyRecordVO applyRecords(Long uid, PageQuery pageQuery) {
        return null;
    }

    @Override
    public void applyCreate(AddFriendDTO dto, Long uid) {

    }
}
