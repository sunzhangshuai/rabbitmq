package com.sunchen;

import com.rabbitmq.client.BuiltinExchangeType;
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
public class ProducerPubSub {
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
        String exchangeName = "test_fanout";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT, true, false, false, null);
        String queue1 = "test_fanout_queue1";
        String queue2 = "test_fanout_queue2";
        channel.queueDeclare(queue1, true, false, false, null);
        channel.queueDeclare(queue2, true, false, false, null);
        channel.queueBind(queue1, exchangeName, "");
        channel.queueBind(queue2, exchangeName, "");
        channel.basicPublish(exchangeName, "", null, "hello。。。。。。。".getBytes());
        channel.close();
        connection.close();
    }
}
