package com.test.fastjson.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

public class Test {
	public static void main(String[] args) {
		JSONObject apply = new JSONObject();
		apply.put("appId", "111111");
		JSONArray finances = new JSONArray();
		apply.put("finances", finances);
		JSONObject finance1 = new JSONObject();
		finance1.put("name", "融资信息1");
		finances.add(finance1);
		JSONObject finance2 = new JSONObject();
		finance2.put("name", "融资信息2");
		finances.add(finance2);
		System.out.println(JSONObject.toJSONString(apply));
		
		JSONObject parseFinance1 = (JSONObject) JSONPath.eval(apply, "finances[0]");
		JSONObject parseFinance2 = (JSONObject) JSONPath.eval(apply, "finances[1]");
		System.out.println(JSONObject.toJSONString(parseFinance1));
		System.out.println(JSONObject.toJSONString(parseFinance2));
		
		String name1 = (String) JSONPath.eval(apply, "finances[0].name");
		String name2 = (String) JSONPath.eval(apply, "finances[1].name");
		System.out.println(name1);
		System.out.println(name2);
		
	}
}
