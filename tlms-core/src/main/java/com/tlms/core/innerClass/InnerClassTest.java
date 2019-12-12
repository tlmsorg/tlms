package com.tlms.core.innerClass;

import java.io.Serializable;

public class InnerClassTest{

	public class Inner{
		public void doMethod() {
			System.out.println("执行内部类操作");
		}
	}
	public static void main(String[] args) {
		InnerClassTest innerClassTest = new InnerClassTest();
		InnerClassTest.Inner inner = innerClassTest.new  Inner();
		inner.doMethod();
	}

}
