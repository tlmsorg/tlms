package com.core.mutidata;

import org.aspectj.lang.JoinPoint;

public class DataSourceInterceptor {
	 public void setdataSourceOne(JoinPoint jp) {  
	        DatabaseContextHolder.setCustomerType("dataSource1");  
	    }  
	      
	    public void setdataSourceTwo(JoinPoint jp) {  
	        DatabaseContextHolder.setCustomerType("dataSource2");  
	    }  
}
