package com.lym.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DispatcherInstanceInfo {
    private String host;
    private int port;
}
