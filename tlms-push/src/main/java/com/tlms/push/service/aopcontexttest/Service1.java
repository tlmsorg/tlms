package com.tlms.push.service.aopcontexttest;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//public class Service1{
	public class Service1 implements IService{
	private Logger logger = Logger.getLogger("AopContextController.class");
	@Autowired
	private IService service2;
	
	public void test() {
		IService currentProxy = (IService) AopContext.currentProxy();
		logger.debug("Service1->test currentProxy:"+currentProxy);
		logger.debug("Service1->test this:"+this);
		service2.test();
		// TODO Auto-generated method stub
	}

	@Override
	public void test(String str) {
		// TODO Auto-generated method stub
		
	}

}
