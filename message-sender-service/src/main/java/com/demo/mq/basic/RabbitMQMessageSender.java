package com.demo.mq.basic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

public class RabbitMQMessageSender {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try {
            Connection connection = connectionFactory.newConnection("localhost", 5672);
            Channel channel = connection.createChannel();
            String exchange = "test-exchange";
            channel.exchangeDeclare(exchange, "fanout", true);
            String queue = channel.queueDeclare("group-0001").getQueue();
            String topic = "test-topic";
            channel.queueBind(queue, exchange, topic);
            while (true) {
                String msg = ZonedDateTime.now() + ": hello, world!";
                System.out.println("Sending msg:" + msg);
                channel.basicPublish(exchange, topic, null, msg.getBytes(StandardCharsets.UTF_8));
                Thread.sleep(3 * 1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
