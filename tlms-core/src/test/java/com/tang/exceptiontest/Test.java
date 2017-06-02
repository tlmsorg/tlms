package com.tang.exceptiontest;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RuntimeException exception  = new RuntimeException("2222");
		try {
			throw new ExceptionTest("22222", exception.getCause());
		} catch (ExceptionTest e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}

}
