package com.test.concurrent.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 线程执行完回调
 * @author tom
 *
 */
public class ConsumerCallable implements Callable<ThreadResponseVo> {
	private BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	int loopTime = 0;
	public ConsumerCallable(BlockingQueue<String> queue,int loopTime){
		this.queue = queue;
		this.loopTime = loopTime;
	}

	@Override
	public ThreadResponseVo call() throws Exception {
		System.out.println("模拟第三方处理,5秒.....");
		int i = 0;
		while(i++ < loopTime){
			System.out.println(Thread.currentThread().getId()+"当前耗时："+i);
			Thread.currentThread().sleep(1000);
		}
		
		String head = queue.take();//Retrieves and removes the head of this queue, waiting if necessary until an element becomes available.
		ThreadResponseVo trv = new ThreadResponseVo();
		trv.setName(head);
		trv.setAge(40);
		trv.setSex("男");
		trv.setLoopTime(loopTime);
		System.out.println(Thread.currentThread().getId()+"线程返回");
		return trv;
	}

}
