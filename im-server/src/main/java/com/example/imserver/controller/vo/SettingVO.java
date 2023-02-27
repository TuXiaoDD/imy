package com.example.imserver.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SettingVO {

    @JsonProperty("keyboard_event_notify")
    private String keyboardEventNotify;
    @JsonProperty("notify_cue_tone")
    private String notifyCueTone;
    @JsonProperty("theme_bag_img")
    private String themeBagImg;
    @JsonProperty("theme_color")
    private String themeColor;
    @JsonProperty("theme_mode")
    private String themeMode;
}
