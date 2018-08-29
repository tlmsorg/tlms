package com.tlms.rabitmq.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.core.ExchangeTypes;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
	public final static String QUEUE_NAME="pujjr.wms.queue";
	public final static String EXCHANGE_NAME="amq.topic";//pujjr.wms.exchange
	public final static String ROUTINGKEY="pujjr.wms.queue";
	public final static String host = "172.18.10.131";
	public final static int port = 5672;
	public final static String userName = "pjadmin";
	public final static String passWord = "Purking0326";
	public final static String virtualHost = "/test";
	
	
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(userName);
        factory.setPassword(passWord);
        factory.setVirtualHost(virtualHost);
        //创建一个新的连接
        Connection connection = factory.newConnection();
        
        //创建一个通道
        Channel channel = connection.createChannel();
        //声明一个队列        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);//queueDeclare（名字，是否持久化，独占的queue， 不使用时是否自动删除，其他参数）；
        //声明交换机
        //        channel.exchangeDeclare(EXCHANGE_NAME, "topic"); //direct fanout topic  
//        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTINGKEY); 
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME, ROUTINGKEY); 
        for (int i = 0; i < 1; i++) {
        	String message = "消息"+i;
            //发送消息到队列中
//          channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
//        	channel.basicPublish(EXCHANGE_NAME, ROUTINGKEY, null, message.getBytes("UTF-8"));
        	InterviewEntity entity = new InterviewEntity();
        	entity.setCode("001001000127");
        	entity.setPstate("7");
        	channel.basicPublish(EXCHANGE_NAME, ROUTINGKEY, null, JSONObject.toJSONString(entity).getBytes("UTF-8"));
            System.out.println("Producer Send +'" + JSONObject.toJSONString(entity) + "'");
		}
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
