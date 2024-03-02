package com.lym.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DispatcherInstance {
    private String host;
    private String ip;
    private int port;
}
