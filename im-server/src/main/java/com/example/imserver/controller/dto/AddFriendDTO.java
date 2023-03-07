package com.example.imserver.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class AddFriendDTO {

    @JsonProperty("friend_id")
    @NotNull
    private Long friendId;
    @JsonProperty("remark")
    private String remark;
}
