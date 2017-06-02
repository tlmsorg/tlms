package com.test.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.ibatis.executor.ExecutorException;

public class ConcurrentTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("线程1执行中");
				
			}
		});
	
//		executorService.shutdown();
		System.out.println("父线程执行中："+Thread.currentThread().getName());
		Future<?> future2 = executorService.submit(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("线程2执行中："+Thread.currentThread().getName());
					Thread.currentThread().sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Future<?> future3 = executorService.submit(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("线程3执行中："+Thread.currentThread().getName());
					Thread.currentThread().sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Future<?> future4 = executorService.submit(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				try {
					System.out.println("线程4执行中："+Thread.currentThread().getName());
					Thread.currentThread().sleep(15000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return "线程4返回";
			}
		});
		executorService.shutdown();
		try {
			System.out.println("future2.get():"+future2.get());
			System.out.println("future3.get():"+future3.get());
			System.out.println("future4.get():"+future4.get());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
