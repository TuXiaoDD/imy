package com.example.imserver.service;

import javax.servlet.http.HttpServletResponse;

import com.example.imserver.controller.dto.LoginDTO;
import com.example.imserver.controller.dto.RegisterDTO;
import com.example.imserver.controller.vo.LoginVO;
import com.example.imserver.controller.vo.UserSettingVO;
import com.example.imserver.entity.UserDO;
import com.example.imserver.service.query.UserQuery;

import java.util.List;

/**
 * @packageName: com.example.imserver.service
 * @className: UserService
 * @author: leMin
 * @date: 2023/3/3  0:40
 * @description:
 */
public interface UserService {
    List<UserDO> query(List<Long> ids);

    LoginVO login(LoginDTO dto, HttpServletResponse response);

    Long register(RegisterDTO dto);

    UserSettingVO getSetting(Long uid);

    List<UserDO> queryUser(UserQuery query);

    List<Long> getNotExistUid(List<Long> ids);
}
