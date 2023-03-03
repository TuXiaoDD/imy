package com.example.imserver.service.converter;

import com.example.imserver.controller.vo.UserInfoVO;
import com.example.imserver.entity.UserDO;
import org.springframework.beans.BeanUtils;

public class UserConverter {

    public static UserInfoVO userDO2UserInfoVO(UserDO userDO){
        UserInfoVO vo = new UserInfoVO();
        BeanUtils.copyProperties(userDO,vo);
        return vo;
    }
}