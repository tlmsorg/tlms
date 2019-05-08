package com.tlms.core.designpattern.commandpattern;

public class RejectCmd implements ICommand{
	private Receiver receiver;
	public RejectCmd(Receiver receiver) {
		this.receiver = receiver;
	}
	@Override
	public void execute() {
		System.out.println("RejectCommand-->execute()");
		this.receiver.doReject();
		
	}

}
