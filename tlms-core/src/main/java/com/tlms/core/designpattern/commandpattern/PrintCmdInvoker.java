package com.tlms.core.designpattern.commandpattern;

import com.alibaba.fastjson.JSONObject;

/**
 * 命令执行器
 * @author 160068
 * 2019年2月25日 上午11:38:02
 * @param <T>
 */
public class PrintCmdInvoker<T> {
	private ICommand commandImpl;

	public void setCommandImpl(ICommand commandImpl) {
		this.commandImpl = commandImpl;
	}
	
	public void runCommand() {
		System.out.println("Invoker-->runCommand() 命令"+this.commandImpl.getClass().getName()+"执行前");
		this.commandImpl.execute();
		System.out.println("Invoker-->runCommand() 命令"+this.commandImpl.getClass().getName()+"执行后");
	}
	
	public void execute(ICommand command) {
		command.execute();
	}
	
	public <T> T test3() {
		JSONObject json = new JSONObject();
		json.put("userName", "王小二");
		return  (T) json;
	}
	
	
/*	public <T> T generateJxflApply() {
//		return new PrintJxflApplyCmd<Object>().execute();
		return new PrintJxflApplyCmd<>().test3();
	}*/
	
	public <T> T execute(PrintCmd<T> command,PrintCmdContext commantContext) {
	    return command.execute(commantContext);
	}
	
	
}
