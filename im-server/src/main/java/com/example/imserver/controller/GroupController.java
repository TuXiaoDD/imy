package com.example.imserver.controller;

import com.example.imserver.controller.dto.group.GroupListDTO;
import com.example.imserver.controller.vo.LoginVO;
import com.example.imserver.controller.vo.group.GroupListVO;
import com.example.imserver.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/group")
public class GroupController {

    @Autowired
    GroupService groupService;

    /**
     * 获取群列表
     *
     * @return
     */
    @GetMapping("/list")
    public List<GroupListVO> list(GroupListDTO dto) {
       return groupService.groupList(dto);
    }

    /**
     * 获取群信息
     *
     * @return
     */
    @GetMapping("/detail")
    public List<LoginVO> detail() {
        return new ArrayList<>();
    }

    /**
     * 创建群聊
     *
     * @return
     */
    @PostMapping("/create")
    public List<LoginVO> create() {
        return new ArrayList<>();
    }

    /**
     * 群聊设置
     *
     * @return
     */
    @PostMapping("/setting")
    public List<LoginVO> setting() {
        return new ArrayList<>();
    }

    /**
     * 邀请进群
     *
     * @return
     */
    @PostMapping("/invite")
    public List<LoginVO> invite() {
        return new ArrayList<>();
    }

    /**
     * 群聊踢人
     *
     * @return
     */
    @PostMapping("/member/remove")
    public List<LoginVO> memberRemove() {
        return new ArrayList<>();
    }

    /**
     * 解散群聊
     *
     * @return
     */
    @PostMapping("/dismiss")
    public List<LoginVO> dismiss() {
        return new ArrayList<>();
    }

    /**
     * 用户退出群聊
     *
     * @return
     */
    @PostMapping("/secede")
    public List<LoginVO> secede() {
        return new ArrayList<>();
    }

    /**
     * 修改群聊名片
     *
     * @return
     */
    @PostMapping("/member/remark")
    public List<LoginVO> memberRemark() {
        return new ArrayList<>();
    }

    /**
     * 获取可加入群聊的好友列表
     *
     * @return
     */
    @PostMapping("/member/invites")
    public List<LoginVO> memberInvites() {
        return new ArrayList<>();
    }

    /**
     * 获取群组成员列表
     *
     * @return
     */
    @PostMapping("/member/list")
    public List<LoginVO> memberList() {
        return new ArrayList<>();
    }

    /**
     * 编辑群公告
     *
     * @return
     */
    @PostMapping("/notice/edit")
    public List<LoginVO> notice() {
        return new ArrayList<>();
    }

}
