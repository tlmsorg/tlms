package com.tlms.core.proxy.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

	
	static interface ISubject{
		String doSave1(String name);
		String doSave2(String name,String age);
	}
	
	static class SubjectImpl implements ISubject,Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String userName = "";
		
		@Override
		public String doSave1(String name) {
			String msg = "doSave1";
			System.out.println(msg+"|"+name);
			return msg;
		}

		@Override
		public String doSave2(String name, String age) {
			String msg = "doSave2";
			System.out.println(msg+"|"+name+"|"+age);
			return null;
		}
	}
	
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
			Object obj = method.invoke(targetObj, args);
			System.out.println("执行后");
			return obj;
		}
		
		public Object getProxy(Object targetObj) {
			this.targetObj = targetObj;
			Object proxyObj = Proxy.newProxyInstance(targetObj.getClass().getClassLoader(), targetObj.getClass().getInterfaces(), this);
			return proxyObj;
		}
		
	}
	
	/**
	 * 获取代理对象方法1
	 * @throws Exception
	 */
	public void method1() throws Exception {
		ISubject subjectImpl = new SubjectImpl();
//		ISubject subjectProxy = (ISubject) Proxy.newProxyInstance(subjectImpl.getClass().getClassLoader(), subjectImpl.getClass().getInterfaces(), new ProxyInvocationHandler(subjectImpl));
		ISubject subjectProxy = (ISubject) Proxy.newProxyInstance(ISubject.class.getClassLoader(), new Class[] {ISubject.class}, new ProxyInvocationHandler(subjectImpl));
//		ISubject subjectProxy = (ISubject) new ProxyInvocationHandler().getProxy(subjectImpl);
		subjectProxy.doSave1("brighttang");
//		subjectProxy.doSave2("brighttang","20");
	}
	
	/**
	 * 获取代理对象方法2
	 * @throws Exception
	 */
	public void method2() throws Exception {
		ISubject subjectImpl = new SubjectImpl();
		//1、获取代理类class
		Class proxyClass = Proxy.getProxyClass(ISubject.class.getClassLoader(), new Class[] {ISubject.class});
		//2、获取代理类contructor
		Constructor constructor = proxyClass.getConstructor(new Class[] {InvocationHandler.class});
		//3、获取代理对象
		ISubject subjectProxy = (ISubject) constructor.newInstance(new ProxyInvocationHandler(subjectImpl));
		subjectProxy.doSave1("method2");
	}
	
	public void serilizeObj() throws Exception {
		ISubject subjectImpl = new SubjectImpl();
		FileOutputStream fos = new FileOutputStream("d://objSerilizable.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(subjectImpl);
		oos.close();
		fos.close();
	}
	
	public void deSerilizeObj() throws Exception {
		
		FileInputStream fis = new FileInputStream("d://objSerilizable.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
//		ISubject subjectImpl = (ISubject) ois.readObject();
		Object obj = ois.readObject();
		System.out.println(obj.getClass().getName());
		fis.close();
		fis.close();
//		subjectImpl.doSave1("deSerilizeObj--->brignttang");
		ISubject subjectProxy = (ISubject) Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new ProxyInvocationHandler(obj));
		subjectProxy.doSave1("deSerilizeObj--->brignttang");
	}
	
	public static void main(String[] args) throws Exception {
		ProxyTest test = new ProxyTest();
		/**
		 * 先执行序列化，再执行反序列化
		 */
//		test.serilizeObj();
//		test.deSerilizeObj();
		new ProxyTest().method1();
//		new ProxyTest().method2();
	}

}
