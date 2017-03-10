package com.sys.aop.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author tom 2017-03-10
 */
@ControllerAdvice
@ResponseBody
public class MyException {
	@ResponseStatus(HttpStatus.OK)  
	@ExceptionHandler(Exception.class)  
	public String handleException(Exception e){
		return e.getMessage();
	}
}
