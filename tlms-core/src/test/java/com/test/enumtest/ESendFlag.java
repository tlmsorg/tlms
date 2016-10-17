package com.test.enumtest;

public enum ESendFlag {
	willSend("01","未发送"),
	havaSend("02","已发送");
	private String code;
	private String text;
	private ESendFlag(String code,String text) {
		// TODO Auto-generated constructor stub
		System.out.println("code:"+code+"|"+"text:"+text);
		this.code = code;
		this.text = text;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
