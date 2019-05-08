package com.tlms.core.threadlocaltest;

public class UserThreadIsolation implements Runnable{

	@Override
	public void run() {
		while(true) {
			
			try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			VarPoolIsolation varPool = VarPoolIsolation.getInstanceIsolation();
			String currThreadName = Thread.currentThread().getName();
			System.out.println("线程"+currThreadName+"中取值："+varPool.get("name"));
			for(int i = 0;i < 5;i++) {
				varPool.put("name", "王Isolation"+i);
				System.out.println("线程"+currThreadName+"中赋值："+varPool.get("name"));
				try {
					Thread.currentThread().sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		}
		
	}

}
