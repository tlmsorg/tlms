package com.tlms.rabitmq.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
	public final static String virtualHost = "test";
	public final static String EXCHANGE_NAME="pujjr.wms.exchange";//pujjr.wms.exchange，amq.topic
	public final static String ROUTINGKEY="pujjr.wms.queue";
	public final static String QUEUE_NAME="pujjr.wms.queue";
	
	public final static String host = "172.18.10.131";
	public final static int port = 5672;
	public final static String userName = "pjadmin";
	public final static String passWord = "Purking0326";
	
	
	
    public static void main(String[] args) throws IOException, TimeoutException {
    	long timeBegin = System.currentTimeMillis();
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
        /**
         * 1、声明一个队列        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
         * autoDelete
         * 		true:consumer线程停止后，自动删除队列
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);//queueDeclare（名字，是否持久化，独占的queue， 不使用时是否自动删除，其他参数）；
        /**
         * 2、声明交换机
         */
//        channel.exchangeDeclare(EXCHANGE_NAME, "topic"); //direct fanout topic  
        /**
         * 声明一个持久化交换机
         * type：交换机类型，支持三种交换机类型：direct、fanout、topic
         * 		direct:完全根据key进行投递的叫做Direct交换机，例如，绑定时设置了routing key为"abc"，那么客户端提交的消息，只有设置了key为"abc"的才会投递到队列。
         * 		topic:对key进行模式匹配后进行投递的叫做Topic交换机，符号"#"匹配一个或多个词，符号"*"匹配正好一个词。例如"abc.#"匹配"abc.def.ghi"，"abc.*"只匹配"abc.def"。
         * 		fanout:还有一种不需要key的，叫做Fanout交换机，它采取广播模式，一个消息进来时，投递到与该交换机绑定的所有队列。  
         */
        channel.exchangeDeclare(EXCHANGE_NAME, "direct",true);//direct fanout topic  
        /**
         * 3、绑定队列至交换机，通过指定的路由作为桥梁
         */
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME, ROUTINGKEY); 
//        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME, "*.wms.queue"); 
        
        for (int i = 0; i < 100; i++) {
        	String message = "消息"+i;
            //发送消息到队列中
//          channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
//        	channel.basicPublish(EXCHANGE_NAME, ROUTINGKEY, null, message.getBytes("UTF-8"));
        	InterviewEntity entity = new InterviewEntity();
        	entity.setCode("code"+i);
        	entity.setPstate("7");
        	channel.basicPublish(EXCHANGE_NAME, ROUTINGKEY, null, JSONObject.toJSONString(entity).getBytes("UTF-8"));
            System.out.println("Producer Send +'" + JSONObject.toJSONString(entity) + "'");
		}
        //关闭通道和连接
        channel.close();
        connection.close();
        long timeEnd = System.currentTimeMillis();
        System.out.println("耗时："+(timeEnd-timeBegin)/1000);
    }
}
