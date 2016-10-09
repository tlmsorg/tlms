package com.tlms.aop.test;

public class TestProxy {  
    
    public static void main(String[] args) {  
        // 创建代理目标对象。对于Spring来说，这一工作  
        // 是由Spring DI容器完成的。  
        ICustomerService serviceProxyTarget = new CustomerServiceImpl();  
  
        // 创建代理对象。对于Spring来说，这一工作  
        // 也是由Spring DI容器完成的。  
        CustomerServiceProxy serviceProxy = new CustomerServiceProxy();  
        serviceProxy.setCustomerService(serviceProxyTarget);  
        ICustomerService serviceBean = (ICustomerService) serviceProxy;  
  
        // 调用业务逻辑操作  
        serviceBean.doSomething1();  
    }  
}