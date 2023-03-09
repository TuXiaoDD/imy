package com.example.imserver.service.converter;

import java.time.LocalDateTime;

import com.example.imserver.controller.dto.RegisterDTO;
import com.example.imserver.controller.vo.ContactDetailVO;
import com.example.imserver.controller.vo.UserInfoVO;
import com.example.imserver.entity.UserDO;
import com.example.imserver.service.query.UserQuery;
import org.springframework.beans.BeanUtils;

public class UserConverter {

    public static UserInfoVO userDO2UserInfoVO(UserDO userDO) {
        Long id = userDO.getId();
        String nickname = userDO.getNickname();
        String mobile = userDO.getMobile();
        String email = userDO.getEmail();
        Integer gender = userDO.getGender();
        LocalDateTime birthday = userDO.getBirthday();
        String motto = userDO.getMotto();
        String avatar = userDO.getAvatar();


        UserInfoVO vo = new UserInfoVO();
        vo.setAvatar(avatar);
        vo.setEmail(email);
        vo.setGender(gender);
//        vo.setIsQiye();
        vo.setMobile(mobile);
        vo.setMotto(motto);
        vo.setNickname(nickname);
        vo.setUid(id);

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
