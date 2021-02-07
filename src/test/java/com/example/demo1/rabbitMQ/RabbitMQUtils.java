package com.example.demo1.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtils {
    private static ConnectionFactory connectionFactory;
    static {
        connectionFactory=new ConnectionFactory();
        //重量级资源
        connectionFactory.setHost("192.168.3.212");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
    }
    //定义提供连接对象的方法
    public static Connection geConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭通道和连接的方法
    public static void closeConnectionAndChannel(Channel channel, Connection connection){
        try {
            if (channel!=null) channel.close();
            if (connection!=null) connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}