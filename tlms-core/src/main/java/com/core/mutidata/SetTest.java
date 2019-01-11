package com.core.mutidata;

import java.util.HashSet;
import java.util.Set;

public class SetTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> setTest = new HashSet<String>();
		setTest.add("111");
		setTest.add("2222");
		setTest.add("111");
		System.out.println(setTest);
		StringBuffer sb = new StringBuffer();
		System.out.println(sb.length());
	}

}
