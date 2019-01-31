package com.tlms.core.threadlocaltest;

public class UserThread1 implements Runnable{
	private ThreadLocal<UserInfo> threadLocal = null;
	public UserThread1(ThreadLocal<UserInfo> threadLocal) {
		this.threadLocal = threadLocal;
	}

	@Override
	public void run() {
		System.out.println("线程内："+this.threadLocal.get());
		
	}

}
