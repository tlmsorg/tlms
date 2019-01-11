package com.tlms.core.t;

import java.util.List;

import com.alibaba.fastjson.JSON;


public class StaticFans {
	/**
	 * 方法入参泛型
	 * 160068
	 * 2018年12月6日 下午3:13:36
	 * @param a
	 */
	// 静态函数
	public static <T> void StaticMethod(T a) {
		System.out.println("静态函数 :" + a);
	}

	/**
	 * 方法入参泛型
	 * 160068
	 * 2018年12月6日 下午3:13:09
	 * @param a
	 */
	// 普通函数
	public <T> void OtherMethod(T a) {
		System.out.println("普通函数 :" + a);
	}
	
	/**
	 * 方法入参泛型、返回值泛型
	 * 160068
	 * 2018年12月6日 下午3:13:50
	 * @param response
	 * @param object
	 * @return
	 */
	public static <T> List<T> parseArray(String response,Class<T> object){  
	    List<T> modelList = JSON.parseArray(response, object);  
	    return modelList;  
	}  
	
}
