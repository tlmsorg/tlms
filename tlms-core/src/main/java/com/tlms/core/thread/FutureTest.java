package com.tlms.core.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FutureTest {
	
	class MyThread implements Callable<String>{

		@Override
		public String call() throws Exception {
			int i = 0;
			while(i < 5) {
				System.out.println("执行中:"+i);
				i++;
				Thread.currentThread().sleep(1000);
			}
			return "线程执行结果";
		}
	}
	
	public void doTest() throws Exception {
		System.out.println("执行前");
		FutureTask<String> future = new FutureTask<String>(new MyThread());
		BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>();
		ExecutorService executorService = new ThreadPoolExecutor(5, 5, 1, TimeUnit.SECONDS, workQueue);
		executorService.submit(future);
		System.out.println("执行后");
		String threadResult = future.get();
		System.out.println("执行完成："+threadResult);
		executorService.shutdown();
	}
	public static void main(String[] args) throws Exception {
		new FutureTest().doTest();
		
	}
}
