package com.test.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

public class Consumer implements Callable<Object> {
	private final BlockingQueue<String> queue;
	public Consumer(BlockingQueue<String> queue){
		this.queue = queue;
	}
	@Override
	public Object call() throws Exception {
		Thread.currentThread().sleep(3000);
		System.out.println("消费者嗷嗷待哺中.....");
		System.out.println("消费者消费数据："+queue.take()+"，线程池共享队列长度："+queue.size());
		System.out.println("消费者吃饱走人");
		return "消费者执行完成";
	}

}
