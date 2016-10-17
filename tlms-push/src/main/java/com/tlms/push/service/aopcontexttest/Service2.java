package com.tlms.push.service.aopcontexttest;

import org.apache.log4j.Logger;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//public class Service2{
	public class Service2 implements IService{
	private Logger logger = Logger.getLogger("AopContextController.class");
	@Autowired
	private IService service3;

	public void test(String str1,String str2) {
		IService currentProxy = (IService) AopContext.currentProxy();
		logger.debug("Service2->test currentProxy:"+currentProxy);
		logger.debug("Service2->test this:"+this);
		// TODO Auto-generated method stub
		service3.test("ttt");
		
	}

	public void test() {
		// TODO Auto-generated method stub
		IService currentProxy = (IService) AopContext.currentProxy();
		logger.debug("Service2->test currentProxy:"+currentProxy);
		logger.debug("Service2->test this:"+this);
		// TODO Auto-generated method stub
		service3.test();
	}

	@Override
	public void test(String str) {
		// TODO Auto-generated method stub
		
	}

}
