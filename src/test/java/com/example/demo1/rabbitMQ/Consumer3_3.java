package com.example.demo1.rabbitMQ;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer3_3 {
    public static void main(String[] args) throws IOException {
        //连接对象
        Connection connection = RabbitMQUtils.geConnection();
        Channel channel=connection.createChannel();

        //通道绑定交换机
        channel.exchangeDeclare("logs","fanout");
        //临时队列
        String queue = channel.queueDeclare().getQueue();
        //绑定交换机和队列
        channel.queueBind(queue,"logs","");
        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者3："+new String(body));
            }
        });
    }
}
