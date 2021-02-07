package com.example.demo1.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Provider {

    @Test
    //生产消息
    public void testSendMessage() throws IOException, TimeoutException {

        //创建连接mq的连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置连接rabbitmq主机
        connectionFactory.setHost("192.168.3.212");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置连接哪个虚拟主机
        connectionFactory.setVirtualHost("/ems");
        //设置访问虚拟主机的用户名，密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");

        //获取连接对象
        Connection connection=connectionFactory.newConnection();
        //Connection connection1=RabbitMQUtils.geConnection();

        //获取连接中的通道
        Channel channel = connection.createChannel();

        //通道绑定最终的消息队列
        //参数1：队列名称，如果队列不存在自动创建
        //参数2：用来定义队列特性是否持久化
        //参数3：是否独占队列
        //参数4：是否再消费完成后自动删除队列
        //参数5：附加额外参数
        channel.queueDeclare("hello",false,false,false,null);

        //发布消息
        //参数1：交换机的名称  参数2：队列名称  参数3：传递消息额外设置  参数4：消息的具体内容
        channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello rabbitmq".getBytes());

        channel.close();
        connection.close();
        RabbitMQUtils.closeConnectionAndChannel(channel,connection);
    }
}
