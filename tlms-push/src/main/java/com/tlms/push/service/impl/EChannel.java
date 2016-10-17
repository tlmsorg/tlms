package com.tlms.push.service.impl;

public enum EChannel {
	WEB("1"),//站内推送
	MESSAGE("2"),//短信推送
	WEIXIN("3"),//微信推送
	MAIL("4");//邮件推送
	private final String value;
	EChannel(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
}
