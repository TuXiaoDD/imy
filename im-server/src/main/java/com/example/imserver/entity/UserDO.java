package com.example.imserver.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * user
 *
 * @author
 */
@Data
@TableName("user")
public class UserDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名称
     */
    private String nickname;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 个性签名
     */
    private String motto;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 注册时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 密码
     */
    private String password;

}