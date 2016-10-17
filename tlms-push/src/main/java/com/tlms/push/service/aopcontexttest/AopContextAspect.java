package com.tlms.push.service.aopcontexttest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

@Aspect 
public class AopContextAspect {
	private Logger logger = Logger.getLogger("AopContextController.class");
	@Before("execution(* com.tlms.push.service.aopcontexttest.Service1.test())")
	public void target() throws IllegalArgumentException, IllegalAccessException, ParseException{
		logger.debug("AopContextAspect->target 拦截");
		Service1 currentProxy = (Service1) AopContext.currentProxy();
		logger.debug("AopContextAspect->target:"+currentProxy);
//		
	}
	
	/*@Before("execution(* com.tlms.push.service.aopcontexttest.Service2.test(..)) && args(str1,str2)")
	public void target2(String str1,String str2) throws IllegalArgumentException, IllegalAccessException, ParseException{
		logger.debug("AopContextAspect->target2 拦截");
		IService currentProxy = (IService) AopContext.currentProxy();
		logger.debug("AopContextAspect->target2:"+currentProxy);
	}
	
	@Before("execution(* com.tlms.push.service.aopcontexttest.Service3.test(..)) && args(str)")
	public void target3(String str) throws IllegalArgumentException, IllegalAccessException, ParseException{
		logger.debug("AopContextAspect->target3 拦截");
		IService currentProxy = (IService) AopContext.currentProxy();
		logger.debug("AopContextAspect->target3:"+currentProxy);
	}*/
	
	
	@Before("execution(* com.tlms.push.service.aopcontexttest.Service2.test())")
	public void target2() throws IllegalArgumentException, IllegalAccessException, ParseException{
		logger.debug("AopContextAspect->target2 拦截");
		Service2 currentProxy = (Service2) AopContext.currentProxy();
		logger.debug("AopContextAspect->target2:"+currentProxy);
	}
	
	@Before("execution(* com.tlms.push.service.aopcontexttest.Service3.test())")
	public void target3() throws IllegalArgumentException, IllegalAccessException, ParseException{
		logger.debug("AopContextAspect->target3 拦截");
		Service3 currentProxy = (Service3) AopContext.currentProxy();
		logger.debug("AopContextAspect->target3:"+currentProxy);
	}
	
	@Before("execution(* com.tlms.push.service.aopcontexttest.Service4.test())")
	public void target4() throws IllegalArgumentException, IllegalAccessException, ParseException{
		logger.debug("AopContextAspect->target4 拦截");
		IService currentProxy = (IService) AopContext.currentProxy();
		logger.debug("AopContextAspect->target4:"+currentProxy);
	}
}
