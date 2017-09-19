package com.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sys.domain.SysUser;
import com.tlms.rabitmq.test.MQProducer;
@RestController
@ResponseBody
public class RabbitMqController {
	@Autowired
	private MQProducer mqProducerImpl;
	@RequestMapping(value="/producer",method=RequestMethod.POST)
	public String producer() throws Exception{
		for (int i = 0; i < 10; i++) {
			Map<String,Object> msg = new HashMap();
	        msg.put("data","hello,rabbmitmq!");
	        String message = "消息"+i;
	        mqProducerImpl.sendDataToQueue("test_queue_key1.test",message);
		}
		
		return "producer";
	}
}
