package com.tlms.rabitmq.test;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class QueueListenter implements MessageListener{

	@Override
	public void onMessage(Message msg) {
		try{
           
            String message = new String(msg.getBody(), "UTF-8");
            System.out.println("Message:"+msg.toString());
            System.out.println("收到消息:"+message);
        }catch(Exception e){
            e.printStackTrace();
        }
	}

}
