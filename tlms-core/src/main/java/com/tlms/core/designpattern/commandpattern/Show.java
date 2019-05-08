package com.tlms.core.designpattern.commandpattern;

import com.alibaba.fastjson.JSONObject;

public class Show<T> implements PrintCmd<T>{

	public String test1(T t) {
		return "1234";
	}
	public <T> Integer test2(T t) {
		return 1234;
	}
	
	public <T> T test3() {
		JSONObject json = new JSONObject();
		json.put("userName", "王小二");
		return  (T) json;
	}
	public static void main(String[] args) {
		Show<String> show = new Show<>();
//		show.test1(new Integer(1));
		Integer ossKey = show.test2(new Integer(1));
		Integer ossKey2 = show.test2(new String("1"));
		JSONObject json = show.test3();
		System.out.println(ossKey);
		System.out.println(ossKey2);
		System.out.println(json);
	}
	@Override
	public <T> T execute(PrintCmdContext commantContext) {
		// TODO Auto-generated method stub
		return null;
	}


}
