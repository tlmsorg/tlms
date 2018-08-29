package com.tlms.rabitmq.test;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.BasicProperties.Builder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ReturnListener;
import com.tlms.mq.domain.SysPushMessage;

/**
 * 潽金站内消息推送测试
 * @author tom
 *
 */
public class PjMessageProducer {
	public final static String QUEUE_NAME="pujjr.wms.queue";
	public final static String EXCHANGE_NAME="amq.topic";//pujjr.wms.exchange
	public final static String ROUTINGKEY="pujjr.wms.routing";
	/*public final static String QUEUE_NAME="tangqueue";
	public final static String EXCHANGE_NAME="test.exchange";
	public final static String ROUTINGKEY="tangroute";*/
	public final static String host = "172.18.10.131";
	public final static int port = 5672;
	public final static String userName = "pjadmin";
	public final static String passWord = "Purking0326";
	public final static String virtualHost = "test";
	
	
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
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
        //channel.exchangeDeclare(EXCHANGE_NAME, "topic"); //direct fanout topic  
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTINGKEY);  
        
        channel.confirmSelect();
    	channel.addReturnListener(new ReturnListener() {
			@Override
			public void handleReturn(int replyCode,String replyText,String exchange,String routingKey,AMQP.BasicProperties properties,byte[] body) throws IOException {
				System.out.println("返回:"+replyCode+"|"+replyText+"|"+exchange+"|"+routingKey);
				String message = new String(body,"UTF-8");
				System.out.println("由于routingkey错误，消息发送失败，消息体："+message);
			}
		});
    	channel.addConfirmListener(new ConfirmListener() {
			@Override
			public void handleNack(long deliveryTag, boolean multiple) throws IOException {
				System.out.println("handleNack->deliveryTag:"+deliveryTag+",multiple:"+multiple);
			}
			
			@Override
			public void handleAck(long deliveryTag, boolean multiple) throws IOException {
				System.out.println("handleAck->deliveryTag:"+deliveryTag+",multiple:"+multiple);
			}
		});
        
        for (int i = 0; i < 1; i++) {
        	String message = "消息"+i;
            //发送消息到队列中
        	SysPushMessage sysPushMessage = new SysPushMessage();
        	sysPushMessage.setAccountId("admin");
        	sysPushMessage.setMsg("消息"+(i+1));
        	sysPushMessage.setMsgLevel("00");
        	sysPushMessage.setTranCode("1005");
        	sysPushMessage.setIsMultiple(true);
        	Calendar cl = Calendar.getInstance();
        	cl.add(Calendar.MINUTE, 1);
//        	sysPushMessage.setClockTime(cl.getTime());
        	System.out.println(JSONObject.toJSONString(sysPushMessage).getBytes("UTF-8"));
//        	channel.basicPublish(EXCHANGE_NAME, ROUTINGKEY, null, JSONObject.toJSONString(sysPushMessage).getBytes("UTF-8"));
        	
//        	channel.basicPublish(EXCHANGE_NAME, "test1111111111111", null, JSONObject.toJSONString(sysPushMessage).getBytes("UTF-8"));
			Map<String, Object> headers=new HashMap<String, Object>();  
			headers.put("key", "123456");  
			headers.put("token", "654321");  
 
        	Builder builder = new BasicProperties().builder();
        	builder.headers(headers);
        	builder.deliveryMode(2);
        	
//        	channel.basicPublish(EXCHANGE_NAME+"1111", "test1111111111111", true, builder.build(), JSONObject.toJSONString(sysPushMessage).getBytes("UTF-8"));
   
        	/**
        	 * routingkey错误，回调ReturnListener
        	 */
        	channel.basicPublish(EXCHANGE_NAME,  ROUTINGKEY, true, false, builder.build(), JSONObject.toJSONString(sysPushMessage).getBytes("UTF-8"));

        	try {
        		if(channel.waitForConfirms()) {
            		System.out.println("发送交换机成功");
            	}else {
            		System.out.println("发送交换机失败");
            	}
                System.out.println("Producer Send +'" + JSONObject.toJSONString(sysPushMessage) + "'");
			} catch (Exception e) {
				System.out.println("交换机异常，无法连接到交换机:"+EXCHANGE_NAME);
			}
        	
		}
        try {
        	//关闭通道和连接
            channel.close();
            connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
        
    }
}
