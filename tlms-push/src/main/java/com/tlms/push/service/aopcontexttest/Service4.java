package com.tlms.push.service.aopcontexttest;

import org.apache.log4j.Logger;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

@Service
//public class Service4{
	public class Service4 implements IService{
	private Logger logger = Logger.getLogger("AopContextController.class");

	public void test() {
		IService currentProxy = (IService) AopContext.currentProxy();
		logger.debug("Service4->test currentProxy:"+currentProxy);
		logger.debug("Service4->test this:"+this);
		// TODO Auto-generated method stub
	}

	@Override
	public void test(String str) {
		// TODO Auto-generated method stub
		
	}

}
