package com.example.demo1.rabbitMQ;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer1 {

    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection=RabbitMQUtils.geConnection();
        Channel channel=connection.createChannel();

        channel.basicQos(1); //每次只能消费一个消息
        channel.queueDeclare("work",true,false,false,null);
        //参数1：队列名称  参数2：消息自动确认 true 消费者自动向rabbitmq确认消息消费
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try{
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("消费者-1："+new String(body));
                //参数1：确认队列中那个消息  参数2：是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
