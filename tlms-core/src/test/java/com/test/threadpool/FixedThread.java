package com.test.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThread implements Runnable{

	private int index;
	public FixedThread(int index){
		this.index = index;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
//		while(true){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(index+"|"+this);
		}
//	}

}
