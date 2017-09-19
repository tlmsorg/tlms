package com.test.log4j;

import org.apache.log4j.Logger;

public class Log4jTest {
	private static final Logger logger = Logger.getLogger(Log4jTest.class);
	public static void main(String[] args) {
		
		while(true){
			
//			logger.info("info日志：");
			for (int i = 0; i < 10000; i++) {
			logger.info("info日志："+i);
			}
			logger.debug("debug");
			logger.error("error");
			
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
