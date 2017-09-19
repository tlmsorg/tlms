package com.tlms.rocketmq.test;

import java.util.UUID;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

public class RocketMQProducer {

	private DefaultMQProducer sender;  
	  
    protected String nameServer;  
  
    protected String groupName;  
  
    protected String topics;  
  
    public void init() {  
        sender = new DefaultMQProducer(groupName);  
        sender.setNamesrvAddr(nameServer);  
        sender.setInstanceName(UUID.randomUUID().toString());  
        try {  
            sender.start();  
        } catch (MQClientException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public RocketMQProducer(String nameServer, String groupName, String topics) {  
        this.nameServer = nameServer;  
        this.groupName = groupName;  
        this.topics = topics;  
    }  
  
    public void send(Message message) {  
  
        message.setTopic(topics);  
  
        try {  
            SendResult result = sender.send(message);  
            SendStatus status = result.getSendStatus();  
            System.out.println("messageId=" + result.getMsgId() + ", status=" + status);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  

}
