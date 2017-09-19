package com.test.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

public class Producer implements Callable<Object> {
	private final BlockingQueue<String> queue;
	public Producer(BlockingQueue<String> queue){
		this.queue = queue;
	}
	@Override
	public Object call() throws Exception {
		this.queue.put("【1】");
		this.queue.put("【2】");
		System.out.println("生产者生产数据，【1】,【2】,线程池共享队列长度："+queue.size());
		Thread.currentThread().sleep(5000);
		return "生产者执行完成";
	}

}
