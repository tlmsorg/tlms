package com.test.postloan.copytest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSONObject;
import com.tlms.core.util.Utils;

/**
 * @author tom
 *
 */
public class TestMain {

	/**
	 * 测试工具类中的属性拷贝方法
	 * tom 2016年11月7日
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestBean1 tb1 = new TestBean1();
		TestBean2 tb2 = new TestBean2();
		TestBean3 tb3 = new  TestBean3();
		TestBean4 tb4 = new TestBean4();
		List<TestBean3> tb3List = new ArrayList<TestBean3>();
		List<TestBean4> tb4List = new ArrayList<TestBean4>();
		
		//初始化tb4
		tb4.setAddress("永川");
		tb4.setPhone("15223426350");
		//初始化tb3
		tb4List.add(tb4);
		tb3.setAddress("重庆");
		tb3.setPhone("18723290701");
		tb3.setListAddr(tb4List);
		tb3List.add(tb3);
		//初始化tb1
		tb1.setName("唐亮");
		tb1.setSex("男");
		tb1.setScore(22.4);
		tb1.setAge(11);
		tb1.setTbList(tb3List);
		tb1.setTestBean4(tb4);
		tb1.setAddress("重庆永川");
//		Utils.copyProperties(tb1, tb2);
		BeanUtils.copyProperties(tb1, tb2);
		System.out.println("tb1拷贝至tb2后，tb2值："+JSONObject.toJSONString(tb2));
	}
}
