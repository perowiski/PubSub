package com.pangaea.subscriberserver.services;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class SubscriberService {

    Logger logger = LoggerFactory.getLogger(SubscriberService.class);

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

    public void subscribeToTopic(String topic) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(Integer.parseInt(port));
        factory.setUsername(username);
        factory.setPassword(password);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(topic, "topic");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, topic, "");
        String waitingMessage = String.format("%s Waiting for messages. from [Topic] %s", applicationName, topic);
        //System.out.println(waitingMessage);
        logger.info(waitingMessage);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            String displayMessage = String.format("%s Received: %s from [Topic] %s", applicationName, message, topic);
            //System.out.println(displayMessage);
            logger.info(displayMessage);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }
}
