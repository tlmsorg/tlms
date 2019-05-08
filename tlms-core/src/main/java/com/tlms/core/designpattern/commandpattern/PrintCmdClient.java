package com.tlms.core.designpattern.commandpattern;

import org.activiti.engine.ActivitiIllegalArgumentException;

public class PrintCmdClient {

	public <T> T executeCommand(PrintCmd<T> command,PrintCmdContext commantContext) {
	    if (command == null) {
	      throw new ActivitiIllegalArgumentException("The command is null");
	    }
//	    return commandExecutor.execute(command);
		return new PrintCmdInvoker<>().execute(command,commantContext);
	}
	
	public static void main(String[] args) {
		PrintCmdInvoker invoker = new PrintCmdInvoker();
		
		invoker.setCommandImpl(new CommitCmd(new Receiver()));
		invoker.runCommand();
		
		invoker.setCommandImpl(new RejectCmd(new Receiver()));
		invoker.runCommand();
		
		JxflApplyPrintCmd<PrintCmdResult> printJxflApplyCmd = new JxflApplyPrintCmd<PrintCmdResult>();
		PrintCmdClient client = new PrintCmdClient();
		PrintCmdContext commantContext = new PrintCmdContext();
		commantContext.setAppId("A401201542123ICBC");
		PrintCmdResult printCmdResult = client.executeCommand(printJxflApplyCmd,commantContext);
		System.out.println("ossKey:"+printCmdResult.getOssKey());
		/**
		 * new CommitCmd(new Receiver()):将指定的接收者对象赋予指定命令
		 */
//		invoker.execute(new CommitCmd(new Receiver()));
//		invoker.execute(new RejectCmd(new Receiver()));
	}

}
