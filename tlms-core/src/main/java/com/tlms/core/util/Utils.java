package com.tlms.core.util;

public class Utils {
	/**
	 * 对象属性转换 
	 * @param propName  输入格式："myUserName"
	 * @return 返回格式：：MY_USER_NAME
	 */
	public static String fieldParse2Col(String propName){
		System.out.println("对象属性转换前："+propName);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < propName.length(); i++) {
			char c = propName.charAt(i);
			if(Character.isUpperCase(c)){
				sb.append("_"+Character.toLowerCase(c));
			}else{
				sb.append(c);
			}
		}
		return sb.toString().toUpperCase();
	}
}
