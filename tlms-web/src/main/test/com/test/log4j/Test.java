package com.test.log4j;

public class Test {
	public static void main(String[] args) {
		System.out.println(false && false);
		System.out.println(true && false);
		
		while(true){
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("******************************");
			System.out.println(System.currentTimeMillis());
			System.out.println(System.currentTimeMillis()+5000);
		}
		
		
	}
}
