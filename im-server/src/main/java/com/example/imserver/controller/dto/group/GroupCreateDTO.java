package com.example.imserver.controller.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 创建群聊
 */
@NoArgsConstructor
@Data
public class GroupCreateDTO {

    @JsonProperty("avatar")
    private String avatar;
    @NotNull(message = "群聊名称不能为空")
    @JsonProperty("name")
    private String name;
    @JsonProperty("profile")
    private String profile;
    @NotNull(message = "请选择用户")
    @JsonProperty("ids")
    private List<Long> ids;
}
