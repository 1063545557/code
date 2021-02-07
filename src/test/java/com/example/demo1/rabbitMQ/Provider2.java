package com.example.demo1.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provider2 {
    public static void main(String[] args) throws IOException {
        Connection connection=RabbitMQUtils.geConnection();
        Channel channel=connection.createChannel();

        //将通道声明指定交换机
        //参数1：交换机名称 参数2：交换机类型  fanout 广播类型
        channel.exchangeDeclare("logs","fanout");

    }
}
