package com.tlms.core.groovy

import com.alibaba.fastjson.JSONObject

class GroovyType {
	static void main(String[] args) {
		println("hello groovy");
		def x = 5;
		println(x);
		JSONObject json = new JSONObject();
		json.put("name", "tom");
		TransactionMapData tmd = TransactionMapData.getInstance();
		tmd.set("tenantName", "承租人姓名");
	}
}
	
