package com.tlms.core.designpattern.commandpattern;

public interface PrintCmd<T> {
	public <T> T execute(PrintCmdContext commantContext);
}
