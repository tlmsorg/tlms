package com.tlms.core.ditest;

import org.springframework.beans.factory.annotation.Autowired;

public class ClassA {
	@Autowired
	private ClassB classBBBB;
	
	private String userName;


	public String getUserName() {
		return userName;
	}
	
	public String doBusyness() {
		String result = "";
		System.out.println("****调用ClassB执行业务逻辑开始****");
		/**
		 * classBBBB对象并没有直接调用new来实例化，而是通过在反射中注入
		 */
		result = classBBBB.doBusyness();
		System.out.println("****调用ClassB执行业务逻辑结束****");
		return result;
	}

}
