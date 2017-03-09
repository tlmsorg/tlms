package com.sys.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class SecurityAspect {
	private static final Logger logger = Logger.getLogger(SecurityAspect.class);
	
	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object execute(ProceedingJoinPoint pjp) throws Throwable{
		logger.info("execute");
		Object obj = pjp.proceed();
		return obj;
	}
/*	
	@Before("execution(* com.sys.controller.LoginController.userLogin(..)) && args(user)")
	public void test(User user){
		System.out.println("user:");
	}*/
/*	@Before("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void execute() throws Throwable{
		logger.info("execute");
	}*/
}
