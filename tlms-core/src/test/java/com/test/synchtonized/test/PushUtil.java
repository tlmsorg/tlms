package com.test.synchtonized.test;

import java.util.ArrayList;
import java.util.List;

public class PushUtil {
	public static final ArrayList<String> sessionList = new ArrayList<String>();;
	
	public synchronized static List<String> getSessionList(){
		
		try {
			int i = 0;
			while(i < 10){
				i++;
				System.out.println(i+"----当前线程名："+Thread.currentThread().getName());
				Thread.currentThread().sleep(1000);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return sessionList;
	}
}
