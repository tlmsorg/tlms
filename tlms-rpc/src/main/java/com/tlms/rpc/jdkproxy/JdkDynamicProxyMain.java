package com.tlms.rpc.jdkproxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;

public class JdkDynamicProxyMain {

	public void test() throws Exception{
		/**
		 InvocationHandler handler = new MyInvocationHandler(...);
     	 Class proxyClass = Proxy.getProxyClass(
         Foo.class.getClassLoader(), new Class[] { Foo.class });
     	 Foo f = (Foo) proxyClass.
         getConstructor(new Class[] { InvocationHandler.class }).
         newInstance(new Object[] { handler });
		 */
		IStuService stuServiceImpl = new StuServiceImpl();
		JdkDynamicProxy handler = new JdkDynamicProxy(stuServiceImpl);
	
		Class<?> proxyClass = Proxy.getProxyClass(IStuService.class.getClassLoader(), new Class[] {IStuService.class});
		
//		Constructor<?> constructor = proxyClass.getConstructor(JdkDynamicProxy.class);
		Constructor<?> constructor = proxyClass.getConstructor(new Class[] {HWInvocationHandler.class});
		IStuService stuServiceProxy = (IStuService) constructor.newInstance(handler);
		stuServiceProxy.doSomeThing();
	}
	
	public static void main(String[] args) throws Exception {
		IStuService stuServiceImpl = new StuServiceImpl();
		Class<?>[] clazzArray = stuServiceImpl.getClass().getInterfaces();
		
		for (Class<?> clazz : clazzArray) {
			System.out.println(stuServiceImpl.getClass().getName()+"实现了接口："+clazz.getName());
		}
		
		IStuService stuServiceImplProxy = new JdkDynamicProxy(stuServiceImpl).getProxy();
		stuServiceImplProxy.doSomeThing();
		
		StuPojo stuPojo = new StuPojo();
		stuPojo.setName("brighttang");
		stuServiceImplProxy.saveStu(stuPojo);
		
		new JdkDynamicProxyMain().test();
		
	}

}
