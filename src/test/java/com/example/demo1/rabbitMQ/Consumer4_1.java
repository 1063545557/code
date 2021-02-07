package com.example.demo1.rabbitMQ;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer4_1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.geConnection();
        Channel channel=connection.createChannel();

        //通道声明交换机以及交换机类型
        channel.exchangeDeclare("log_direct","direct");
        //创建一个临时队列
        String queue = channel.queueDeclare().getQueue();
        //基于route key绑定队列和交换机
        channel.queueBind(queue,"log_direct","error");
        //消费信息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1："+new String(body));
            }
        });
    }
}
