package com.pangaea.publisherserver.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HttpNotification {
    private String topic;
    private Object data;

    public HttpNotification(String topic, Object data){
        this.topic = topic;
        this.data = data;
    }
}
