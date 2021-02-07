package com.example.demo1.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provider4 {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.geConnection();
        Channel channel=connection.createChannel();
        //通过通道声明交换机
        //参数1：交换机名称 参数2：direct 路由模式
        channel.exchangeDeclare("logs_direct","direct");
        //发送消息
        String routingkey="info";
        channel.basicPublish("logs_direct",routingkey,null,("这是direct模型发布的基于route key:["+routingkey+"]").getBytes());

        //关闭资源
        RabbitMQUtils.closeConnectionAndChannel(channel,connection);
    }
}
