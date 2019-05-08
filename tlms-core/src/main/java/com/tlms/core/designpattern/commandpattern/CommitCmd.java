package com.tlms.core.designpattern.commandpattern;

public class CommitCmd implements ICommand {

	private Receiver receiver;
	public CommitCmd(Receiver receiver) {
		this.receiver = receiver;
	}
	@Override
	public void execute() {
		System.out.println("CommitCmd-->execute()");
		this.receiver.doCommit();
	}

}
