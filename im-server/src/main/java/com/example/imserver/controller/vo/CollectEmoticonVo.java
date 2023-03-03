package com.example.imserver.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: leMin
 * @className: CollectEmoticonVo
 * @date: 2023/2/28 ~ 23:48
 * @description:    表情符号
 */
@NoArgsConstructor
@Data
public class CollectEmoticonVo {

    @JsonProperty("media_id")
    private Integer mediaId;
    @JsonProperty("src")
    private String src;
}
