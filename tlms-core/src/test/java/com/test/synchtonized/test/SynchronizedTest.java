package com.test.synchtonized.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SynchronizedTest {
	static final List<String> connectPool = new ArrayList<String>();
	public static void main(String[] args) {
		
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0;i < 10;i ++){
			list.add(i+"");
		}
		/*for (String str : list) {
			list.remove(str);
			System.out.println(list.size());
		}*/
		
		/*for (int i = 0; i < list.size(); i++) {
			if("5".equals(list.get(i)))	
				list.remove(list.get(i));
			System.out.println(i+"|"+list.size());
			System.out.println();
		}*/
		
		int corePoolSize = 3;
		int maximumPoolSize = 3;
		long keepAliveTime = 1000;
		LinkedBlockingDeque<Runnable>  workQueue = new LinkedBlockingDeque<Runnable>();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.DAYS, workQueue);

		Future<String> future1 = executor.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				
				System.out.println("线程1执行中");
				int i =0;
				while(true){
					i++;
					List<String> sessionList = PushUtil.getSessionList();
					sessionList.add(i+"");
					System.out.println("生产线程："+sessionList.size());
					for (int j = 0; j < sessionList.size(); j++) {
						System.out.println(j+"|"+sessionList.get(j));
					}
					Thread.currentThread().sleep(1000);
				}
			}
		});
		
		Future<String> future2 = executor.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				
				System.out.println("线程2执行中");
				int i =0;
				while(true){
					List<String> sessionList = PushUtil.getSessionList();
					for (int j = 0; j < sessionList.size(); j++) {
						String temp = sessionList.get(j);
						if(j%2 == 0){
							SynchronizedTest.connectPool.remove(temp);
						}
					}
					System.out.println("消费线程："+sessionList.size());
					for (int j = 0; j < sessionList.size(); j++) {
						String temp = sessionList.get(j);
					}
//					Thread.currentThread().sleep(1000);
				}
			}
		});
	}

}
