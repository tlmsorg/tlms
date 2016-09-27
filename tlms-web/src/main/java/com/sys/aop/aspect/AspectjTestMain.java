package com.sys.aop.aspect;

import org.springframework.aop.aspectj.annotation.AspectJAdvisorFactory;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import com.sys.aop.Waiter;
import com.sys.aop.impl.WaiterImpl;

public class AspectjTestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Waiter waiter = new WaiterImpl();
		AspectJProxyFactory factory = new AspectJProxyFactory();
		factory.setTarget(waiter);
		factory.addAspect(PreGreetingAspect.class);
		Waiter proxy = factory.getProxy();
		proxy.greetTo("ttt");
	}

}
