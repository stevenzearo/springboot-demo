package com.demo.mq.basic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

public class RabbitMQMessageReceiver {
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
            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(queue, consumer);
            while (true) {
                QueueingConsumer.Delivery delivery = consumer.getQueue().take().getValue();
                byte[] body = delivery.getBody();
                System.out.println("get content: " + new String(body));
                Thread.sleep(500);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), true); // ack to avoid repeat consuming
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
