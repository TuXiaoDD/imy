package com.example.imserver.service;

import com.example.common.page.PageQuery;
import com.example.imserver.controller.dto.AddFriendDTO;
import com.example.imserver.controller.vo.ContactDetailVO;
import com.example.imserver.controller.vo.ContactVO;
import com.example.imserver.controller.vo.FriendApplyRecordVO;

import java.util.List;

public interface ContactService {

    ContactDetailVO contactDetail(Long friendUid,Long uid);

    FriendApplyRecordVO applyRecords(Long uid, PageQuery pageQuery);

    void applyCreate(AddFriendDTO dto, Long uid);

    /*
       @params: [uid]
      * @return: com.example.imserver.controller.vo.ContactListVO
      * @description:     根据该用户的id，查询其所有通讯录好友列表
      */
    List<ContactVO> queryContactList(Long uid);

}
