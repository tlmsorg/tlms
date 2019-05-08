package com.tlms.core.designpattern.commandpattern;

public class JxflApplyPrintCmd<T> implements PrintCmd<T>{
	/*public <T> T test3() {
		JSONObject json = new JSONObject();
		json.put("userName", "王小二");
//		return  (T) json;
//		return new PrintJxflService<>().test3();
		return new PrintJxflService<>().doSomething();
	}*/
	private  PrintJxflApplyService<PrintCmdResult> printJxflServiceImpl = new PrintJxflApplyService<PrintCmdResult>();
	
	public <T> T execute(PrintCmdContext commantContext) {
//		return  new PrintJxflService().doSomething();
//		return new PrintJxflService<>().doSomething(commantContext);
		return printJxflServiceImpl.doSomething(commantContext);
//		return new PrintJxflApplyCmd<>().test3();
//		return new PrintJxflService<>().test3();
	}


}
