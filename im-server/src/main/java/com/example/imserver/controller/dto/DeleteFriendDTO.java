package com.example.imserver.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * @author: leMin
 * @className: DeleteFriendDTO
 * @date: 2023/3/11 ~ 15:08
 * @description:
 */
@Data
@NoArgsConstructor
public class DeleteFriendDTO {

    @NotNull
    private Long friendUid;

    @NotNull
    private Long uid;

}
