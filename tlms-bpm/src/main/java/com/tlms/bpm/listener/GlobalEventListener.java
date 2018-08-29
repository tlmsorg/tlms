package com.tlms.bpm.listener;

import java.util.Map;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

public class GlobalEventListener implements ActivitiEventListener{
	private static final Logger logger = Logger.getLogger(GlobalEventListener.class);
	private Map<String,Object> handlers;
	private boolean isFail = false;
	@Override
	public void onEvent(ActivitiEvent event) {
		logger.info(event);
		logger.info("捕获事件："+event.getType().name() + "|"+ToStringBuilder.reflectionToString(event));
		String eventType = event.getType().name();
		/*isFail = true;
		throw new RuntimeException("捕获事件，抛出异常");*/
	}

	@Override
	public boolean isFailOnException() {
		// TODO Auto-generated method stub
		return isFail;
	}

	public Map<String, Object> getHandlers() {
		return handlers;
	}

	public void setHandlers(Map<String, Object> handlers) {
		this.handlers = handlers;
	}

}
