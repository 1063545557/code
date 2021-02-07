package com.example.demo1.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provider1 {
    public static void main(String[] args) throws IOException {
        //连接对象
        Connection connection=RabbitMQUtils.geConnection();
        //获取通道对象
        Channel channel=connection.createChannel();

        //通过通道声明队列
        channel.queueDeclare("work",true,false,false,null);

        for (int i = 0; i <10 ; i++) {
            //生产消息
            channel.basicPublish("","work",null,(i+"hello work queue").getBytes());
        }

        //关闭资源
        RabbitMQUtils.closeConnectionAndChannel(channel,connection);
    }
}
