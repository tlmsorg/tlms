package com.tlms.core.aba;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @company 潽金金融
 * @project_name tlms-core
 * @ClassName AbaTest2 解决多线程下的aba问题
 * @Description 网帖地址备份：https://docs.qq.com/doc/DY2xzQXJQYURLVHlH
 * @author 160068
 * @date 2019年9月6日 下午1:56:19
 * @version V1.0
 */
public class AbaTest2 {
	
	public static AtomicStampedReference<Integer> a = new AtomicStampedReference<Integer>(1,1);
	/**
	 * 说明：线程2对a执行加1和减1后，在线程1中执行cas操作，操作结果仍然成功，出现aba问题。
	 */
	public static void main(String[] args) {
		Runnable thread1 = new Runnable() {
			@Override
			public void run() {
				System.out.println(System.currentTimeMillis() +"操作线程（主线程）)" + Thread.currentThread() +",更新前，值 = " + a.getReference()+"|stamp:"+a.getStamp()); 
				Integer stamp = a.getStamp();
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				boolean isCasSuccess = a.compareAndSet(1, 2, stamp, stamp + 1);
				System.out.println(System.currentTimeMillis() +"操作线程（主线程）)" + Thread.currentThread() +",更新后，值 = " + a.getReference()+"|stamp:"+a.getStamp()+"|isCasSuccess："+isCasSuccess); 
			}
			
		};
		
		Runnable thread2 = new Runnable() {
			@Override
			public void run() {
//				Thread.yield();
				Integer stamp = a.getStamp();
				try {
					Thread.sleep(100);
					boolean isCasSucess = a.compareAndSet(1, 2, a.getStamp(), a.getStamp() + 1);
					System.out.println(System.currentTimeMillis() +"操作线程（干扰线程）)" + Thread.currentThread() +",increment,值 = " + a.getReference()+"|stamp:"+a.getStamp()+"|isCasSucess:"+isCasSucess); 
					isCasSucess = a.compareAndSet(2, 1, a.getStamp(), a.getStamp() + 1);
					System.out.println(System.currentTimeMillis() +"操作线程（干扰线程）)" + Thread.currentThread() +",decrement,值 = " + a.getReference()+"|stamp:"+a.getStamp()+"|isCasSucess:"+isCasSucess); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		};
		
		new Thread(thread1).start();
		new Thread(thread2).start();
		
	}

}
