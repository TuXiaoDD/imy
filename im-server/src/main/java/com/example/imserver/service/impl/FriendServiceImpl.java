package com.example.imserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.page.PageQuery;
import com.example.common.utils.DataUtils;
import com.example.imserver.controller.dto.AddFriendDTO;
import com.example.imserver.controller.vo.ContactDetailVO;
import com.example.imserver.controller.vo.FriendApplyRecordVO;
import com.example.imserver.dao.mapper.FriendMapper;
import com.example.imserver.entity.FriendDO;
import com.example.imserver.enums.FriendStatusEnum;
import com.example.imserver.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendMapper friendMapper;

    /**
     * 查询uid的好友 好友的uid是friendUid
     *
     * @param uid
     * @param friendUid
     * @return
     */
    @Override
    public FriendDO queryByUid(Long uid, Long friendUid) {
        LambdaQueryWrapper<FriendDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FriendDO::getFriendStatus, 0);
        wrapper.eq(FriendDO::getUid, uid);
        wrapper.eq(FriendDO::getFriendUid, friendUid);
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

    public List<FriendDO> list(Long uid, List<Long> friendIds) {
        LambdaQueryWrapper<FriendDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FriendDO::getFriendStatus, FriendStatusEnum.NORMAL.getStatus());
        wrapper.in(DataUtils.isNotEmpty(friendIds), FriendDO::getFriendUid, friendIds);
        wrapper.eq(FriendDO::getUid, uid);
        return friendMapper.selectList(wrapper);
    }


    @Override
    public List<Long> getNotFriendUid(Long uid, List<Long> ids) {
        List<Long> collect = list(uid, ids).stream().map(FriendDO::getFriendUid).collect(Collectors.toList());
        if (!new HashSet<>(ids).containsAll(collect)) {
            ids.removeAll(collect);
            return ids;
        }
        // 查询ids用户是否把uid删除或加黑名单
        LambdaQueryWrapper<FriendDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(FriendDO::getFriendStatus, FriendStatusEnum.notFriendStatus());
        queryWrapper.in(FriendDO::getUid, ids);
        queryWrapper.eq(FriendDO::getFriendUid, uid);
        List<FriendDO> friendDOList = friendMapper.selectList(queryWrapper);
        if (DataUtils.isNotEmpty(friendDOList))
            return friendDOList.stream().map(FriendDO::getId).collect(Collectors.toList());
        return List.of();
    }
}
