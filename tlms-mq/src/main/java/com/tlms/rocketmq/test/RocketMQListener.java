package com.tlms.rocketmq.test;

import java.util.List;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

public class RocketMQListener implements MessageListenerConcurrently{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> arg0, ConsumeConcurrentlyContext arg1) {
		for (MessageExt message : arg0) {  
            String msg = new String(message.getBody());  
            System.out.println("msg data from rocketMQ:" + msg);  
        }  
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; 
	}

}
