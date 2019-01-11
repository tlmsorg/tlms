package com.tlms.rpc.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicProxy implements InvocationHandler{
	private Object target;
	public JdkDynamicProxy(Object target) {
		this.target = target;
	}
	
	public <T> T getProxy() {
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(proxy.getClass().getTypeName());
		System.out.println("代理对象执行1");
		Object obj = method.invoke(target, args);
		System.out.println("代理对象执行2");
		return obj;
	}
}
