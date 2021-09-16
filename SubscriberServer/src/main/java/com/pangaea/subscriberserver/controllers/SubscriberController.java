package com.pangaea.subscriberserver.controllers;

import com.pangaea.subscriberserver.dtos.SubscriberHttpRequestDto;
import com.pangaea.subscriberserver.dtos.SubscriberHttpResponseDto;
import com.pangaea.subscriberserver.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class SubscriberController {

    private SubscriberService subscriberService;

    @Autowired
    public SubscriberController(SubscriberService subscriberService){
        this.subscriberService = subscriberService;
    }

    @PostMapping(path = "/subscribe/{topic}")
    public SubscriberHttpResponseDto subscribe(@PathVariable String topic,
                                               @RequestBody SubscriberHttpRequestDto subscriberHttpRequestDto) throws IOException, TimeoutException {
        subscriberService.subscribeToTopic(topic);
        return new SubscriberHttpResponseDto(topic, subscriberHttpRequestDto.getUrl());
    }
}
