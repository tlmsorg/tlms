package com.test.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型测试
 * @author tom
 *
 */
public class GenericityTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Genericity<Integer> gc = new Genericity<Integer>();
		List<String> list = new ArrayList<String>();
		list.add("111");
		list.add("222");
		list.add("333");
		List<?> list2 = list;
		System.out.println(list2);
		gc.setData(10);
		System.out.println(gc.getData());
	}
}
