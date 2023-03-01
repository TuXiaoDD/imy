package com.example.imserver.entity;

import java.io.Serializable;
import java.util.Date;

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
    private Byte gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 个性签名
     */
    private String motto;

    /**
     * 头像
     */
    private String avatar;

    private Date addTime;

    private Date updateTime;

    private Byte isDelete;

}