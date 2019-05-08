package com.tlms.core.designpattern.commandpattern;

public class Receiver {
	public void doCommit() {
		System.out.println("Receiver-->doCommit()");
	}
	public void doReject() {
		System.out.println("Receiver-->doReject()");
	}
}
