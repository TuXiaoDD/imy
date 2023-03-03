package com.example.imserver.controller;

import com.example.imserver.controller.vo.LoginVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/upload")
public class UploadController {

    /**
     * 上传头像
     * @return
     */
    @PostMapping("/avatar")
    public String avatar(){

        return "ne";
    }

    /**
     * 查询大文件拆分信息服务接口
     * @return
     */
    @GetMapping("/multipart/initiate")
    public String initiate(){

        return "ne";
    }
    /**
     * 文件拆分上传服务接口
     * @return
     */
    @PostMapping("/multipart")
    public String multipart(){

        return "ne";
    }

}
