package com.pangaea.subscriberserver.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SubscriberHttpResponseDto {
    private String url;
    private String topic;

    public SubscriberHttpResponseDto(String topic, String url) {
        this.topic = topic;
        this.url = url;
    }
}
