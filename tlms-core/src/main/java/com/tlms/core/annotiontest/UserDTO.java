package com.tlms.core.annotiontest;

public class UserDTO {
	
	@AnnotationUser(userName="TOM")
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
