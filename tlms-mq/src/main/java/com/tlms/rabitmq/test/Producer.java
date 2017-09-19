package com.tlms.rabitmq.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
	
/*	icbc_mq_apply.queue=icbc.mq.apply.tang
			icbc_mq_apply.exchange=icbc-mq-exchange.tang
			icbc_mq_apply.routingKey=icbc.mq.apply.tang*/
	
//	public final static String QUEUE_NAME="rabbitMQ.test";
//	public final static String QUEUE_NAME="icbc.mq.apply";
//	public final static String QUEUE_NAME="test_queue_key";
	public final static String QUEUE_NAME="icbc.mq.result.tang";
//	public final static String EXCHANGE_NAME="exchangeNametopic";
//	public final static String EXCHANGE_NAME="pujjr-topic-exchange";
//	public final static String EXCHANGE_NAME="test-mq-exchange";
//	public final static String ROUTINGKEY="icbc.mq.apply";
	public final static String EXCHANGE_NAME="icbc-mq-exchange.tang";
//	public final static String ROUTINGKEY="test_queue_key";
	public final static String ROUTINGKEY="icbc.mq.result.tang";
	
	
	
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost("39.108.166.189");
        factory.setPort(5672);
        factory.setUsername("icbc-mq");
        factory.setPassword("123456");
        factory.setVirtualHost("icbc-mq");
        //创建一个新的连接
        Connection connection = factory.newConnection();
        
        //创建一个通道
        Channel channel = connection.createChannel();
//          声明一个队列        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);//queueDeclare（名字，是否持久化，独占的queue， 不使用时是否自动删除，其他参数）；
//        channel.exchangeDeclare(EXCHANGE_NAME, "topic"); //direct fanout topic  
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTINGKEY);  
        
        for (int i = 0; i < 1; i++) {
        	String message = "消息"+i;
            //发送消息到队列中
        	
//            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
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
