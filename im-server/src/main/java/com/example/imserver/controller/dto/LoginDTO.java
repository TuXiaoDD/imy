package com.example.imserver.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class LoginDTO {
    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$", message = "手机号格式不正确")
    private String mobile;
    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 16, message = "密码长度为6到16位")
    private String password;

    private String platform = "web";

}
