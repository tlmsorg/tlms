package com.test.concurrent.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

import org.apache.commons.dbcp.datasources.SharedPoolDataSource;

import com.alibaba.fastjson.parser.Feature;
/**
 * jdk 8 采用completablefuture
 * @author tom
 *
 */
public class CompletableFutureTest {

	public void runanbleTest(ExecutorService executor){
		final String name = "brighttang";
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
			
			@Override
			public void run() {//采用runnable 线程执行完成，返回null
				int i = 0;
				while(i++ < 5){
					try {
						//模拟业务逻辑处理
						Thread.currentThread().sleep(1000);
						System.out.println(Thread.currentThread().getName()+":"+i+"|"+name);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, executor);
		try {
			System.out.println("线程执行完成，返回："+future.get());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("关闭线程池");
		executor.shutdown();
	}
	
	public void supplierTest(ExecutorService executor,List paramList){
		final String name = "brignttang";
		List<CompletableFuture<List<ThreadResponseVo>>> futureList = new ArrayList<CompletableFuture<List<ThreadResponseVo>>>();
		for (final Object object : paramList) {
			CompletableFuture<List<ThreadResponseVo>> future = CompletableFuture.supplyAsync(new Supplier<List<ThreadResponseVo>>() {
				@Override
				public List<ThreadResponseVo> get() {//类似于callable的返回结果
					System.out.println(Thread.currentThread().getName());
					System.out.println(object);
					int i = 0;
					while(i++ < 5){
						try {
							//模拟业务逻辑处理
							Thread.currentThread().sleep(1000);
							System.out.println(Thread.currentThread().getName()+":"+i);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					List<ThreadResponseVo> trvList = new ArrayList<ThreadResponseVo>();
					ThreadResponseVo trv = new ThreadResponseVo();
					trv.setAge(10);
					trv.setLoopTime(10);
					trv.setName(name);
					trv.setSex("男");
					trvList.add(trv);
					return trvList;
				}
			}, executor);
			futureList.add(future);
		}

		
		try {
			for (CompletableFuture<List<ThreadResponseVo>> future : futureList) {
				List<ThreadResponseVo> trvList = future.get();
				for (ThreadResponseVo trv : trvList) {
					System.out.println("线程执行完成 future.get():"+trv.getName()+"|"+trv.getSex()+"|"+trv.getAge()+"|"+trv.getLoopTime()+"|");
				}
			}
			executor.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		CompletableFutureTest cft = new CompletableFutureTest();
		List<String> list = new ArrayList<String>();
		list.add("参数");
		cft.supplierTest(executor,list);
//		cft.runanbleTest(executor);
	}

}
