package com.test.doubleformate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.tlms.core.util.Utils;

/**对象中的Double成员属性格式化测试 2016-11-23
 * @author tom
 *
 */
public class DoubleFormate {

	/**
	 * tom 2016年11月23日
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person person = new Person();
		person.setName("tang");
		person.setScore(0.25447);
		person.setParentScore(8.2658);
		
		Person person2 = new Person();
		person2.setName("tang");
		person2.setScore(0.25447);
		List<Person> personList = new ArrayList<Person>();
		personList.add(person2);
		person.setPersonList(personList);
		Utils.formateDoubleOfObject(person,2);
		System.out.println(JSONObject.toJSONString(person));
		for(Person tempPerson:person.getPersonList()){
			Utils.formateDoubleOfObject(tempPerson,2);
		}
		System.out.println(JSONObject.toJSONString(person));
	}

}
