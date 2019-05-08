package com.tlms.core.designpattern.commandpattern;

import com.alibaba.fastjson.JSONObject;

public class PrintJxflApplyService<T> {
	public <T> T doSomething(PrintCmdContext commantContext) {
		System.out.println("命令上线文："+JSONObject.toJSONString(commantContext));
		JSONObject json = new JSONObject();
		PrintCmdResult printCmdResult = new PrintCmdResult();
		printCmdResult.setOssKey("打印文件生成的pdf文件");
		return (T) printCmdResult;
	}
	
	public <T> T test3() {
		JSONObject json = new JSONObject();
		json.put("userName", "王小二");
		return  (T) json;
	}
}
