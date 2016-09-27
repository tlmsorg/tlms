package com.sys.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.aop.Waiter;
import com.sys.domain.Student;

@Controller
public class AopController {
	@Autowired
	private Waiter waiter;
	
	@ResponseBody
	@RequestMapping(value="/aspectj_test.ctrl")
	public String aspectj_test(){
		System.out.println("AopController->aspectj_test");
		waiter.greetTo("111");
		return "rollback_test";
	}
	
	@ResponseBody
	@RequestMapping(value="/aspectj_test_studentupd.ctrl")
	public String aspectj_test_studentupd(){
		System.out.println("AopController->aspectj_test");
		Student student = new Student();
		student.setCouse("语文");
		student.setPoint("100");
		student.setUserId("200810405234");
		waiter.studentUpd(student);
		return "rollback_test";
	}
}	
