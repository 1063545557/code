package com.example.demo1.rabbitMQ;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer5_1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.geConnection();
        Channel channel=connection.createChannel();

        //声明交换机以及交换机类型
        channel.exchangeDeclare("topics","topic");
        //创建一个临时队列
        String queue = channel.queueDeclare().getQueue();
        //绑定队列和交换机 动态通配符形式route key
        channel.queueBind(queue,"topics","user.*");
        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1："+new String(body));
            }
        });
    }
}
