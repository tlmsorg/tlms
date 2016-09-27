package com.sys.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.sys.aop.UserBean;

public class PerformanceHandler implements InvocationHandler{	
	private Object target;
	public PerformanceHandler(Object target){
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("111111111111111:"+method.getName()+","+args.length);
		method.invoke(target, args);
		
		System.out.println("222222222222222");
		return null;
	}

}
