package com.example.imserver.controller;

import com.example.imserver.controller.vo.UserDetailVO;
import com.example.imserver.controller.vo.UserSettingVO;
import com.example.imserver.entity.UserDO;
import com.example.imserver.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 修改用户相关信息记得要让缓存失效
 */
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    @Autowired
    UserServiceImpl userService;

    /**
     * 用户相关设置
     *
     * @return
     */
    @GetMapping("/setting")
    public UserSettingVO setting(Long uid) {
        return userService.getSetting(uid);
    }

    /**
     * 修改用户密码
     *
     * @return
     */
    @PostMapping("/change/password")
    public UserSettingVO changePassword() {

        return new UserSettingVO();
    }

    /**
     * 修改用户手机
     *
     * @return
     */
    @PostMapping("/change/mobile")
    public UserSettingVO changeMobile() {

        return new UserSettingVO();
    }

    /**
     * 修改用户信息
     *
     * @return
     */
    @PostMapping("/change/detail")
    public UserSettingVO changeDetail() {

        return new UserSettingVO();
    }

    /**
     * 修改用户邮箱
     *
     * @return
     */
    @PostMapping("/change/email")
    public UserSettingVO changeEmail() {

        return new UserSettingVO();
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/detail")
    public UserDetailVO detail() {
        return new UserDetailVO();
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @GetMapping("/list")
    public List<UserDO> list() {
        return userService.query(null);
    }


}
