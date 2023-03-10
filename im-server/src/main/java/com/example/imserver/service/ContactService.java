package com.example.imserver.service;

import com.example.common.page.PageQuery;
import com.example.imserver.controller.dto.AddFriendDTO;
import com.example.imserver.controller.dto.ApplyAcceptTO;
import com.example.imserver.controller.dto.ContactListDTO;
import com.example.imserver.controller.vo.*;

import java.util.List;

public interface ContactService {

    ContactDetailVO contactDetail(Long friendUid,Long uid);

    List<FriendApplyRecordVO> applyRecords(Long uid);

    void applyCreate(AddFriendDTO dto, Long uid);

    List<ContactListVO> queryContactList(ContactListDTO dto);

    SearchUserVO search(Long uid, String mobile);

    void applyAccept(ApplyAcceptTO dto, Long uid);

    /**
     * @params: []
     * @return: java.util.List<com.example.imserver.controller.vo.ContactListVO>
     * @description: 查询通讯录好友列表
     */
//    List<ContactDO> queryContactList();
}
