package com.tlms.core.aba;

import java.util.concurrent.atomic.AtomicInteger;

public class AbaTest {
	
	public static AtomicInteger a = new AtomicInteger(1);
	
	/**
	 * 说明：线程2对a执行加1和减1后，在线程1中执行cas操作，操作结果仍然成功，出现aba问题。
	 */
	public static void main(String[] args) {
		Runnable thread1 = new Runnable() {
			@Override
			public void run() {
				System.out.println("操作线程（主线程)" + Thread.currentThread() +",值 = " + a); 
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				boolean isCasSuccess = a.compareAndSet(1, 2);
				System.out.println("操作线程（主线程)" + Thread.currentThread() +",CAS操作结果："+isCasSuccess+"，值 = " + a);
				
				
			}
			
		};
		
		Runnable thread2 = new Runnable() {
			@Override
			public void run() {
//				Thread.yield();
				try {
					Thread.sleep(100);
					a.incrementAndGet();
					System.out.println("操作线程（干扰线程)" + Thread.currentThread() +",increment,值 = " + a); 
					a.decrementAndGet();
					System.out.println("操作线程（干扰线程)" + Thread.currentThread() +",decrement,值 = " + a); 
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		};
		
		new Thread(thread1).start();
		new Thread(thread2).start();
		
	}

}
