package com.example.imserver.netty;

import lombok.Data;

@Data
public class Message {
    private MessageHeader messageHeader;
    private Object messageBody;

    @Override
    public String toString() {
        return "Message{" +
                "messageHeader=" + messageHeader +
                ", messagePack=" + messageBody +
                '}';
    }
}
