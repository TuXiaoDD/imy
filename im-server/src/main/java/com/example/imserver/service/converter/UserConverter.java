package com.example.imserver.service.converter;

import com.example.imserver.controller.dto.RegisterDTO;
import com.example.imserver.controller.vo.ContactDetailVO;
import com.example.imserver.controller.vo.UserInfoVO;
import com.example.imserver.entity.UserDO;
import com.example.imserver.service.query.UserQuery;
import org.springframework.beans.BeanUtils;

public class UserConverter {

    public static UserInfoVO userDO2UserInfoVO(UserDO userDO) {
        UserInfoVO vo = new UserInfoVO();
        BeanUtils.copyProperties(userDO, vo);
        return vo;
    }

    public static ContactDetailVO userDO2ContactDetailVO(UserDO user) {
        ContactDetailVO vo = new ContactDetailVO();
        vo.setId(user.getId());
        vo.setMotto(user.getMotto());
        vo.setMobile(user.getMobile());
        vo.setGender(user.getGender());
        vo.setAvatar(user.getAvatar());
        vo.setNickname(user.getNickname());
        return vo;
    }
}
