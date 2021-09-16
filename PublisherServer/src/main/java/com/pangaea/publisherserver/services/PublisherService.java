package com.pangaea.publisherserver.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pangaea.publisherserver.dtos.HttpNotification;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Service
public class PublisherService {

    Logger logger = LoggerFactory.getLogger(PublisherService.class);

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private String port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    private ObjectMapper objectMapper;

    @Autowired
    public PublisherService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void publishToTopic(HttpNotification httpNotification) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(Integer.parseInt(port));
        factory.setUsername(username);
        factory.setPassword(password);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(httpNotification.getTopic(), "topic");
        String message =  objectMapper.writeValueAsString(httpNotification);
        channel.basicPublish(httpNotification.getTopic(), "", null,
               message.getBytes(StandardCharsets.UTF_8));
        String displayMessage = String.format("%s Published: %s to [Topic] %s", applicationName, message, httpNotification.getTopic());
        //System.out.println(displayMessage);
        logger.info(displayMessage);
    }
}
