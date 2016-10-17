package com.tlms.push.service.aopcontexttest;

import org.apache.log4j.Logger;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//public class Service3{
	public class Service3 implements IService{
	private Logger logger = Logger.getLogger("AopContextController.class");
	@Autowired
	private Service4 service4;

	public void test(String str) {
		IService currentProxy = (IService) AopContext.currentProxy();
		logger.debug("Service3->test currentProxy:"+currentProxy);
		logger.debug("Service3->test this:"+this);
		// TODO Auto-generated method stub
		service4.test();
	}

	public void test() {
		// TODO Auto-generated method stub
		IService currentProxy = (IService) AopContext.currentProxy();
		logger.debug("Service3->test currentProxy:"+currentProxy);
		logger.debug("Service3->test this:"+this);
		// TODO Auto-generated method stub
		service4.test();
	}

}
