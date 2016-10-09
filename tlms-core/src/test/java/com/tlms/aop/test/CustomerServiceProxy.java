package com.tlms.aop.test;

public class CustomerServiceProxy implements ICustomerService {  
	  
    private ICustomerService customerService;  
  
    public void setCustomerService(ICustomerService customerService) {  
        this.customerService = customerService;  
    }  
  
    public void doSomething1() {  
        doBefore();  
        customerService.doSomething1();  
        doAfter();  
    }  
  
    public void doSomething2() {  
        doBefore();  
        customerService.doSomething2();  
        doAfter();  
    }  
  
    private void doBefore() {  
        // 例如，可以在此处开启事务  
        System.out.println("do some important things before...");  
    }  
  
    private void doAfter() {  
        // 例如，可以在此处提交或回滚事务、释放资源等等  
        System.out.println("do some important things after...");  
    }  
  
}