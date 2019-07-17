package com.tlms.core.test;

import com.tlms.core.annotiontest.UserDTO;

@com.tlms.core.annotiontest.AnnotationType
public class AnnotationTest {
	private static String tableName;
	
	public static void main(String[] args) {
		UserDTO userDTO = new UserDTO();
		System.out.println(userDTO.getUserName());
		System.out.println(tableName);
	}

}
