package com.sys.aop.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class MyException {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  
	@ExceptionHandler(Exception.class)  
	public String handleException(Exception e){
		System.out.println("handleException5555555555");
		return e.getMessage();
	}
}
