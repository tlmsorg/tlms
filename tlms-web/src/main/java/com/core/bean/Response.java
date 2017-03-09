package com.core.bean;

public class Response {
	String msg = "";
	public Response failure(String msg){
		this.msg = msg;
		return this;
		
	}
}
