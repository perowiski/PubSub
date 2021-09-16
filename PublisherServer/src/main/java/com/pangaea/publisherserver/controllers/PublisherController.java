package com.pangaea.publisherserver.controllers;

import com.pangaea.publisherserver.dtos.HttpNotification;
import com.pangaea.publisherserver.dtos.PublisherHttpResponseDto;
import com.pangaea.publisherserver.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class PublisherController {

    private PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService){
        this.publisherService = publisherService;
    }

    @PostMapping(path = "/publish/{topic}")
    public PublisherHttpResponseDto publish(@PathVariable String topic,
                                            @RequestBody String requestBody) throws IOException, TimeoutException {
        HttpNotification httpNotification = new HttpNotification(topic, requestBody);
        publisherService.publishToTopic(httpNotification);
        return new PublisherHttpResponseDto();
    }
}
