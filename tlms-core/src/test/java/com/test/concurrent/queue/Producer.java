package com.test.concurrent.queue;

import java.util.concurrent.BlockingQueue;
/**
 * 生产者
 * @author tom
 *
 */
public class Producer implements Runnable{
	private final BlockingQueue queue;
	int i = 0;
	private boolean isWakeup = true;
	private boolean isStop = false;
	public Producer(BlockingQueue queue){
		this.queue = queue;
	}
	public void run() {
		try {
			while(!isStop){
				System.out.println("生产者状态监控"+this.isWakeup);
				if(isWakeup){
					queue.put(produce());//对象存入队列queue末尾，如果队列已满，当前线程挂起，等待当前队列有空间时，当前线程被唤醒。
					
					Object[] array = queue.toArray();
					StringBuilder sb = new StringBuilder();
					for (Object obj : array) {
						sb.append(obj+",");
					}
					System.out.println(Thread.currentThread().getName()+"生产后队列数据:"+sb.toString());
				}
				Thread.currentThread().sleep(1000);
			}
		} catch (Exception e) {
		}
	}
	public int produce(){
		i = i+1;
		System.out.println(Thread.currentThread().getName()+"生产者生产："+i);
		return i;
	}
	public void wakeup(){
		System.out.println(Thread.currentThread().getName()+"唤醒生产者线程");
		this.isWakeup = true;
		this.i = 0;
	}
	public void hangUp(){
		System.out.println(Thread.currentThread().getName()+"挂起生产者线程");
		this.isWakeup = false;
	}
	public void stop(){
		System.out.println(Thread.currentThread().getName()+"停止生产者线程");
		this.isStop = true;
	}
}
