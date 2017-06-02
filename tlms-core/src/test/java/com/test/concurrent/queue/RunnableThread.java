package com.test.concurrent.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RunnableThread implements Runnable{
	private ThreadResponseVo trv = null;
	int loopTime = 0;
	public RunnableThread(ThreadResponseVo trv,int loopTime){
		this.trv = trv;
		this.loopTime = loopTime;
	}

	@Override
	public void run() {
		System.out.println("模拟第三方处理,5秒.....");
		int i = 0;
		while(i++ < loopTime){
			System.out.println("当前耗时："+i);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		trv.setName("brighttang");
		trv.setAge(40);
		trv.setSex("男");
	}
}
