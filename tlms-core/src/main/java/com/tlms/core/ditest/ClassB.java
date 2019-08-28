package com.tlms.core.ditest;

public class ClassB {
	private String userName = "brighttang";

	public String getUserName() {
		return userName;
	}
	
	public String doBusyness() {
		System.out.println("classB中执行相关业务逻辑");
		return "执行返回";
	}

}
