package com.test.concurrent.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ArrayUtils;

public class BlockqueueTest {
	public void service2(){
		int corePoolSize = 2;
		int maximumPoolSize = 2;
		int keepAliveTime = 5000;
		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(2);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MINUTES, workQueue);
		BlockingQueue queue = new LinkedBlockingDeque(2);
		Producer p = new Producer(queue);
		Future<?> futures = executor.submit(p);
		try {
			Thread.currentThread().sleep(5000);
			System.out.println(Thread.currentThread().getName()+"关闭线程池");
			executor.shutdownNow();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 生产者消费者测试
	 */
	public void service1(){
		BlockingQueue queue = new LinkedBlockingDeque(5);
		Producer p = new Producer(queue);
		Consumer c1 = new Consumer(queue);
		
		ExecutorService service = Executors.newFixedThreadPool(2);
		service.execute(p);
		service.execute(c1);
		while(true){
			if(queue.contains(8)){
				p.hangUp();
				break;
			}
		}
		while(true){
			if(queue.isEmpty()){
				try {
					Thread.currentThread().sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				p.wakeup();
				break;
			}
		}
	}
	public static void main(String[] args) {
		BlockqueueTest b = new BlockqueueTest();
		b.service1();
//		b.service2();
	}
}
