package com.example.imserver.service;

import com.example.common.page.PageQuery;
import com.example.imserver.controller.dto.AddFriendDTO;
import com.example.imserver.controller.dto.EditFriendDTO;
import com.example.imserver.controller.vo.ContactDetailVO;
import com.example.imserver.controller.vo.FriendApplyRecordVO;
import com.example.imserver.entity.FriendDO;

import java.util.List;

public interface FriendService {

    /**
     * @params: []
     * @return: java.util.List<com.example.imserver.controller.vo.ContactListVO>
     * @description: 查询通讯录好友列表
     */
//    List<ContactDO> queryContactList();

    FriendDO queryByUid(Long uid, Long friendUid);

    ContactDetailVO contactDetail(Long friendUid, Long uid);

    FriendApplyRecordVO applyRecords(Long uid, PageQuery pageQuery);

    void applyCreate(AddFriendDTO dto, Long uid);

    List<Long> getNotFriendUid(Long uid, List<Long> ids);

    List<FriendDO> list(Long uid, List<Long> friendIds);

    /*删除好友*/
    int deleteFriend(Long uid, Long friendUid);

    int editRemark(EditFriendDTO editFriendDTO);
}
