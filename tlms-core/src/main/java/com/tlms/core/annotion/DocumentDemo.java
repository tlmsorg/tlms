package com.tlms.core.annotion;

import java.lang.annotation.Annotation;
import java.util.Arrays;

@DocumentB
public class DocumentDemo extends ClassA{

	public static void main(String[] args) {
		Class<?> cl = DocumentDemo.class;
		Annotation[] antts = cl.getAnnotations();
		System.out.println("注解数组:"+Arrays.toString(antts));
		for (Annotation annotation : antts) {
			System.out.println("annotation:"+annotation);
		}
		DocumentA docA = cl.getAnnotation(DocumentA.class);
		System.out.println("注解A:"+docA);
		
		boolean isAnnotation = cl.isAnnotationPresent(DocumentA.class);
		System.out.println("注解A是否出现:"+isAnnotation);
	}

}
