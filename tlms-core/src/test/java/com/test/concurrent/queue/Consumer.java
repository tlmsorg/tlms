package com.test.concurrent.queue;

import java.util.concurrent.BlockingQueue;

/**
 * 消费者
 * @author tom
 *
 */
public	class Consumer implements Runnable {
	private final BlockingQueue queue;
	private boolean isWakeup = true;
	private boolean isStop = false;
	public Consumer(BlockingQueue queue){
		this.queue = queue;
	}
	public void run() {
		try {
			while(!isStop){
				System.out.println("isStop:"+isStop);
			if(this.isWakeup){
				consumer(queue.take());//获取队列queue首位对象，如果队列为空，当前线程挂起
				Object[] array = queue.toArray();
				if(array.length == 0){
					System.out.println(Thread.currentThread().getName()+"消费后队列为空");
				}
				StringBuilder sb = new StringBuilder();
				for (Object obj : array) {
					sb.append(obj+",");
				}
				System.out.println(Thread.currentThread().getName()+"消费后队列数据:"+sb.toString());
			}
			Thread.currentThread().sleep(10000);
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
}
public void consumer(Object obj){
	System.out.println(Thread.currentThread().getName()+"消费者消费："+obj);
}
public void wakeup(){
	System.out.println(Thread.currentThread().getName()+"唤醒消费者线程");
	this.isWakeup = true;
}
public void hangUp(){
	System.out.println(Thread.currentThread().getName()+"挂起消费者线程");
	this.isWakeup = false;
}
public void stop(){
	System.out.println(Thread.currentThread().getName()+"停止消费者线程");
	this.isStop = true;
}
}

