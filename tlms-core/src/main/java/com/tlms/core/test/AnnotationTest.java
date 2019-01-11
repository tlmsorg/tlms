package com.tlms.core.test;

import com.tlms.core.annotion.UserDTO;

@com.tlms.core.annotion.AnnotationType
public class AnnotationTest {
	private static String tableName;
	
	public static void main(String[] args) {
		UserDTO userDTO = new UserDTO();
		System.out.println(userDTO.getUserName());
		System.out.println(tableName);
	}

}
