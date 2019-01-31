package com.tlms.core.threadlocaltest;

/**
 * 多线程数据共享
 * @author 160068
 * 2019年1月18日 下午2:52:03
 */
public class UserThreadShare implements Runnable{

	@Override
	public void run() {
		while(true) {
			VarPoolShare varPool = VarPoolShare.getInstanceNormal();
			String currThreadName = Thread.currentThread().getName();
			System.out.println("线程"+currThreadName+"中取值:"+varPool.get("name"));
			for(int i = 0;i < 5;i++) {
				varPool.put("name", "王Share"+i);
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
