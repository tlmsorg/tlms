package com.test.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int corePoolSize = 2;
		int maximumPoolSize = 2;
		long keepAliveTime = 40000;
		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MINUTES, workQueue);
	}

}
