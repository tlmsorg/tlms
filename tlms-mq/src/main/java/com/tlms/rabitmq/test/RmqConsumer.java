package com.tlms.rabitmq.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class RmqConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(RmqConsumer.class);

	public void rmqConsumeMessage(Object obj) {
		System.out.println("消费");
		LOGGER.info("rmq 消费者任务:{}", JSONObject.toJSONString(obj));
		// TODO 具体的消费策略
	}
}
