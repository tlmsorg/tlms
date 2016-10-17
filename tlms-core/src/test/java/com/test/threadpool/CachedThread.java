package com.test.threadpool;

public class CachedThread implements Runnable{
	private int index;
	public CachedThread(int index){
		this.index = index;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(this.index);
	}

}
