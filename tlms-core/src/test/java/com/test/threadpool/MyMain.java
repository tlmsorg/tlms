package com.test.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyMain {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService cachedThreadPoll = Executors.newCachedThreadPool();
//		ExecutorService fixedThreadPoll = Executors.newFixedThreadPool(2);
		LinkedBlockingDeque<Runnable> workQuene = new LinkedBlockingDeque<Runnable>();
		ExecutorService fixedThreadPoll = new ThreadPoolExecutor(2, 2, 10, TimeUnit.SECONDS, workQuene);
		for(int i = 1;i < 100; i++){
			final int index = i;
			/*try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//缓冲线程池
			/*CachedThread thread = new CachedThread(index);
			cachedThreadPoll.execute(thread);*/
//			定长线程池
			FixedThread fixedThread = new FixedThread(index);
			fixedThreadPoll.execute(fixedThread);
			System.out.println("******************"+i+"|"+fixedThread);
		}
	}
}
