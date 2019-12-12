package com.tlms.core.proxy;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class ContructorTest implements Serializable{

	interface UserLogin{
		public String login(String userName,String password) throws Exception;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8559995801667986892L;
	
	static class ProxyInvocationHandler implements InvocationHandler{
		private Object targetObj;
		
		public ProxyInvocationHandler() {
		}
		
		public ProxyInvocationHandler(Object targetObj) {
			this.targetObj = targetObj;
		}
		 
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("执行前,ProxyInvocationHandler--->invoke");
			for (Object object : args) {
				System.out.println("参数:"+object);
			}
			Object obj = method.invoke(targetObj, args);
			System.out.println("执行后:"+obj);
			return obj;
		}
		
		public Object getProxy(Object targetObj) {
			this.targetObj = targetObj;
			Object proxyObj = Proxy.newProxyInstance(targetObj.getClass().getClassLoader(), targetObj.getClass().getInterfaces(), this);
			return proxyObj;
		}
		
	}
	
	public void login(String userName,String password) {
		
	}

	public static void main(String[] args) throws Exception {
		//目标实例对象
		UserLogin userLogin = new UserLogin() {
			@Override
			public String login(String userName, String password) throws Exception {
				return "方法返回："+userName+"**********"+password;
			}
		};
		//获取proxy class
		Class clazz = Proxy.getProxyClass(UserLogin.class.getClassLoader(), new Class[] {UserLogin.class});
		//获取proxy contructor
		Constructor contructor = clazz.getConstructor(new Class[] {InvocationHandler.class});
		//获取proxy object
		UserLogin userLoginProxy = (UserLogin) contructor.newInstance(new ProxyInvocationHandler(userLogin));
		String result = userLoginProxy.login("160010", "111111");
		
		
		System.out.println("最终结果："+result);
	}

}
