package com.tlms.core.threadlocaltest;

public class ThreadLocalMain {
	
	public void getThreadLocal() {
		ThreadLocal<UserInfo> threadLocalUserInfo = new ThreadLocal<UserInfo>();
		UserInfo userInfo = threadLocalUserInfo.get();
	}
	
	public static void main(String[] args) {
		ThreadLocalMain threadLocalMain = new ThreadLocalMain();
		/*
		ThreadLocal<Long> threadLocalLong = new ThreadLocal<Long>();
		ThreadLocal<String> threadLocalName = new ThreadLocal<String>();

		ThreadLocal<UserInfo> threadLocalUserInfo = new ThreadLocal<UserInfo>();
		threadLocalLong.set(Thread.currentThread().getId());
		threadLocalName.set(Thread.currentThread().getName());
		UserInfo userInfo = new UserInfo();
		userInfo.setAddress("线程："+Thread.currentThread().getId()+"地址：");
		userInfo.setName("线程："+Thread.currentThread().getId()+"名称");
		threadLocalUserInfo.set(userInfo);
		
		System.out.println(threadLocalLong.get());
		System.out.println(threadLocalName.get());

		System.out.println("线程外："+threadLocalUserInfo);
		new Thread(new UserThread1(threadLocalUserInfo)).start();
		new Thread(new UserThread1(threadLocalUserInfo)).start();
*/
		threadLocalMain.paramShare();
//		threadLocalMain.paramIsolation();
		
		
	}
	
	/**
	 * 线程数据共享
	 * 160068
	 * 2019年1月18日 下午2:51:28
	 */
	public void paramShare() {
		System.out.println("***************测试：线程数据共享********************");
		String currThreadName = Thread.currentThread().getName();
		VarPoolShare varPool = VarPoolShare.getInstanceNormal();
		varPool.put("name", "王分享");
		new Thread(new UserThreadShare()).start();
		for(int i = 0;i < 5;i++) {
			System.out.println(i+"线程"+currThreadName+"中取值："+varPool.get("name"));
			try {
				Thread.currentThread().sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 线程数据隔离
	 * 160068
	 * 2019年1月18日 下午2:55:36
	 */
	public void paramIsolation() {
		System.out.println("***************测试：线程数据隔离********************");
		String currThreadName = Thread.currentThread().getName();
		VarPoolIsolation varPool = VarPoolIsolation.getInstanceIsolation();
		varPool.put("name", "王隔离");
		System.out.println("线程"+currThreadName+"中赋值:"+varPool.get("name"));
		new Thread(new UserThreadIsolation()).start();
		for(int i = 0;i < 5;i++) {
			System.out.println(i+"线程"+currThreadName+"中取值："+varPool.get("name"));
			try {
				Thread.currentThread().sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
