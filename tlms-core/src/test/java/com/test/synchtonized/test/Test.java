package com.test.synchtonized.test;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0;i < 10;i ++){
			list.add(i+"");
		}
		/*for (String str : list) {
			list.remove(str);
			System.out.println(list.size());
		}*/
	
		for (int i = 0; i < list.size(); i++) {
			if("5".equals(list.get(i)))	
				list.remove(list.get(i));
			if("7".equals(list.get(i)))	
				list.remove(list.get(i));
			if("8".equals(list.get(i)))	
				list.remove(list.get(i));
			System.out.println(i+"|"+list.size());
		}
		for (String string : list) {
			System.out.println(string);
		}
	}

}
