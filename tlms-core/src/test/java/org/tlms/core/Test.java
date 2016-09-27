package org.tlms.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Test {
	
	public String timeParse(String src,String pattern){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(calendar.getTime());
	}

	/**
	 * 对象属性转换 
	 * @param propName  输入格式："myUserName"
	 * @return 返回格式：：MY_USER_NAME
	 * 
	 */
	public String propParse(String propName){
		System.out.println("对象属性转换前："+propName);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < propName.length(); i++) {
			char c = propName.charAt(i);
			if(Character.isUpperCase(c)){
//				System.out.println(c);
				sb.append("_"+Character.toLowerCase(c));
			}else{
				sb.append(c);
			}
		}
		System.out.println("对象属性转换后："+sb.toString().toUpperCase());
		return sb.toString().toUpperCase();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String date = "20160601";
		System.out.println(date.substring(0, 4));
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("YYMMdd");
		System.out.println(format.format(cl.getTime()));
		
		new Test().propParse("myUserName");
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh-mm-ss");
		String timeNow = sdf.format(calendar.getTime());
		System.out.println("timeNow:"+timeNow);
		System.out.println(calendar.getTime());
	}

}
