package com.example.imserver.controller;

import com.example.imserver.controller.vo.CollectEmoticonVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: leMin
 * @className: EmotionController
 * @date: 2023/2/28 ~ 23:44
 * @description:
 */
@RestController("/api/v1/emoticon")
public class EmoticonController {

    /**
      * @author: leMin
      * @date: 2023/2/28  ~  23:50
      * @params: []
      * @return: java.util.List<com.example.imserver.controller.vo.CollectEmoticonVo>
      * @description:     查询表情符号集合
      */
    @GetMapping("/list")
    public List<CollectEmoticonVo> emotionList() {
        List<CollectEmoticonVo> emoticonVoList = new ArrayList<>();
        return emoticonVoList;
    }

}
