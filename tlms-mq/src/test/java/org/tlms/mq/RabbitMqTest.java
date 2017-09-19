package org.tlms.mq;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tlms.rabitmq.test.MQProducer;
import com.tlms.rabitmq.test.MQProducerImpl;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring-rabbimq.xml,classpath*:/spring-mybatis.xml,classpath*:/applicationContext.xml"})
public class RabbitMqTest {
	 @Resource
	 MQProducerImpl mqProducer;
	 final String queue_key = "test_queue_key";
	@Test
	public void test() {
		System.out.println("11111111");
		Map<String,Object> msg = new HashMap();
        msg.put("data","hello,rabbmitmq!");
        mqProducer.sendDataToQueue("icbc.mq.apply",msg);
		fail("Not yet implemented");
	}

}
