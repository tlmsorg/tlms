package com.tlms.rpc.jdkproxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JdkDynamicProxyMain {

	public void jdkDynamicProxy2() throws Exception{
		/**
		 InvocationHandler handler = new MyInvocationHandler(...);
     	 Class proxyClass = Proxy.getProxyClass(
         Foo.class.getClassLoader(), new Class[] { Foo.class });
     	 Foo f = (Foo) proxyClass.
         getConstructor(new Class[] { InvocationHandler.class }).
         newInstance(new Object[] { handler });
		 */
		//1、目标对象
		IStuService stuService = new StuServiceImpl();
		//2.1代理class
		Class<?> proxyClass = Proxy.getProxyClass(IStuService.class.getClassLoader(), new Class[] {IStuService.class});
		//2.2代理class构造方法
		Constructor<?> constructor = proxyClass.getConstructor(new Class[] {InvocationHandler.class});
		//2.3代理对象
		//目标对象调用处理程序
		InvocationHandler handler = new MyInvocationHandler(stuService);
		IStuService stuServiceProxy = (IStuService) constructor.newInstance(handler);
		//3、代理对象执行方法
		stuServiceProxy.doSomeThing();
	}
	
	public void jdkDynamicProxy1() throws Exception{
		//1、目标对象
		IStuService stuService = new StuServiceImpl();
/*		Class<?>[] clazzArray = stuServiceImpl.getClass().getInterfaces();
		for (Class<?> clazz : clazzArray) {
			System.out.println(stuServiceImpl.getClass().getName()+"实现了接口："+clazz.getName());
		}*/
		//2、代理对象
		IStuService stuServiceImplProxy = new JdkDynamicProxy(stuService).getProxy();
		//3、代理对象执行方法
		stuServiceImplProxy.doSomeThing();
		/*
		StuPojo stuPojo = new StuPojo();
		stuPojo.setName("brighttang");
		stuServiceImplProxy.saveStu(stuPojo);*/
	}
	
	public static void main(String[] args) throws Exception {
//		new JdkDynamicProxyMain().jdkDynamicProxy1();
		new JdkDynamicProxyMain().jdkDynamicProxy2();
		
	}

}
