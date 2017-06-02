package com.tang.exceptiontest;

public class ExceptionTest extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExceptionTest() {
		super();
	}
	public ExceptionTest(String message){
		super(message);
	}
	
	public ExceptionTest(String message,Throwable throwable){
	
//		super(message, throwable);
		System.out.println(throwable.getCause());
	}

}
