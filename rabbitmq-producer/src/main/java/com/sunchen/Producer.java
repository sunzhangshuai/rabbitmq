package com.sunchen;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Producer:
 *
 * @author sunchen
 * @date 2020/7/25 5:49 下午
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建工厂
        ConnectionFactory factory = new ConnectionFactory();
        //2.设置参数
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/sunchen");
        factory.setUsername("zhangshuai");
        factory.setPassword("sunsun520");
        //3.创建连接
        Connection connection = factory.newConnection();
        //4.创建channel
        Channel channel = connection.createChannel();
        //5.创建队列
        /**
         * String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
         * queue 队列
         * durable 重启后是否还在
         * exclusive 1.是否独占，只能一个消费者监听 2. 当connection关闭时，是否删除队列
         * autoDelete 当没有consumer时，是否删除掉
         * arguments 参数
         */
        channel.queueDeclare("order", true, false, false, null);
        //6.发消息
        channel.basicPublish("", "order", null, "32009090099999".getBytes());
        //
        channel.close();
        connection.close();
    }
}
