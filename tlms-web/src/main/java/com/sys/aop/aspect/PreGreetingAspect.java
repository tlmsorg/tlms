package com.sys.aop.aspect;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before; 

@Aspect 
public class PreGreetingAspect {
	/*@Before("execution(* greetTo(..))")
	public void beforeGreeting(){
		System.out.println("execution beforeGreeting");
	}*/
	
	@Before("execution(* com.sys.aop.impl..*(..)) && args(object)")
	public void target(Object object){
		System.out.println("*********增强开始***********");
		System.out.println("PreGreetingAspect->target");
		System.out.println("对象类名 ："+object.getClass().getName());
		
		Method[] methods = object.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if(method.getName().startsWith("get")){
				System.out.println(method.getName());
			}
		}
		Field[] fields = object.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			
			try {
//				System.out.println("属性名:"+field.getName()+"|值："+(String) field.get(object));
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		System.out.println("*********增强结束***********");
	}
}
