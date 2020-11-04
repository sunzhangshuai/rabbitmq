package com.sunchen.route;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Consumer:
 *
 * @author sunchen
 * @date 2020/7/25 6:40 下午
 */
public class ConsumerRouting2 {
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
        String queue1 = "test_direct_queue2";
        //6.接收消息
        channel.basicConsume(queue1, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body:" + new String(body));
            }
        });
    }
}
