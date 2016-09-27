package com.sys.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

import com.sys.aop.proxy.PerformanceHandler;

public class AopTestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ForumService forumService = new ForumServiceImpl();
		PerformanceHandler handler = new PerformanceHandler(forumService);
		ForumService forumProxy = (ForumService) Proxy.newProxyInstance(forumService.getClass().getClassLoader(), 
				forumService.getClass().getInterfaces(), handler);
//		forumProxy.removeForum(11111);
//		forumProxy.removeTopic(22222);
		
		UserBean userBean = new UserBean();
		userBean.setName("brighttang");
		userBean.setAge(18);
		userBean.setSex("男");
		forumProxy.userUpd(userBean);
		
		
		Field[] fields = userBean.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			System.out.println(field.getName());
		}
	}
}
