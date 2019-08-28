package com.tlms.core.ditest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @company 潽金金融
 * @project_name tlms-core
 * @ClassName ClassMain
 * @Description 依赖注入（DI）测试，在类classA中注入classB实例
 * @author 160068
 * @date 2019年8月28日 下午5:45:21
 * @version V1.0
 */
public class ClassMain {

	public static void main(String[] args) throws Exception {
		Class clazzA = Class.forName("com.tlms.core.ditest.ClassA");
		
		Field[] fieldClassB = clazzA.getDeclaredFields();
		//获取ClassA实例
		ClassA objA = (ClassA) clazzA.newInstance();
		for (Field field : fieldClassB) {
			field.setAccessible(true);
			//获取注解
			Annotation[] annotations = field.getDeclaredAnnotations();
			//目标注解
			Annotation targetAnnotation = null;
			for (Annotation annotation : annotations) {
				if(annotation instanceof Autowired) {
					/**
					 * 找到对象A中属性使用了AutoWire注解的field
					 */
					targetAnnotation = annotation;
					break;
				}
			}
			if(targetAnnotation != null) {
				//获取classB类
				Class clazzB = field.getType();
				//获取classB实例
				Object objB = clazzB.newInstance();
				/**
				 * 注入对象：classA对象赋值（对classA中的classB对象赋值）
				 */
				field.set(objA, objB);
			}
		}
				
		Field fieldUserName = clazzA.getDeclaredField("userName");
		fieldUserName.setAccessible(true);
		//classA对象赋值（classB对象赋值）
		fieldUserName.set(objA, "王小二");
		//测试ClassA中ClassB数据
		System.out.println("userName:"+objA.getUserName());
		//测试调用classB方法
		System.out.println("在classA中，执行业务逻辑方法：");
		objA.doBusyness();
		
	}

}
