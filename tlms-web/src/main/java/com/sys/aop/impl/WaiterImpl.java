package com.sys.aop.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sys.aop.Waiter;
import com.sys.domain.Student;

@Service
public class WaiterImpl implements Waiter {
	public String userName;
	public void greetTo(String clientName) {
		// TODO Auto-generated method stub
		System.out.println("greetTo");
	}
	
	public void studentUpd(Student stu){
		System.out.println("studentUpd");
	}

}
