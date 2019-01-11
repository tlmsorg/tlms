package com.tlms.core.t;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;


public class TypeTest {
	private String name = "王小二";
	
	public <T> void test(T str) {
		System.out.println(str);
	}
	
	public <T> T getFiled(T str) {
		return  (T) str;
	}
	 
	public <T> T getField2() {
		
		return null;
	}
	public static void main(String[] args) {
		TypeTest tt = new TypeTest();
		System.out.println(tt.getFiled("666666"));
		System.out.println(55555);
		TypeTest tt2 = tt.getFiled(tt);
		System.out.println(tt2.name);
		
		ObjectPoint integerPoint = new ObjectPoint();  
		integerPoint.setX(new Integer(100));  
		Integer integerX=(Integer)integerPoint.getX(); 
		System.out.println(integerX);
		
		
		/*ObjectPoint floatPoint = new ObjectPoint();  
		floatPoint.setX(new Float(100.12f));  
		String floatX = (String)floatPoint.getX();  
		System.out.println(floatX);*/
		/*Point<Integer> p = new Point<Integer>() ;   
		p.setX(new Integer(100)) ;   
		System.out.println(p.getX());*/
		
		Point<Float> p = new Point<Float>() ;   
		p.setX(new Float(108)) ;   
		System.out.println(p.getX());  
		ArrayList al;
		tt.test(12345);

		// 静态方法
		StaticFans.StaticMethod("adfdsa");// 使用方法一
		StaticFans.<String>StaticMethod("1234");// 使用方法二

		// 常规方法
		StaticFans staticFans = new StaticFans();
		staticFans.OtherMethod(new Integer(123));// 使用方法一
		staticFans.<Integer>OtherMethod(new Integer(123));// 使用方法二
		
		ArrayList<Point> pointList = new ArrayList<Point>();
		Point point = new Point<Integer>();
		point.setX(123);
		pointList.add(point);
		List<Point> pointList2 = StaticFans.<Point>parseArray(JSONObject.toJSONString(pointList), Point.class);
		System.out.println(pointList2.size());
	}

}
