package com.tlms.rabitmq.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Customer {
	public final static String virtualHost = "test";
	public final static String EXCHANGE_NAME="pujjr.wms.exchange";//pujjr.wms.exchange、amq.topic
	public final static String ROUTINGKEY="pujjr.wms.queue";
	public final static String QUEUE_NAME="pujjr.wms.queue";
	
	public final static String host = "172.18.10.131";
	public final static int port = 5672;
	public final static String userName = "pjadmin";
	public final static String passWord = "Purking0326";
	//接收条数
	public static long  receivedCnt = 0;
    //最大中奖条数
	public static long   maxReceivedCnt = 50;
	
    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ地址
//        factory.setHost("localhost");
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(userName);
        factory.setPassword(passWord);
        factory.setVirtualHost(virtualHost);
//        factory.setVirtualHost("icbc-mq");
        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个通道
        final Channel channel = connection.createChannel();
        
//        channel.queueDelete("rabbitMQ.test");
        
        /**
         * 声明一个队列        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
         * autoDelete
         * 		true:consumer线程停止后，自动删除队列
         * 		false:队列一经创建，不再自动删除
         */
//        channel.queueDeclare(QUEUE_NAME, true, false, false, null);//queueDeclare（名字，是否持久化，独占的queue， 不使用时是否自动删除，其他参数）；
//        channel.exchangeDeclare(EXCHANGE_NAME, "topic"); //direct fanout topic
//        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "test");  
//        channel.queueDeclare(QUEUE_NAME, true, false, false, null);//queueDeclare（名字，是否持久化，独占的queue， 不使用时是否自动删除，其他参数）；
       
        System.out.println("Customer Waiting Received messages");
       
        //DefaultConsumer类实现了Consumer接口，通过传入一个频道，
        // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
            	System.out.println("*******************消息接收开始**************************");
                String message = new String(body, "UTF-8");
                System.out.println("Customer Received ：" + message );
                System.out.println("consumerTag:"+consumerTag);
                System.out.println(envelope.getDeliveryTag());
                System.out.println(envelope.getExchange());
                System.out.println(envelope.getRoutingKey());
                System.out.println(properties);
               /* int i = 0;
                while(i < 5){
                	System.out.println("i:"+i);
                	 try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                	 i++;
                }*/
//                	channel.basicRecover();
				/* if("消息5".equals(message)){
					System.out.println("消费成功---》"+message);
					channel.basicAck(envelope.getDeliveryTag(), false);
				}else{
					channel.txRollback();
				}*/
                
                System.out.println();
                channel.basicAck(envelope.getDeliveryTag(), false);
                if(Customer.receivedCnt < Customer.maxReceivedCnt) {
                	Customer.receivedCnt ++;
                	System.out.println("中奖："+Customer.receivedCnt);
                }else {
                	Customer.receivedCnt ++;
                	System.out.println("未中奖："+Customer.receivedCnt);
                }
                System.out.println("*******************消息接收**************************");
            }
        };
        /*
         * 自动回复队列应答 -- RabbitMQ中的消息确认机制
         * autoAck
         * 		false：在接收消息后，不会自动确认消息，需要显示对消息进行确认
         * 		true：在接收消息后，自动确认对消息进行确认
         */
        channel.basicConsume(QUEUE_NAME, false, consumer);
        System.out.println("执行完成");
       /*
        GetResponse response = channel.basicGet(QUEUE_NAME, false);
        System.out.println(response);
//        channel.basicAck(response.getEnvelope().getDeliveryTag(), false);
        System.out.println("22222");*/
    }
}
