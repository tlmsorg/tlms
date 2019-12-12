package com.tlms.core.annotiontest;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;

public class DbCreateMain2 {

	public static void main(String[] args) throws Exception {
		Class<?> memberClazz = Class.forName("com.tlms.core.annotiontest.Member");
		//循环类所有注解
		Annotation[] annotationArray = memberClazz.getAnnotations();
		System.out.println(annotationArray.length);
		for (Annotation annotation : annotationArray) {
			DBTable dbTable = (DBTable) annotation;
			System.out.println(dbTable.name());
		}
		//获取指定注解
		DBTable dbTable = memberClazz.getAnnotation(DBTable.class);
		System.out.println(dbTable.name());
		
		
		Field[] fieldArray = memberClazz.getFields();
		for (Field field : fieldArray) {
			System.out.println("属性："+field.getName());
		}
	}

}
