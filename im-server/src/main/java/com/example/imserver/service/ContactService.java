package com.example.imserver.service;

import com.example.common.page.PageQuery;
import com.example.imserver.controller.dto.AddFriendDTO;
import com.example.imserver.controller.vo.ContactDetailVO;
import com.example.imserver.controller.vo.FriendApplyRecordVO;

import java.util.List;

public interface ContactService {

    ContactDetailVO contactDetail(Long friendUid,Long uid);

    FriendApplyRecordVO applyRecords(Long uid, PageQuery pageQuery);

    void applyCreate(AddFriendDTO dto, Long uid);

    /**
     * @params: []
     * @return: java.util.List<com.example.imserver.controller.vo.ContactListVO>
     * @description: 查询通讯录好友列表
     */
//    List<ContactDO> queryContactList();
}
