package com.tlms.rocketmq.test;

import java.util.UUID;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;

public class RocketMQConsumer {
	private DefaultMQPushConsumer consumer;  
	  
    private MessageListener listener;  
  
    protected String nameServer;  
  
    protected String groupName;  
  
    protected String topics;  
  
    public RocketMQConsumer(MessageListener listener, String nameServer, String groupName, String topics) {  
        this.listener = listener;  
        this.nameServer = nameServer;  
        this.groupName = groupName;  
        this.topics = topics;  
    }  
  
    public void init() {  
        consumer = new DefaultMQPushConsumer(groupName);  
        consumer.setNamesrvAddr(nameServer);  
        try {  
            consumer.subscribe(topics, "*");  
        } catch (MQClientException e) {  
            e.printStackTrace();  
        }  
        consumer.setInstanceName(UUID.randomUUID().toString());  
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);  
        consumer.registerMessageListener((MessageListenerConcurrently) this.listener);  
  
        try {  
            consumer.start();  
        } catch (MQClientException e) {  
            e.printStackTrace();  
        }  
        System.out.println("RocketMQConsumer Started! group=" + consumer.getConsumerGroup() + " instance=" + consumer.getInstanceName()  
        );  
    }  
}
