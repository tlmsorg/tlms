package com.tlms.aop.test;

public class CustomerServiceImpl implements ICustomerService {  
	  
    public void doSomething1() {  
  
        System.out.println("Inside CustomerServiceImpl.doSomething1()");  
  
        doSomething2();  
  
    }  
  
    public void doSomething2() {  
  
        System.out.println("Inside CustomerServiceImpl.doSomething2()");  
  
    }  
  
}
