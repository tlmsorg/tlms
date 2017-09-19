package com.test.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 简单生产者、消费者测试
 * 生产者、消费者线程均执行一次，并返回结果。
 * 2017-08-08
 * @author tom
 */
public class ThreadPoolExecutorTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		int corePoolSize = 2;
		int maximumPoolSize = 2;
		long keepAliveTime = 40000;
		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MINUTES, workQueue);
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		Future<Object> future1 = executor.submit(new Producer(queue));
		Future<Object> future2 = executor.submit(new Consumer(queue));
		Future<Object> future3 = executor.submit(new Consumer(queue));
		
		System.out.println("当前线程池中活动线程数："+executor.getPoolSize());
		if("生产者执行完成".equals(future1.get())){
			System.out.println("生产者执行完成，回调");
		}
		if("消费者执行完成".equals(future2.get())){
			System.out.println("消费者1执行完成，回调");
		}
		if("消费者执行完成".equals(future3.get())){
			System.out.println("消费者2执行完成，回调");
		}
		Thread.currentThread().sleep(1000);
		System.out.println("当前线程池中活动线程数："+executor.getPoolSize());
		executor.shutdown();
	}

}
