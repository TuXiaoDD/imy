package com.example.imserver.controller;

import com.example.imserver.controller.dto.SmsCodeDTO;
import com.example.imserver.controller.vo.LoginVO;
import com.example.imserver.controller.vo.SmsCodeVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 杂项
 */
@RestController
@RequestMapping("/api/v1/")
public class CommonController {

    /**
     * 短信验证码
     * @return
     */
    @PostMapping("/common/sms-code")
    public SmsCodeVO smsCode(@RequestBody SmsCodeDTO dto){

        return new SmsCodeVO();
    }
}
