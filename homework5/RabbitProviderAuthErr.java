package com.geekbrains.geekmarketwinter.utils;

import com.geekbrains.geekmarketwinter.controllers.LogAuthController;
import com.geekbrains.geekmarketwinter.entites.LogAuth;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitProviderAuthErr {
    private static final String QUEUE_NAME = "auth-err";
    private Channel channel;
    private Connection connection;
    private LogAuthController logAuthController;

    @Autowired
    public void setLogAuthController(LogAuthController logAuthController) {
        this.logAuthController = logAuthController;
    }

    public void openConnect() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("sent " + msg);
    }

    public void receiverMsgAndLogDB() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            final DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String msg = new String(delivery.getBody(), "UTF-8");
//                System.out.println("Reciver " + msg);
                LogAuth logAuth = new LogAuth(msg);
                logAuth = logAuthController.saveLogAuth(logAuth);
                System.out.println("Reciver " + logAuth);
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
